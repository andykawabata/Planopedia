package com.team.planopedia.dao;

import javax.persistence.*;

@Entity
@Table(name="ratingAlgorithm")
public class RatingAlgorithm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long algorithmId;

    private Integer categoryRating;

   // one user have many ratingAlgorithm
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    //one category can belong to many ratingAlgorithm
    @ManyToOne
    @JoinColumn(name = "categoryName")
    Category category;

    public RatingAlgorithm() {
    }

    public RatingAlgorithm(Long algorithmId, Integer categoryRating, User user, Category category) {
        this.algorithmId = algorithmId;
        this.categoryRating = categoryRating;
        this.user = user;
        this.category = category;
    }

    public Long getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public Integer getCategoryRating() {
        return categoryRating;
    }

    public void setCategoryRating(Integer categoryRating) {
        this.categoryRating = categoryRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
