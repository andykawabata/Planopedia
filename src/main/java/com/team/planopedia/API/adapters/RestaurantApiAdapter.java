package com.team.planopedia.API.adapters;

import com.team.planopedia.API.interfaces.APIFoodInterface;
import com.team.planopedia.API.translators.YelpAPI;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author andrewkawabata
 */
public class RestaurantApiAdapter implements APIFoodInterface {

    public static final APIFoodInterface restaurantTranslator = new YelpAPI();
    
    @Override
    public ArrayList<Map<String, String>> getRestaurants(String _term, String _city, int _limit) {
        return restaurantTranslator.getRestaurants(_term, _city, _limit);
    }

    @Override
    public ArrayList<Map<String, String>> getRestaurantSearchByName(String _restaurantName, String _city, int _limit) {
        return restaurantTranslator.getRestaurantSearchByName(_restaurantName, _city, _limit);
    }
    
    
    
}
