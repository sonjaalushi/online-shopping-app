package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.UserEntity;
import com.sda.online_shopping_app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {




    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<UserEntity> users = userService.getAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "users/create";
    }

//    @PostMapping("/signup")
//    public String signUp(@ModelAttribute("user") UserEntity user) {
//        UserEntity newUser = userService.registerUser(user);
//        return "redirect:/users";
//    }

//    @PostMapping("/signin")
//    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
//
//        Optional<UserEntity> user = userService.loginUser(email, password);
//        if (user.isPresent()) {
//            model.addAttribute("user", user.get());
//            return "redirect:/success";
//        } else {
//            model.addAttribute("error", true);
//            return "signin";
//        }
//    }


//    @PostMapping("/signin")
//    public String signIn(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
//
//        Optional<UserEntity> user = userService.loginUser(email, password);
//
//        if (user.isPresent()) {
//
//            UserEntity loggedInUser = user.get();
//
//            session.setAttribute("user", loggedInUser);
//
//            if ("ADMIN".equals(loggedInUser.getRole())) {
//                return "redirect:/users/home";
//            } else {
//                return "redirect:/products/list";
//            }
//        } else {
//            model.addAttribute("error", "Invalid email or password.");
//            return "signin";
//        }
//    }


    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Integer id, Model model) {
        Optional<UserEntity> user = userService.getById(id);
        model.addAttribute("user", user.get());
        return "users/edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("user") UserEntity user) {
        user.setId(id);
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }



}