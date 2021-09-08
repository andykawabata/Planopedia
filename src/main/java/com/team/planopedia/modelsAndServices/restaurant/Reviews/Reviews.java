package com.team.planopedia.modelsAndServices.restaurant.Reviews;


public class Reviews {
    
    private SingleReview goodReview;
    private SingleReview badReview;

    public Reviews(SingleReview goodReview, SingleReview badReview) {
        this.goodReview = goodReview;
        this.badReview = badReview;
    }
    
}