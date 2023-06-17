package com.bookinfo.reviews.api;

import java.util.List;

public class Reviews {
    private int id;
    List<Review> reviews;

    public Reviews(int id, List<Review> reviews) {
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
