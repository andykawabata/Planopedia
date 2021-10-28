package com.team.planopedia.modelsAndServices.restaurant;

import com.team.planopedia.dao.User;
import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfo;
import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfoService;
import com.team.planopedia.modelsAndServices.restaurant.Directions.Directions;
import com.team.planopedia.modelsAndServices.restaurant.Reviews.Reviews;
import com.team.planopedia.modelsAndServices.restaurant.Reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    
    @Autowired
    BasicInfoService basicInfoService;
    @Autowired
    ReviewsService reviewsService;
    
    public Restaurant generateRestaurant(String city, String zip, String cuisine, int numPeople, User user){
        
        BasicInfo basicInfo = basicInfoService.chooseSingleRestaurant(city, zip, cuisine, 0, user);
        Reviews reviews = reviewsService.getReviewsFromBasicInfo(basicInfo);
        Directions directions = null;
        
        return new Restaurant(basicInfo, reviews, directions);
    }
}
