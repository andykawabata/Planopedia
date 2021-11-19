package com.team.planopedia.controllers;


import com.team.planopedia.dao.Category;
import com.team.planopedia.dao.Plan;
import com.team.planopedia.dao.User;
import com.team.planopedia.dao.RestaurantInfo;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller handling all user functions.
 * @author andrewkawabata
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {
   
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    WeatherService weatherService;
    @Autowired
    PlanRepository planRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/test")
    @ResponseBody
    public String CreateNewUser(){
        User user = new User();
        user.setUserName("");
        user.setGoogleEmail("");
        userRepository.save(user);
        return "save: "+user;

    }
    
    @GetMapping(path = "/profile")
    public String showProfile(){
        return "index";
    }
    
    @GetMapping(path = "/saved-plans")
    public String showAllSavedPlans(HttpSession session, Model model){
        
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
        String userEmail = (String) userMap.get("googleEmail");
        User user = userRepository.findByGoogleEmail(userEmail);
        List<Plan> plans = user.getPlans();
        
        model.addAttribute("plans", (List<Plan>) plans);
        
        return "saved-plans";
    }
    
    @GetMapping(path = "/single-plan")
    public String showSingleSavedPlan(@RequestParam("planId") String planId, Model model, HttpSession session){
        
       Long planIdLong = Long.valueOf(planId);
       Plan plan = planRepository.findByPlanId(planIdLong);
       RestaurantInfo restInfo = plan.getRestaurantInfo();
       String restaurantName = restInfo.getRestaurantName();
       String zip = restInfo.getRestaurantZip();
       
        Restaurant restaurant = restaurantService.getRestaurantByName(restaurantName, zip);
        Weather weather = weatherService.getWeatherFromZip(zip);
        
        session.setAttribute("latestRestaurant", restaurant);
        
        model.addAttribute("restaurant",restaurant);   //adding the Restaurant object to a model which is accessible in the HTML pages.
        model.addAttribute("weather",weather);
        model.addAttribute("planId", planId);
        model.addAttribute("plan", plan);
       
        return "single-plan";
    }
    
    @PostMapping(path = "/save-plan")
    public String savePlan(HttpSession session){
        
        Restaurant r = (Restaurant) session.getAttribute("latestRestaurant");
        System.out.println(r);
     
        Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
        String userEmail = (String) userMap.get("googleEmail");
        User user = userRepository.findByGoogleEmail(userEmail);

        Plan plan = new Plan();
        RestaurantInfo restInfo = new RestaurantInfo();
        restInfo.setRestaurantAddress(r.getBasicInfo().getFullAddress());
        restInfo.setRestaurantName(r.getBasicInfo().getLocationName());
        restInfo.setRestaurantZip(r.getBasicInfo().getZipCode());
        restInfo.setPlan(plan);
        
        List<String> catStrings = r.getBasicInfo().getCategoryNames();
        List<Category> clist = new ArrayList<>();
        for(String cat : catStrings){
            clist.add(new Category(cat));
        }
        restInfo.setCategories(clist);
        
        plan.setRestaurantInfo(restInfo);
        plan.setUser(user);
        
        planRepository.save(plan);
        
        return "redirect:/user/saved-plans";
    }
    
    @PostMapping(path = "/rate-plan")
    public String ratePlan(@RequestParam("planId") String planId, @RequestParam("rating") String rating){
        
        Long planIdLong = Long.valueOf(planId);
        Plan plan = planRepository.findByPlanId(planIdLong);
        plan.setRating(Integer.valueOf(rating));
        planRepository.save(plan);
        
        return "redirect:/user/saved-plans";
    }
}
