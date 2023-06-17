package com.bookinfo.reviews.api;

import com.bookinfo.reviews.repository.ReviewEntity;
import com.bookinfo.reviews.repository.ReviewsService;
import com.bookinfo.reviews.repository.ReviewsServiceImpl;
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
        ReviewsService reviewService = new ReviewsServiceImpl();
        List<ReviewEntity> reviewEntities = reviewService.findReviews(productId);

        List<Review> reviewList = new ArrayList<>();
        for (ReviewEntity reviewEntity: reviewEntities) {
            Rating rating = new Rating(reviewEntity.getStars(), reviewEntity.getColor());
            Review review = new Review(reviewEntity.getReviewer(), reviewEntity.getText(), rating);
            reviewList.add(review);
        }

        Reviews reviews = new Reviews(productId, reviewList);
        return reviews;
    }
}
