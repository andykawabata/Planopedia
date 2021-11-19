package com.team.planopedia.modelsAndServices.restaurant.BasicInfo;

import com.team.planopedia.API.adapters.RestaurantApiAdapter;
import com.team.planopedia.ChoiceAlgorithm.ChoiceMaker;
import com.team.planopedia.dao.Plan;
import com.team.planopedia.dao.RestaurantInfo;
import com.team.planopedia.dao.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service that calls the Restaurant api layer to either generate a restaurant or look up
 * a restaurant, and store the data in a BasicInfo object. It also calls the choice algorithm system.
 * 
 * @author andrewkawabata
 */
@Service
public class BasicInfoService {

    /**
     * Pulls multiple restaurants from api, and selects one to return
     * @param city
     * @param zip
     * @param cuisine
     * @param numPeople
     * @param user
     * @return 
     */
    public BasicInfo chooseSingleRestaurant(String city, String zip, String cuisine, int numPeople, User user){
        
        RestaurantApiAdapter api = new RestaurantApiAdapter();
        int numRestaurants = 40; // max entries pulled from api
        
        ArrayList<Map<String, String>> potentialRestaurants = api.getRestaurants(cuisine, city, numRestaurants);
        Map<String, String> chosenRestaurant;
        
        //if user not logged in, choose restaurant randomly
        if(user == null){
             chosenRestaurant = pickRandomRestaurantFromList(potentialRestaurants);
        }else{
            // if user logged in, use choiceMaker algorithm
            ChoiceMaker choiceMaker = new ChoiceMaker();
            chosenRestaurant = choiceMaker.makeDecision(user, potentialRestaurants);
            // if choiceMaker wasn't able to return anything, fallback on random
            if(chosenRestaurant == null){
                chosenRestaurant = pickRandomRestaurantFromList(potentialRestaurants);
            }
        }
        
        if(chosenRestaurant == null) return null;
        
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
    
    /**
     * Method that takes zip and restaurant Name and pulls first matching entry from api
     * @param restaurantName
     * @param zip
     * @return 
     */
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
    
    // picks random restaurant from list
    public static Map<String, String> pickRandomRestaurantFromList(List<Map<String, String>> potentialRestaurants){
        
        int n = potentialRestaurants.size();
        if(n == 0){ return null; }
        int randIndex = (int) ((Math.random() *  (n)));
        return potentialRestaurants.get(randIndex);
    }
    
    //puts category names into list
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
