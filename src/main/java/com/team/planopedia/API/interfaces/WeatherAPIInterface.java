package com.team.planopedia.API.interfaces;



/**
 * Grabs weather from the weather API
 *
 * @author Arturo
 * Last updated 4/1/2020
 */
public interface WeatherAPIInterface {

    /**
     * Grabs the weather from the weather API
     *
     * @param _zipCode
     * @param _countryCode
     * @return an Array String with the weather information from the API
     */
    String [] getWeather(String _zipCode, String _countryCode);
}
