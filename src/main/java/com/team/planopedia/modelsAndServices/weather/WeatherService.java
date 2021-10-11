package com.team.planopedia.modelsAndServices.weather;

import com.team.planopedia.API.translators.OpenWeatherMapAPI;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    
    //get weather by zip and country
    //getWeather(String _zipCode, String _country)
    public Weather getWeatherFromZip(String zip){
        
        OpenWeatherMapAPI api = new OpenWeatherMapAPI();
        String[] weatherItems = api.getWeather(zip, "US");
        
        String temp = weatherItems[0];
        String description = weatherItems[1];
        String iconURL = "https://openweathermap.org/img/w/10d.png";
      
        return new Weather(temp, description, iconURL);
    }
    
}

