package com.team.planopedia.API.interfaces;

/**
 * Grabs weather from the weather API
 *
 * @author Nimra Sami
 * @author Arturo Serdan
 * Last updated October 13, 2021
 */
import java.util.Map;
public interface WeatherAPIInterface {

    /**
     * Grabs the weather from the weather API
     *
     * @param _zipCode
     * @param _countryCode
     * @return an HashMap with the weather information from the API
     */
    Map<String, String> getWeather(String _zipCode, String _countryCode);
}

