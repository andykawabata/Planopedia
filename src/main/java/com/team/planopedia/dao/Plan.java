package com.team.planopedia.dao;

import javax.persistence.*;

@Entity
@Table(name = "Plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    private Long userId;
    //private Long restaurantInfoId;

    /**
     * one to one relation,
     * foreign key in the plan table that maps to the primary key in the restaurantInfo table.
     */
    @OneToOne
    @JoinColumn(name = "restaurantInfoId")
    private RestaurantInfo restaurantInfo;

    /**
     * Constructors, getters and setters
     */
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

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }
}
