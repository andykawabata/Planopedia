package com.team.planopedia.controllers;

import com.team.planopedia.modelsAndServices.restaurant.Restaurant;
import com.team.planopedia.modelsAndServices.restaurant.RestaurantService;
import com.team.planopedia.modelsAndServices.weather.Weather;
import com.team.planopedia.modelsAndServices.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultController {
    
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
    
}
