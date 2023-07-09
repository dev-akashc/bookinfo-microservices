package com.bookinfo.reviews.api;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class Ratings {
    private int id;
    List<Rating>  ratings;

    @JsonbCreator
    public Ratings(
            @JsonbProperty("id") int id,
            @JsonbProperty("ratings") List<Rating> ratings) {
        this.id = id;
        this.ratings = ratings;         
    }

    public int getId() {
        return id;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
