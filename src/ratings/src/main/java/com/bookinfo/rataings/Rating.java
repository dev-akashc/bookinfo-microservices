package com.bookinfo.rataings;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Rating {
    private String reviewer;
    private int stars;

    @JsonCreator
    public Rating(String reviewer, int stars) {
        this.reviewer = reviewer;
        this.stars = stars;
    }

    public String getReviewer() {
        return reviewer;
    }

    public int getStars() {
        return stars;
    }

    public String getColor() {
        return "red";
    }

}