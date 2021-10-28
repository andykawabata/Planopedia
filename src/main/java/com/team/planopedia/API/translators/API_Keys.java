package com.team.planopedia.API.translators;

/**
 * This class is meant to store the API keys used for Planopedia Web Application
 * Last updated August 31, 2021
 *
 * @Author Nimra Sami
 * @Author Arturo Serdan
 */
public class API_Keys {

    /**
     * Method meant to store the Yelp API key for food information
     *
     * @returns an API key for the Yelp API
     */
    public static String yelp() {
        String api_key = "VHIDbflsCUhZRkYvtALzuaFY773pZSripJ2L8CarE44iBFqbNn3eexl_Fay4gcBK14Jt7HUarpGgXjskL4xVeylC6P9HijQOaAKuZaTWEEeZJEqXGXHb0P8Phx0tYXYx";

        return api_key;
    }

    /**
     * Method used to store the weather API (openweathermap) key
     *
     * @return API key for the weather API
     */
    public static String openWeatherMapAPI() {
        String api_key = "85cbeb5a131f1fa93c91d16c8ecd96fe";

        return api_key;
    }

    /**
     * Method meant to store the google API key
     *
     * @return API key for the google API
     */
    public static String googleAPI() {
        String api_key = "AIzaSyDppa2_6pPP1zFlH2sYz1ckpjXaNL0bWYQ";

        return api_key;
    }
}
