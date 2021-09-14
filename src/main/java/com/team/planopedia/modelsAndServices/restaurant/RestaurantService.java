package com.team.planopedia.modelsAndServices.restaurant;

import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfo;
import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfoService;
import com.team.planopedia.modelsAndServices.restaurant.Directions.Directions;
import com.team.planopedia.modelsAndServices.restaurant.Reviews.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    
    @Autowired
    BasicInfoService basicInfoService;
    
    public Restaurant generateRestaurant(String city, String zip, String cuisine, int numPeople){
        
        BasicInfo basicInfo = basicInfoService.chooseSingleRestaurant(city, zip, cuisine, 0);
        Reviews reviews = null;
        Directions directions = null;
        
        return new Restaurant(basicInfo, reviews, directions);
    }
}
