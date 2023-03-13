package com.bookinfo.reviews.api;

public class Review {
    private String reviewer;
    private String text;
    private Rating rating;

    public Rating getRating() {
        return rating;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

}
