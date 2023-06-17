package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class Reviews {
    private int id;
    List<Review> reviews;

    @JsonbCreator
    public Reviews(
            @JsonbProperty("id") int id,
            @JsonbProperty("reviews") List<Review> reviews) {
        this.id = id;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

}
