package com.team.planopedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String homeScreen(Model model, @RequestParam(required = false) String error){
        
        System.out.println("myerror: " + error);
        if(error != null){
            System.out.println(error);
            model.addAttribute("error", (String) "Could not find any restaurants matching criteria.");
        }
        return "index";
    }
}
