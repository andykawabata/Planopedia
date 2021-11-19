package com.team.planopedia.modelsAndServices.restaurant.Reviews;

import com.team.planopedia.API.adapters.RestaurantApiAdapter;
import com.team.planopedia.API.adapters.ReviewApiAdapter;
import com.team.planopedia.modelsAndServices.restaurant.BasicInfo.BasicInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Service that pulls reviews and sorts them
 * @author andrewkawabata
 */
@Service
public class ReviewsService {
    
    public Reviews getReviewsFromBasicInfo(BasicInfo basicInfo){
        
        String restaurantId = basicInfo.getRestaurantId();
        
        ReviewApiAdapter api = new ReviewApiAdapter();
        
        //get reviews based on restaurant ID
        List<Map<String, String>> reviewList = api.getRestaurantReviews(restaurantId);

        Map<String, SingleReview> chosenReviews = this.chooseGoodAndBadReviews(reviewList);
        
        SingleReview goodReview = chosenReviews.get("goodReview");
        SingleReview badReview = chosenReviews.get("badReview");
        
        return new Reviews(goodReview, badReview);
        
    }
    
    // picks 2 reviews from list. One good, one bad.
    private Map<String, SingleReview> chooseGoodAndBadReviews(List<Map<String, String>> reviewList){
        
        // create comparator to sort reviews
        Comparator<Map<String, String>> mapComparator;
        mapComparator = new Comparator<Map<String, String>>() {
            public int compare(Map<String, String> m1, Map<String, String> m2) {
                return m1.get("rating").compareTo(m2.get("rating"));
            }
        };
        
        Collections.sort(reviewList, mapComparator);
        
        // the worst and best reviews are at either end of the sorted array
        Map<String, String> review1 =  reviewList.get(0);
        Map<String, String> review2 =  reviewList.get(reviewList.size()-1);
        
        SingleReview badReview = new SingleReview(review1.get("rating"), review1.get("text"), review1.get("name"));
        SingleReview goodReview = new SingleReview(review2.get("rating"), review2.get("text"), review2.get("name"));
     
        Map<String, SingleReview> reviewMap = new HashMap<>();
        reviewMap.put("goodReview", goodReview);
        reviewMap.put("badReview", badReview);
        
        return reviewMap;
    }
}