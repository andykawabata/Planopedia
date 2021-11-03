/**
 * Interface meant to create a standard calling for the application
 * Last updated September 30, 2021
 *
 * @Author Nimra Sami
 * @Author Arturo Serdan
 */
package com.team.planopedia.API.interfaces;

import java.util.ArrayList;
import java.util.*;

public interface APIFoodInterface {

    /**
     * @param _term The term used to look for a certain type of food
     * @param _city The city given by the user
     * @param _limit The
     * @return Restaurant data
     */
    public ArrayList<Map<String, String>> getRestaurants(String _term, String _city, int _limit);

    /**
     * @param _restaurantName The term used to look for a certain type of food
     * @param _city The city given by the user
     * @param _limit The
     * @return Restaurant data
     */
    public ArrayList<Map<String, String>> getRestaurantSearchByName(String _restaurantName, String _city, int _limit);
    
}
