package com.team.planopedia.API.interfaces;

/**
 * Interface meant to create a standard calling for the application
 * Last updated August 31, 2021
 * @Author Nimra Sami
 * @Author Arturo Serdan
 */

public interface APIFoodInterface {
    /**
     * @param _term The term used to look for a certain type of food
     * @param _city The city given by the user
     * @param _limit The
     * @return Restaurant data
     */
    String [] getRestaurants(String _term, String _city, int _limit);
}
