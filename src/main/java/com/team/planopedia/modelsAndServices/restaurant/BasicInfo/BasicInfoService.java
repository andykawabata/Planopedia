package com.team.planopedia.modelsAndServices.restaurant.BasicInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BasicInfoService {

    //yelpAPIAdapter apiAdapter = new yelpAPIAdapter;

    
    public BasicInfo chooseSingleRestaurant(String city, String zip, String cuisine, int numPeople){

        //apiAdapter.getBasicInfo(Need API classes for this);
        //Map<String, String> BasicInfoMap = apiAdapter.getBasicInfo(Need API classes for this);
        
        
        List<Map<String, String>> potentialRestaurants = this.fakeApiRestaurantCall(city, zip, cuisine);
        
        Map<String, String> chosenRestaurant = this.pickRestaurantFromList(potentialRestaurants);
        
        String locationName = chosenRestaurant.get("name");
        String fullAddress = chosenRestaurant.get("address");
        String phoneNumber = chosenRestaurant.get("phone");
        String priceRating = chosenRestaurant.get("price");
        String starRating = chosenRestaurant.get("rating");

        return new BasicInfo(locationName, fullAddress, phoneNumber, priceRating, starRating);

    }
    
    private Map<String, String> pickRestaurantFromList(List<Map<String, String>> potentialRestaurants){
        
        return potentialRestaurants.get(0);
    }
    
    
    
    
    
    
    
    
    
    ////////////////////// DUMMY DATA ////////////////////////////////
    
    
    public List<Map<String, String>> fakeApiRestaurantCall(String city, String zip, String cuisine){
        
        Map<String, String> dummyRestaurant1 = new HashMap<>();
        dummyRestaurant1.put("name", "Arby's");
        dummyRestaurant1.put("address", "123 Road Street");
        dummyRestaurant1.put("phone", "555-5555");
        dummyRestaurant1.put("price", "$$");
        dummyRestaurant1.put("rating", "4/5");
        
        Map<String, String> dummyRestaurant2 = new HashMap<>();
        dummyRestaurant2.put("name", "Printworks");
        dummyRestaurant2.put("address", "32 Hill Road");
        dummyRestaurant2.put("phone", "555-5555");
        dummyRestaurant2.put("price", "$$");
        dummyRestaurant2.put("rating", "4/5");
        
         Map<String, String> dummyRestaurant3 = new HashMap<>();
        dummyRestaurant3.put("name", "Taco's Bell");
        dummyRestaurant3.put("address", "2 Cheese Street");
        dummyRestaurant3.put("phone", "555-5555");
        dummyRestaurant3.put("price", "$");
        dummyRestaurant3.put("rating", "3/5");
        
        List<Map<String, String>> dummyRestaurantList = new ArrayList<Map<String, String>>();
        dummyRestaurantList.add(dummyRestaurant1);
        dummyRestaurantList.add(dummyRestaurant2);
        dummyRestaurantList.add(dummyRestaurant3);
        
        return dummyRestaurantList;
        
    }
    
    
}
