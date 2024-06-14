package com.sda.online_shopping_app.frontendController;

import com.sda.online_shopping_app.entity.User;
import com.sda.online_shopping_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserCon {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        Optional<User> optionalUser = userService.getById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            return "user";
        } else {
            return "redirect:/error";
        }
    }




    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new RuntimeException("A");
        } else {
            userService.save(user);
            return "redirect:/users";
        }
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
