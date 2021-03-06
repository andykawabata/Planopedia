package com.team.planopedia.modelsAndServices.restaurant.BasicInfo;

import java.util.List;

/**
 * Class that will contain the basic information of a plan, including name, location,
 * contact information, as well as price and user ratings. When constructing a restaurant object,
 * this is the first piece. The information in this object will be used for the remaining api calls
 * such as Weather, Directions and Reviews.
 * 
 */
public class BasicInfo {

    //Add some kind of template (StringBuilder) to keep addresses/Phone Numbers consistent?
    //https://www.baeldung.com/java-regex-validate-phone-numbers

    private String locationName;
    private String fullAddress;
    private String zipCode;
    private String phoneNumber;
    private String priceRating;
    private String starRating;
    private String restaurantId;
    private String imageUrl;
    private List<String> categoryNames;

    public BasicInfo(){

    }

    /**
     * Generic Constructor for BasicInfo Class.
     * @param locationName String containing plan's location.
     * @param fullAddress String containing plan's address (Format: "...").
     * @param phoneNumber String containing plan's phone number (Format: "###-###-####").
     * @param priceRating String containing plan's price rating (Format: "$", "$$", or "$$$").
     * @param starRating String containing plan's overall rating (Format: "#/#").
     */
    public BasicInfo(String locationName, String fullAddress, String zipCode, String phoneNumber, String priceRating, String starRating, String restaurantId, String imageUrl, List<String> categoryNames) {
        this.locationName = locationName;
        this.fullAddress = fullAddress;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.priceRating = priceRating;
        this.starRating = starRating;
        this.restaurantId = restaurantId;
        this.imageUrl = imageUrl;
        this.categoryNames = categoryNames;
    }
    /*-----------------Getters------------------------*/

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }
    
    
    /**
     * Basic getter for the name of the plan's location.
     * @return String containing plan's location.
     */
    
    public String getZipCode() {
        return zipCode;
    }

    public String getLocationName() {
        return locationName;
    }


    /**
     * Basic getter for the address of the plan's location.
     * @return String containing plan's address (Format: "...").
     */
    public String getFullAddress() {
        return fullAddress;
    }


    /**
     * Basic getter for the phone number of the plan's location.
     * @return String containing plan's phone number (Format: "###-###-####").
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Basic getter for the price point of the plan.
     * @return String containing plan's price rating (Format: "$", "$$", or "$$$").
     */
    public String getPriceRating() {
        return priceRating;
    }


    /**
     * Basic getter for the review rating for the plan location.
     * @return String containing plan's overall rating (Format: "#/#").
     */
    public String getStarRating() {
        return starRating;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    
    
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    /*-----------------Setters------------------------*/
    //Do we need these other than for testing? They should only be changed by the api or DB calls.
    /**
     * Basic setter for plan's location name.
     * @param locationName
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }


    /**
     * Basic setter for plan's location address.
     * @param fullAddress
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }


    /**
     * Basic setter for the phone number of a plan.
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Basic setter for the price point rating of a plan.
     * @param priceRating
     */
    public void setPriceRating(String priceRating) {
        this.priceRating = priceRating;
    }


    /**
     * Basic setter for the overal rating of a plan's location.
     * @param starRating
     */
    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    @Override
    public String toString() {
        return "BasicInfo{" + "locationName=" + locationName + ", fullAddress=" + fullAddress + ", phoneNumber=" + phoneNumber + ", priceRating=" + priceRating + ", starRating=" + starRating + '}';
    }
    
    
}
