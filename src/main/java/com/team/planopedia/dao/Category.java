package com.team.planopedia.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;

    /*
    many to one relationship, restaurantInfoId as FK
     */
    @ManyToOne
    @JoinColumn(name = "restaurantInfoId")
    RestaurantInfo restaurantInfo;

    /**
     * Constructors, getters and setters
     */
    public Category() {
    }

    public Category(String categoryName, RestaurantInfo restaurantInfo) {
        this.categoryName = categoryName;
        this.restaurantInfo = restaurantInfo;
    }

    public Category(String catName) {
        this.categoryName = catName;
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
