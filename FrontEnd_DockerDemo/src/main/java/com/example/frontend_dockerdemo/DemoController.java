package com.example.frontend_dockerdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/test")
    public String getString(Model model) {
        String a = getString();
        model.addAttribute("hejsan", a);
        return "index";
    }

    /*@GetMapping("")
    public String getStringFromBackend(Model model) {

        String a = getString();
        model.addAttribute("allProducts", a);
        return "index";
    }*/

    public String getString(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/backend-test";
        return restTemplate.getForObject(url, String.class);
    }

}
