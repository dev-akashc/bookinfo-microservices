package com.bookinfo.reviews.api;

public class Rating {
    private int stars;
    private String color;

    public Rating(int stars, String color) {
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
