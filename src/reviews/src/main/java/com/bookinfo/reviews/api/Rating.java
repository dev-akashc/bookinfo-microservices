package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class Rating {
    private int stars;

    @JsonbCreator
    public Rating(@JsonbProperty("stars") int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public String getColor() {
        return "black";
    }

}
