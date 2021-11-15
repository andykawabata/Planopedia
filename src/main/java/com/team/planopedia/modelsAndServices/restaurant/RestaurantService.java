package com.team.planopedia.modelsAndServices.restaurant;

import com.team.planopedia.dao.User;
import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfo;
import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfoService;
import com.team.planopedia.modelsAndServices.restaurant.Directions.Directions;
import com.team.planopedia.modelsAndServices.restaurant.Directions.DirectionsService;
import com.team.planopedia.modelsAndServices.restaurant.Reviews.Reviews;
import com.team.planopedia.modelsAndServices.restaurant.Reviews.ReviewsService;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    
    @Autowired
    BasicInfoService basicInfoService;
    @Autowired
    ReviewsService reviewsService;
    @Autowired
    DirectionsService directionsService;
    
    public Restaurant generateRestaurant(String city, String zip, String cuisine, int numPeople, User user){
        
        BasicInfo basicInfo = basicInfoService.chooseSingleRestaurant(city, zip, cuisine, 0, user);
        if(basicInfo == null) return null;
        Reviews reviews = reviewsService.getReviewsFromBasicInfo(basicInfo);
        Directions directions = directionsService.getMapUrl(basicInfo.getLocationName(), basicInfo.getZipCode() );
        System.out.println(basicInfo.getCategoryNames().get(0));
        return new Restaurant(basicInfo, reviews, directions);
    }
    
    public Restaurant getRestaurantByName(String restaurantName, String zip){
        
        
        BasicInfo basicInfo = basicInfoService.getRestaurantByName(restaurantName, zip);
        Reviews reviews = reviewsService.getReviewsFromBasicInfo(basicInfo);
        Directions directions = directionsService.getMapUrl(basicInfo.getLocationName(), basicInfo.getZipCode() );
        System.out.println(basicInfo.getCategoryNames().get(0));
        return new Restaurant(basicInfo, reviews, directions);
    }
}
