package com.team.planopedia.API.interfaces;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author andrewkawabata
 */
public interface APIReviewInterface {
    
    public ArrayList<Map<String, String>> getRestaurantReviews(String _restaurantID);
}
