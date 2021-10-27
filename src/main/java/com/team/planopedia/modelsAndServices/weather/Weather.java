package com.team.planopedia.modelsAndServices.weather;

public class Weather {
    
    private String temp;
    private String description;
    private String iconURL;
    //https://openweathermap.org/img/w/10d.png

    public Weather(String temp, String description, String iconURL) {
        this.temp = temp;
        this.description = description;
        this.iconURL = iconURL;
    }

    public String getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }

    public String getIconURL() {
        return iconURL;
    }
    
}
