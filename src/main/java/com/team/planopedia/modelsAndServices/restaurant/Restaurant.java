package com.team.planopedia.modelsAndServices.restaurant;

import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfo;
import com.team.planopedia.modelsAndServices.restaurant.Directions.Directions;
import com.team.planopedia.modelsAndServices.restaurant.Reviews.Reviews;


public class Restaurant {
    
    private BasicInfo basicInfo;
    private Reviews reviews;
    private Directions directions;

    public Restaurant(BasicInfo basicInfo, Reviews reviews, Directions directions) {
        this.basicInfo = basicInfo;
        this.reviews = reviews;
        this.directions = directions;
    }
    
    
    
}
