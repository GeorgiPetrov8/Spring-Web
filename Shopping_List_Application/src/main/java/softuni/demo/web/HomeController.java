package softuni.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.demo.model.CategoryName;
import softuni.demo.model.view.ProductViewModel;
import softuni.demo.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("totalSum", productService.getTotalPrice());

        List<ProductViewModel> drinks = productService.findAllProductsByCategoryName(CategoryName.DRINK)
                .stream().map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("drinks", drinks);
        List<ProductViewModel> foods = productService.findAllProductsByCategoryName(CategoryName.FOOD)
                .stream().map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("foods", foods);
        List<ProductViewModel> households = productService.findAllProductsByCategoryName(CategoryName.HOUSEHOLD)
                .stream().map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("households", households);
        List<ProductViewModel> others = productService.findAllProductsByCategoryName(CategoryName.OTHER)
                .stream().map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("others", others);
        return "home";
    }
}
