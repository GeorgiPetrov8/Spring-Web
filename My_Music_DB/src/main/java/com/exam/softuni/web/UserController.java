package com.exam.softuni.web;

import com.exam.softuni.model.binding.UserLoginBindingModel;
import com.exam.softuni.model.binding.UserRegisterBindingModel;
import com.exam.softuni.model.service.UserServiceModel;
import com.exam.softuni.service.UserService;
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
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("invalidConfirmPassword", false);
            model.addAttribute("usernameExists", false);
            model.addAttribute("emailExists", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        boolean confirmPassValidation = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
        if (!confirmPassValidation) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("invalidConfirmPassword", true);
            return "redirect:register";
        }

        boolean isUsernameExists = userService.findUserByUsername(userRegisterBindingModel.getUsername());
        if (isUsernameExists) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("usernameExists", this);
            return "redirect:register";
        }

        boolean isEmailExists = userService.findUserByEmail(userRegisterBindingModel.getEmail());
        if (isEmailExists) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("emailExists", true);
            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFoundUser", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        boolean isUserRegistered = userService.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (!isUserRegistered) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFoundUser", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userLoginBindingModel);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

}
