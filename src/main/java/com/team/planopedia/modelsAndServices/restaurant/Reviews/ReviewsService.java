package com.team.planopedia.modelsAndServices.restaurant.Reviews;

import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {
    
    public Reviews getReviewsFromBasicInfo(BasicInfo basicInfo){
        
        String restaurantName = basicInfo.getName();
        
        //call to API
        List<Map<String, String>> reviewList = this.getReviewsByRestaurantName(restaurantName);
        
        Map<String, SingleReview> chosenReviews = this.chooseGoodAndBadReviews(reviewList);
        
        SingleReview goodReview = chosenReviews.get("goodReview");
        SingleReview badReview = chosenReviews.get("badReview");
        
        return new Reviews(goodReview, badReview);
        
    }
    
    //Calls API
    private List<Map<String, String>> getReviewsByRestaurantName(String restaurantName){
        
        Map<String, String> dummyReview1 = new HashMap<>();
        dummyReview1.put("rating", "1");
        dummyReview1.put("text", "This place is bad!");
        dummyReview1.put("date", "12-10-20");
        
        Map<String, String> dummyReview2 = new HashMap<>();
        dummyReview2.put("rating", "5");
        dummyReview2.put("text", "This place is good!");
        dummyReview2.put("date", "2-9-20");
        
        List<Map<String, String>> dummyReviewList = new ArrayList<Map<String, String>>();
        dummyReviewList.add(dummyReview1);
        dummyReviewList.add(dummyReview2);
        
        return dummyReviewList;
    }
    
    // picks 2 reviews from list. One good, one bad.
    private Map<String, SingleReview> chooseGoodAndBadReviews(List<Map<String, String>> reviewList){
        
        Map<String, String> review1 =  reviewList.get(0);
        Map<String, String> review2 =  reviewList.get(1);
        
        SingleReview badReview = new SingleReview(review1.get("rating"), review1.get("text"), review1.get("date"));
        SingleReview goodReview = new SingleReview(review2.get("rating"), review2.get("text"), review2.get("date"));
     
        Map<String, SingleReview> reviewMap = new HashMap<>();
        reviewMap.put("goodReview", goodReview);
        reviewMap.put("badReview", badReview);
        
        return reviewMap;
    }
}
