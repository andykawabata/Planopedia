package com.team.planopedia.dao;

import javax.persistence.*;

@Entity
@Table(name="ratingAlgorithm")
public class RatingAlgorithm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long algorithmId;

    private Integer categoryRating;

    private Long userId;

    public RatingAlgorithm() {
    }

    public RatingAlgorithm(Long algorithmId, Integer categoryRating, Long userId) {
        this.algorithmId = algorithmId;
        this.categoryRating = categoryRating;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
