package com.team.planopedia.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    private Long userId;
    private Long restaurantInfoId;

    public Plan() {
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantInfoId() {
        return restaurantInfoId;
    }

    public void setRestaurantInfoId(Long restaurantInfoId) {
        this.restaurantInfoId = restaurantInfoId;
    }
}
