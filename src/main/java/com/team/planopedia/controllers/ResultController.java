package com.team.planopedia.controllers;

import com.team.planopedia.modelsAndServices.restaurant.Restaurant;
import com.team.planopedia.modelsAndServices.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {
    
    @Autowired
    RestaurantService restaurantService;
    
    @GetMapping("/result")
    public String result(/*parameters*/){
        
        Restaurant restaurant = restaurantService.generateRestaurant(null, null, null, 0);
        
        return "home";
    }
}
