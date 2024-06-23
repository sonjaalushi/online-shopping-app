package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.UserEntity;
import com.sda.online_shopping_app.service.ProductService;
import com.sda.online_shopping_app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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



//    @GetMapping("/signup")
//    public String showSignUpForm(Model model) {
//        model.addAttribute("user", new UserEntity());
//        return "shop/home";
//    }



    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", new UserEntity());
        return "shop/home";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") UserEntity user) {
        userService.registerUser(user);
        return "redirect:/shop/home";
    }


    @PostMapping("/signin")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Optional<UserEntity> user = userService.loginUser(email, password);
        if (user.isPresent()) {
            session.setAttribute("loggedInUser", user.get());
            return "redirect:/products/list";
        } else {
            model.addAttribute("error", true);
            return "shop/home"; // ensure this matches the template name
        }
    }



}