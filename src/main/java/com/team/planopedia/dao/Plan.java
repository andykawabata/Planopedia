package com.team.planopedia.dao;

import javax.persistence.*;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    /**
     * one to one relation,
     * foreign key in the plan table that maps to the primary key in the restaurantInfo table.
     */
    @OneToOne
    @JoinColumn(name = "restaurantInfoId")
    private RestaurantInfo restaurantInfo;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    /**
     * Constructors, getters and setters
     */
    public Plan() {
    }

    public Plan(Long planId, RestaurantInfo restaurantInfo, User user) {
        this.planId = planId;
        this.restaurantInfo = restaurantInfo;
        this.user = user;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }
}
