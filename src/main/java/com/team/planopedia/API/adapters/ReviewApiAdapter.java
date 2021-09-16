/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team.planopedia.API.adapters;

import com.team.planopedia.API.interfaces.APIReviewInterface;
import com.team.planopedia.API.translators.YelpAPI;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author andrewkawabata
 */
public class ReviewApiAdapter implements APIReviewInterface{

    public static final APIReviewInterface reviewTranslator = new YelpAPI();

    @Override
    public ArrayList<Map<String, String>> getRestaurantReviews(String _restaurantID) {
        return reviewTranslator.getRestaurantReviews(_restaurantID);
    }
    
    
    
    
}

