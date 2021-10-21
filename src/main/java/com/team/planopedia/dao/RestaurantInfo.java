package com.team.planopedia.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "restaurantInfo")
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantInfoId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantZip;

    @OneToOne(mappedBy = "restaurantInfo")
    private Plan plan;


    //resturantInfo can have many categories, to get all categories for the resturantInfo
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="restaurantInfoId")
    private List<Category> categories = new ArrayList<>();

    /**
     * Constructors, getters and setters
     */
    public RestaurantInfo() {

    }


    public RestaurantInfo(Long restaurantInfoId, String restaurantName, String restaurantAddress, String restaurantZip, List<Category> categories) {
        this.restaurantInfoId = restaurantInfoId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantZip = restaurantZip;
        this.categories = categories;
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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * get all Categories that the restaurant have
     * @return
     */
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
