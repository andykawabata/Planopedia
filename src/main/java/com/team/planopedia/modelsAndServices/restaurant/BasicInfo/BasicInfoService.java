package com.team.planopedia.modelsAndServices.restaurant.BasicInfo;

import com.team.planopedia.API.adapters.RestaurantApiAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BasicInfoService {

    //yelpAPIAdapter apiAdapter = new yelpAPIAdapter;

    
    public BasicInfo chooseSingleRestaurant(String city, String zip, String cuisine, int numPeople){
        
        RestaurantApiAdapter api = new RestaurantApiAdapter();
        int numRestaurants = 20;
        
        List<Map<String, String>> potentialRestaurants = api.getRestaurants(cuisine, city, numRestaurants);
        
        Map<String, String> chosenRestaurant = this.pickRestaurantFromList(potentialRestaurants);
        
        String locationName = chosenRestaurant.get("restaurantName");
        String fullAddress = chosenRestaurant.get("address");
        String phoneNumber = chosenRestaurant.get("phoneNumber");
        String priceRating = chosenRestaurant.get("price");
        String starRating = chosenRestaurant.get("rating");
        String restaurantId = chosenRestaurant.get("restaurantID");

        
        return new BasicInfo(locationName, fullAddress, phoneNumber, priceRating, starRating, restaurantId);

    }
    
    private Map<String, String> pickRestaurantFromList(List<Map<String, String>> potentialRestaurants){
        
        return potentialRestaurants.get(0);
    }
    
}
