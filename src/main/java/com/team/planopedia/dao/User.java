package com.team.planopedia.dao;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    
    @Column(name="google_email", unique=true)
    private String googleEmail;

    //one user can have many plans, to get all plans for the user
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="userId")
    @JsonManagedReference
    private List<Plan> plans = new ArrayList<>();

    // one user get all the ratings for there previous visits
//    @OneToMany(
//            cascade = CascadeType.ALL,
//            orphanRemoval = false
//    )
//    @JoinColumn(name="userId")
//    private List<RatingAlgorithm> ratingAlgorithms = new ArrayList<>();

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
    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    /**
     * get all the user category ratings
     */
//    public List<RatingAlgorithm> getRatingAlgorithms(){return ratingAlgorithms; }
//
//    public void setRatingAlgorithms(List<RatingAlgorithm> ratingAlgorithms) {
//        this.ratingAlgorithms = ratingAlgorithms;
//    }

//    /**
//     * addPlan used to synchronize both sides of the bidirectional association
//     */
//    public void addPlan(Plan plan){
//        plans.add(plan);
//        plan.setUser(this);
//    }
//    /**
//     * removePlan used to synchronize both sides of the bidirectional association
//     */
//    public void removePlan(Plan plan){
//        plans.remove(plan);
//        plan.setUser(null);
//    }

    public static User generateDummyUserWithPlans(){
        final int NUM_PLANS = 30;
        final String[] CATEGORIES = {"pizza","salad","bars","pasta", "cocktailbars"};

        User user = new User((long) 1, "bobRoss", "e@mail.com");
        List<Plan> planList = new ArrayList<>();

        for(int i=0; i<NUM_PLANS; i++){
            Plan plan = new Plan();
            RestaurantInfo restaurantInfo = new RestaurantInfo();
            restaurantInfo.setRestaurantAddress("123 Street");
            restaurantInfo.setRestaurantName("Generic Restaturant Name");
            restaurantInfo.setRestaurantZip("12345");

            List<Category> catList = new ArrayList<>();
            int randCatIndex = (int) ((Math.random() * (CATEGORIES.length)));
            catList.add(new Category(CATEGORIES[randCatIndex]));
            randCatIndex = (int) ((Math.random() * (CATEGORIES.length)));
            catList.add(new Category(CATEGORIES[randCatIndex]));
            restaurantInfo.setCategories(catList);

            plan.setRestaurantInfo(restaurantInfo);
            plan.setRating((int) ((Math.random() * (5 - 1)) + 1));

            planList.add(plan);
        }
        user.setPlans(planList);

        return user;
    }

}


