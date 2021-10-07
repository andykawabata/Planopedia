package com.team.planopedia.dao;



import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String googleEmail;

    //one user can have many plans, to get all plans for the user
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="userId")
    private ArrayList<Plan> plans = new ArrayList<>();

    /**
     * Constructors, getters and setters
     */
    public  User(){};

    public User(Long userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.googleEmail = email;
        //this.providerID = providerID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoogleEmail() {
        return googleEmail;
    }

    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }

    /**
     * getPlans()
     * get all the user plans
     * @return
     */
    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }
}


