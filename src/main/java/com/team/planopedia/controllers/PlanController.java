package com.team.planopedia.controllers;

import com.team.planopedia.modelsAndServices.restaurant.Restaurant;
import com.team.planopedia.modelsAndServices.restaurant.RestaurantService;
import com.team.planopedia.modelsAndServices.weather.Weather;
import com.team.planopedia.modelsAndServices.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/plan")
public class PlanController {
    
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    WeatherService weatherService;
    
    @GetMapping("/result")
    public String result(Model model,
                         @RequestParam("city") String city,                 //This variables are obtained via GET request from form page.
                         @RequestParam("zip") String zip,
                         @RequestParam("cuisine") String cuisine,
                         @RequestParam("numPeople") String numPeople)
    {
        Restaurant restaurant = restaurantService.generateRestaurant(city, zip, cuisine, Integer.parseInt(numPeople));
        Weather weather = weatherService.getWeatherFromZip(zip);
        
        model.addAttribute("restaurant",restaurant);   //adding the Restaurant object to a model which is accessible in the HTML pages.
        model.addAttribute("weather",restaurant);
        
        return "result";
    }
    
    @GetMapping("/create-user-plan")
    public String showUserPlanForm() {
        return "index";
    } 
    
    @GetMapping("/create-guest-plan")
    public String showGuestPlanForm() {
        return "index";
    } 
    
    @PostMapping("/save-plan")
    public String savePlan() {
        return "index";
    } 
    
    @PostMapping("/rate-plan")
    public String ratePlan() {
        return "index";
    } 
    
    
    
}
