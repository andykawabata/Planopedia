package com.team.planopedia.modelsAndServices.restaurant.BasicInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoService {
    
    // Called from Restaurant Service
    public BasicInfo chooseSingleRestaurant(String city, String zip, String cuisine, int numPeople){
        
        //Call API and get list of potential restaurants
        List<Map<String, String>> potentialRestaurants = this.getRestaurantList(city, zip, cuisine);
        
        //Choose Restaurant from list
        Map<String, String> chosenRestaurant = this.pickRestaurantFromList(potentialRestaurants);
        
        String name = chosenRestaurant.get("name");
        String address = chosenRestaurant.get("address");
        String rating = chosenRestaurant.get("rating");
        String phone = chosenRestaurant.get("phone");
        
        return new BasicInfo(name, address, phone, rating);
    }
    
    // method that calls API
    private List<Map<String, String>> getRestaurantList(String city, String zip, String cuisine){
        
        // put in fake data until API is working
        Map<String, String> dummyRestaurant = new HashMap<>();
        dummyRestaurant.put("name", "Arby's");
        dummyRestaurant.put("address", "123 Road Street");
        dummyRestaurant.put("phone", "555-5555");
        dummyRestaurant.put("rating", "4/5");
        
        List<Map<String, String>> dummyRestaurantList = new ArrayList<Map<String, String>>();
        dummyRestaurantList.add(dummyRestaurant);
        
        return dummyRestaurantList;
        
    }
    
    // method that picks restaurant from list, currently implemented to just pick the first one
    private Map<String, String> pickRestaurantFromList(List<Map<String, String>> potentialRestaurants){
        
        return potentialRestaurants.get(0);
    }
    
}
