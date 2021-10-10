package com.team.planopedia.modelsAndServices.restaurant.Reviews;

public class SingleReview {
    
    private String rating;
    private String text;
    private String time;

    public SingleReview(String rating, String text, String time) {
        this.rating = rating;
        this.text = text;
        this.time = time;
    }

    public String getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }
    
    
    
}