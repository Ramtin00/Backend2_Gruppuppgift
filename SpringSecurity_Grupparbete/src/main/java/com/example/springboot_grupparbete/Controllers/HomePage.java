package com.example.springboot_grupparbete.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
}
