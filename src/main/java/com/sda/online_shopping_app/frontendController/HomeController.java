package com.sda.online_shopping_app.frontendController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/homeOnline")
    public String home() {
        return "index";
    }
}