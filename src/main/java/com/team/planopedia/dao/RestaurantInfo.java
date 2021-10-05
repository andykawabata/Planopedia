package com.team.planopedia.dao;

import javax.persistence.*;

@Entity
@Table (name = "restaurantInfo")
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantInfoId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantZip;


    public RestaurantInfo() {

    }


    public RestaurantInfo(String restaurantName, String restaurantAddress, String restaurantZip) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantZip = restaurantZip;
    }

    public Long getRestaurantInfoId() {
        return restaurantInfoId;
    }

    public void setRestaurantInfoId(Long restaurantInfoId) {
        this.restaurantInfoId = restaurantInfoId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantZip() {
        return restaurantZip;
    }

    public void setRestaurantZip(String restaurantZip) {
        this.restaurantZip = restaurantZip;
    }
}
