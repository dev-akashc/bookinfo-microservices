package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class Review {
    private String reviewer;
    private String text;
    private Rating rating;

    @JsonbCreator
    public Review(
            @JsonbProperty("reviewer") String reviewer,
            @JsonbProperty("text") String text,
            @JsonbProperty("rating") Rating rating) {
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
