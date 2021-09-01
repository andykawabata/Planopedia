package com.team.planopedia.modelsAndServices.restaurant;

import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfo;
import com.team.planopedia.modelsAndServices.restaurant.Directions.Directions;
import com.team.planopedia.modelsAndServices.restaurant.Reviews.Reviews;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    
    public Restaurant generateRestaurant(/*paramerters*/){
        
        BasicInfo basicInfo = null;
        Reviews reviews = null;
        Directions directions = null;
        
        return new Restaurant(basicInfo, reviews, directions);
    }
}
