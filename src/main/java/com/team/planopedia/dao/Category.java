package com.team.planopedia.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category {
    @Id
    private String categoryName;


    @ManyToOne
    @JoinColumn(name = "restaurantInfoId")
    RestaurantInfo restaurantInfo;

    public Category() {
    }

    public Category(String categoryName, RestaurantInfo restaurantInfo) {
        this.categoryName = categoryName;
        this.restaurantInfo = restaurantInfo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }
}
