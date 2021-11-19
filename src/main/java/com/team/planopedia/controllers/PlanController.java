package com.team.planopedia.controllers;

import com.team.planopedia.dao.Category;
import com.team.planopedia.dao.Plan;
import com.team.planopedia.dao.RestaurantInfo;
import com.team.planopedia.dao.User;
import com.team.planopedia.modelsAndServices.restaurant.Restaurant;
import com.team.planopedia.modelsAndServices.restaurant.RestaurantService;
import com.team.planopedia.modelsAndServices.weather.Weather;
import com.team.planopedia.modelsAndServices.weather.WeatherService;
import com.team.planopedia.repository.PlanRepository;
import com.team.planopedia.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller handling plan creation
 * @author andrewkawabata
 */
@Controller
@RequestMapping(path = "/plan")
public class PlanController {
    
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    WeatherService weatherService;
    @Autowired
    PlanRepository planRepository;
    @Autowired
    UserRepository userRepository;
    
    // Method when someone submits request for new plan
    @GetMapping("/result")
    public String result(Model model,
                         @RequestParam("city") String city,                 //This variables are obtained via GET request from form page.
                         @RequestParam("zip") String zip,
                         @RequestParam("cuisine") String cuisine,
                         @RequestParam("numPeople") String numPeople, HttpSession session){
    
        // get user if session exists. else assign null to user.
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
        User user = null;
        if(userMap != null){
            String userEmail = (String) userMap.get("googleEmail");
            user = userRepository.findByGoogleEmail(userEmail);
        }
        
        // get restaurant
        Restaurant restaurant = restaurantService.generateRestaurant(city, zip, cuisine, Integer.parseInt(numPeople), user);
        if(restaurant == null){
            return "redirect:/?error=true#plan";
        }
        // get weather
        Weather weather = weatherService.getWeatherFromZip(zip);
        
        // session that holds latest restaurant, in case user wants to save the plan
        session.setAttribute("latestRestaurant", restaurant);
        
        // add models to view model
        model.addAttribute("restaurant",restaurant);   //adding the Restaurant object to a model which is accessible in the HTML pages.
        model.addAttribute("weather",weather);
        
        return "result";
    }
}
