package com.sda.online_shopping_app.controller;


import com.sda.online_shopping_app.entity.Product;
import com.sda.online_shopping_app.entity.User;
import com.sda.online_shopping_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/signup")
    public ResponseEntity<String> signUp( @RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password) {
        Optional<User> userOptional = userService.loginUser(email, password);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get()); // Returning user details on successful login
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

    }
}