package com.exam.softuni.web;

import com.exam.softuni.model.binding.AlbumAddBindingModel;
import com.exam.softuni.model.binding.UserLoginBindingModel;
import com.exam.softuni.model.service.AlbumServiceModel;
import com.exam.softuni.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAdd(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }

        if (!model.containsAttribute("albumAddBindingModel")) {
            model.addAttribute("albumAddBindingModel", new AlbumAddBindingModel());
        }
        return "add-album";
    }

    @PostMapping("/add")
    public String postAdd(@Valid AlbumAddBindingModel albumAddBindingModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);
            return "redirect:add";
        }

        UserLoginBindingModel userLoginBindingModel = (UserLoginBindingModel) httpSession.getAttribute("user");
        String username = userLoginBindingModel.getUsername();

        albumService.addAlbum(modelMapper.map(albumAddBindingModel, AlbumServiceModel.class), username);

        return "redirect:/";
    }
}
