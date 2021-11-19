package com.team.planopedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller responsible for return index page
 * @author andrewkawabata
 */
@Controller
public class HomeController {
    
    // returns home page. can be called with error attached
    @GetMapping("/")
    public String homeScreen(Model model, @RequestParam(required = false) String error){
        
        if(error != null){
            System.out.println(error);
            model.addAttribute("error", (String) "Could not find any restaurants matching criteria.");
        }
        return "index";
    }
}
