package com.team.planopedia.dao;

import javax.persistence.*;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;


    private Long userId;


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

    public Plan(Long planId, Long userId, RestaurantInfo restaurantInfo) {
        this.planId = planId;
        this.userId = userId;
        this.restaurantInfo = restaurantInfo;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
