package com.team.planopedia.modelsAndServices.restaurant.Directions;


public class Directions {
    
    // The string require for the 'src' attribute in the google map's iFrame
    private String mapUrl;

    public Directions(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }
    
    
    
}

