package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class Rating {
    private int stars;
    private String color;

    @JsonbCreator
    public Rating(
            @JsonbProperty("stars") int stars,
            @JsonbProperty("color") String color) {
        this.stars = stars;
        this.color = color;
    }

    public int getStars() {
        return stars;
    }

    public String getColor() {
        return color;
    }

}
