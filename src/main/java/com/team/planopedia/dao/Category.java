package com.team.planopedia.dao;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @Id
    private String categoryName;

    private Long restaurantInfoId;

    public Category() {
    }

    public Category(String categoryName, Long restaurantInfoId) {
        this.categoryName = categoryName;
        this.restaurantInfoId = restaurantInfoId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getRestaurantInfoId() {
        return restaurantInfoId;
    }

    public void setRestaurantInfoId(Long restaurantInfoId) {
        this.restaurantInfoId = restaurantInfoId;
    }
}
