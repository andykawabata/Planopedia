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
    
    @GetMapping("/result")
    public String result(Model model,
                         @RequestParam("city") String city,                 //This variables are obtained via GET request from form page.
                         @RequestParam("zip") String zip,
                         @RequestParam("cuisine") String cuisine,
                         @RequestParam("numPeople") String numPeople, HttpSession session){
    
        // user variable is set to User object if session exisists, else it is null
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
        User user = null;
        if(userMap != null)
            user = AuthController.convertUserMapToObject(userMap);
            
        Restaurant restaurant = restaurantService.generateRestaurant(city, zip, cuisine, Integer.parseInt(numPeople), user);
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
    public String showGuestPlanForm(HttpSession session) {
        
      
        
        
        
        return "index";
    } 
    
    //@PostMapping("/save-plan")
    @GetMapping("/save-plan")
    public String savePlan(HttpSession session) {
        
//        User user = new User();
//        user.setGoogleEmail("e@mail.com");
//        user.setUserName("user1");
//        userRepository.save(user);

        User user = userRepository.findByUserId((Long) (long) 1);

        Plan plan = new Plan();
        RestaurantInfo restInfo = new RestaurantInfo();
        restInfo.setRestaurantAddress("123 Street");
        restInfo.setRestaurantName("Pasta Town");
        restInfo.setRestaurantZip("12345");
        restInfo.setPlan(plan);
        
        List<Category> clist = new ArrayList<>();
        clist.add(new Category("Pasta"));
        restInfo.setCategories(clist);
        
        plan.setRestaurantInfo(restInfo);
        plan.setUser(user);
        
        planRepository.save(plan);
        
        return "index";
    } 
    
    @GetMapping("/rate-plan")
    public String ratePlan(HttpSession session) {
      
        Long planId = (long) 4;
        int rating = 3;
        
        Plan plan = planRepository.findByPlanId(planId);
        plan.setRating(3);
        planRepository.save(plan);
        
        return "index";
    } 
    
    @GetMapping("/test")
    public String test() {
        //test to get users rating algorithms
        //User user = userRepository.findByUserId((Long) (long) 2);
        //List<RatingAlgorithm> alist = user.getRatingAlgorithms();
        //System.out.println(alist.get(0).getCategory().getCategoryName());
        return "index";
    } 
    
    
    
}
