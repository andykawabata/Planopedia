package com.team.planopedia.modelsAndServices.restaurant.Directions;

import com.team.planopedia.API.translators.googleMapsAPI;
import org.springframework.stereotype.Service;

@Service
public class DirectionsService {
    
    public Directions getMapUrl(String restaurantName, String zip){
        
        googleMapsAPI api = new googleMapsAPI();
        
        return new Directions(api.getMapString(zip, buildPlaceString(restaurantName)));
    }
    
    private String buildPlaceString(String restaurantName){
        
        String restaurantNameNoSpaces = restaurantName.replaceAll(" ", "_").toLowerCase();
        return restaurantNameNoSpaces;
    }
    
}