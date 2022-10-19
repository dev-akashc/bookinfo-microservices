package com.bookinfo.reviews.api;

import com.bookinfo.reviews.repository.ReviewEntity;
import com.bookinfo.reviews.repository.ReviewService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "reviews" path)
 */
@Path("/{productId}")
public class Endpoints {

    /**
     * @return Reviews that will be returned as a application/json response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Reviews getReview(@PathParam("productId") int productId) {
        Reviews reviews = new Reviews();
        reviews.setId(productId);

        ReviewService reviewService = new ReviewService();
        List<ReviewEntity> reviewEntities = reviewService.findReviews(productId);

        List<Review> reviewList = new ArrayList<>();
        for (ReviewEntity reviewEntity: reviewEntities) {
            Review review = new Review();
            review.setReviewer(reviewEntity.getReviewer());
            review.setText(reviewEntity.getText());

            Rating rating = new Rating();
            rating.setStars(reviewEntity.getStars());
            rating.setColor(reviewEntity.getColor());
            review.setRating(rating);

            reviewList.add(review);
        }
        reviews.setReviews(reviewList);

        return reviews;
    }
}
