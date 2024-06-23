package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.UserEntity;
import com.sda.online_shopping_app.service.ProductService;
import com.sda.online_shopping_app.service.UserService;
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

    @GetMapping
    public String home(){
        return "shop/home";
    }



    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signUp(@RequestBody UserEntity user) {

        UserEntity newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }


    @PostMapping("/signin")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {

        Optional<UserEntity> user = userService.loginUser(email, password);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "redirect:/success";
        } else {
            model.addAttribute("error", true);
            return "signin";
        }
    }



}