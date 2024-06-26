package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.Enum.Role;
import com.sda.online_shopping_app.entity.UserEntity;
import com.sda.online_shopping_app.service.ProductService;
import com.sda.online_shopping_app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class HomePage {


    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


//    @GetMapping("/")
//    public String homePage() {
//        return "home"; // This will return "home.html" because of Thymeleaf's template resolution
//    }


//    @GetMapping("/signup")
//    public String showSignUpForm(Model model) {
//        model.addAttribute("user", new UserEntity());
//        return "shop/home";
//    }


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("user", new UserEntity());
        return "shop/home";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("user") UserEntity user) {
        userService.registerUser(user);
        return "redirect:/shop/home";
    }


    @PostMapping("/signin")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Optional<UserEntity> userOptional = userService.loginUser(email, password);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            session.setAttribute("loggedInUser", user);

            if (user.getRole() == Role.ADMIN)  {
                return "redirect:/products/create";
            }
            else if(user.getRole() == Role.USER) {
                return "redirect:/products/list";
            } else {
                return "redirect:/shop/home";
            }
        } else {
            model.addAttribute("error", true);
            return "shop/home";
        }
    }
}