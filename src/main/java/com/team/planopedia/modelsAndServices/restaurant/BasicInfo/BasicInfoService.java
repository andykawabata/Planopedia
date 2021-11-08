package com.team.planopedia.modelsAndServices.restaurant.BasicInfo;

import com.team.planopedia.API.adapters.RestaurantApiAdapter;
import com.team.planopedia.dao.Plan;
import com.team.planopedia.dao.RestaurantInfo;
import com.team.planopedia.dao.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BasicInfoService {

    //yelpAPIAdapter apiAdapter = new yelpAPIAdapter;

    
    public BasicInfo chooseSingleRestaurant(String city, String zip, String cuisine, int numPeople, User user){
        
        RestaurantApiAdapter api = new RestaurantApiAdapter();
        int numRestaurants = 20;
        
        List<Map<String, String>> potentialRestaurants = api.getRestaurants(cuisine, city, numRestaurants);
        
        Map<String, String> chosenRestaurant = this.pickRandomRestaurantFromList(potentialRestaurants);
        
        String locationName = chosenRestaurant.get("restaurantName");
        String fullAddress = chosenRestaurant.get("address");
        String zipCode = chosenRestaurant.get("zipCode");
        String phoneNumber = chosenRestaurant.get("phoneNumber");
        String priceRating = chosenRestaurant.get("price");
        String starRating = chosenRestaurant.get("rating");
        String restaurantId = chosenRestaurant.get("restaurantID");
        String imageUrl = chosenRestaurant.get("image_url");
        List<String> categoryNames = parseCategoryNames(chosenRestaurant.get("categories"));

        return new BasicInfo(locationName, fullAddress, zipCode, phoneNumber, priceRating, starRating, restaurantId, imageUrl, categoryNames);

    }
    
    public BasicInfo getRestaurantByName(String restaurantName, String zip){
        
       RestaurantApiAdapter api = new RestaurantApiAdapter();
        ArrayList<Map<String, String>> rList = api.getRestaurantSearchByName(restaurantName, zip, 1);
        Map<String, String> chosenRestaurant = rList.get(0);
        
        String locationName = chosenRestaurant.get("restaurantName");
        String fullAddress = chosenRestaurant.get("address");
        String zipCode = chosenRestaurant.get("zipCode");
        String phoneNumber = chosenRestaurant.get("phoneNumber");
        String priceRating = chosenRestaurant.get("price");
        String starRating = chosenRestaurant.get("rating");
        String restaurantId = chosenRestaurant.get("restaurantID");
        String imageUrl = chosenRestaurant.get("image_url");
        List<String> categoryNames = parseCategoryNames(chosenRestaurant.get("categories"));

        return new BasicInfo(locationName, fullAddress, zipCode, phoneNumber, priceRating, starRating, restaurantId, imageUrl, categoryNames);

    }
    
    private Map<String, String> pickRandomRestaurantFromList(List<Map<String, String>> potentialRestaurants){
        
        int n = potentialRestaurants.size();
        int randIndex = (int) ((Math.random() *  (n)));
        System.out.println(n);
        System.out.println(randIndex);
        return potentialRestaurants.get(randIndex);
    }
    
    private List<String> parseCategoryNames(String catString){
        List<String> catList = new ArrayList<>();
        String[] catArr = catString.split("'categorySeparator'");
        for(int i = 0; i < catArr.length; i++){
            catList.add(catArr[i]);
        }
        return catList;
        
    }
    
    public static User generateDummyUserWithPlans(){
        
        final int NUM_PLANS = 30;
        
        User user = new User((long) 1, "bobRoss", "e@mail.com");
        
        for(int i=0; i<NUM_PLANS; i++){
            Plan plan = new Plan();
            RestaurantInfo restaurantInfo = new RestaurantInfo();
            restaurantInfo.setRestaurantAddress("123 Street");
            restaurantInfo.setRestaurantName("Generic Restaturant Name");
            restaurantInfo.setRestaurantZip("12345");
            restaurantInfo.setPlan(plan);
        }
        
        
        
        return null;
    }
}
