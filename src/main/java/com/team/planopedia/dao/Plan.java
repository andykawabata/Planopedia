package com.team.planopedia.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    private int rating;

    /**
     * one to one relation, foreign key in the plan table that maps to the
     * primary key in the restaurantInfo table.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurantInfoId")
    @JsonBackReference
    private RestaurantInfo restaurantInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    @JsonBackReference
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

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
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
