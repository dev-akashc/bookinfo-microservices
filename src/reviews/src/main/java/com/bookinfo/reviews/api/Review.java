package com.bookinfo.reviews.api;

public class Review {
    private String reviewer;
    private String text;
    private Rating rating;

    public Review(String reviewer, String text, Rating rating) {
        this.reviewer = reviewer;
        this.text = text;
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getText() {
        return text;
    }

}
