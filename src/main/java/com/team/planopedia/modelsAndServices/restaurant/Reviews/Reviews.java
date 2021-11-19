package com.team.planopedia.modelsAndServices.restaurant.Reviews;

/**
 * This object holds all data for the review section on the plan page.
 * @author andrewkawabata
 */
public class Reviews {
    
    private SingleReview goodReview;
    private SingleReview badReview;

    public Reviews(SingleReview goodReview, SingleReview badReview) {
        this.goodReview = goodReview;
        this.badReview = badReview;
    }

    public SingleReview getGoodReview() {
        return goodReview;
    }

    public SingleReview getBadReview() {
        return badReview;
    }
    
    
    
}