package com.team.planopedia.modelsAndServices.weather;

import com.team.planopedia.API.translators.OpenWeatherMapAPI;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    
    //get weather by zip and country
    //getWeather(String _zipCode, String _country)
    public Weather getWeatherFromZip(String zip){
        
        OpenWeatherMapAPI api = new OpenWeatherMapAPI();
        Map<String, String> weatherItems = api.getWeather(zip, "US");
        
        String temp = weatherItems.get("temperatureInFahrenheit");
        String description = weatherItems.get("weatherDescription");
        String iconURL = weatherItems.get("weatherImageURL");
      
        return new Weather(temp, description, iconURL);
    }
    
}

