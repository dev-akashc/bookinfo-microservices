package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class Rating {
    private String reviewer;
    private int stars;

    @JsonbCreator
    public Rating(
            @JsonbProperty("reviewer") String reviewer,
            @JsonbProperty("stars") int stars) {
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
        return "black";
    }

}
