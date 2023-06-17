package com.bookinfo.reviews.api;

import com.bookinfo.reviews.repository.ReviewEntity;
import com.bookinfo.reviews.repository.ReviewsService;
import com.bookinfo.reviews.repository.ReviewsServiceImpl;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "reviews" path)
 */
@Path("/")
public class Endpoints {

    private ReviewsService reviewsService;

    public Endpoints() {
        this.reviewsService = new ReviewsServiceImpl();
    }

    /**
     * @return Reviews that will be returned as a application/json response.
     */
    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reviews getReviews(@PathParam("productId") int productId) {
        List<ReviewEntity> reviewEntities = reviewsService.findReviews(productId);

        List<Review> reviewList = new ArrayList<>();
        for (ReviewEntity reviewEntity: reviewEntities) {
            Rating rating = new Rating(reviewEntity.getStars(), reviewEntity.getColor());
            Review review = new Review(reviewEntity.getReviewer(), reviewEntity.getText(), rating);
            reviewList.add(review);
        }

        Reviews reviews = new Reviews(productId, reviewList);
        return reviews;
    }

    /**
     * @return Reviews that has been created by the request.
     */
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Reviews postReviews(Reviews reviews) {
        List<ReviewEntity> reviewEntities = new ArrayList<>();
        for (Review review : reviews.getReviews()) {
            ReviewEntity entity = new ReviewEntity();
            entity.setProductId(reviews.getId());
            entity.setReviewer(review.getReviewer());
            entity.setText(review.getText());
            entity.setStars(review.getRating().getStars());
            entity.setColor(review.getRating().getColor());
            reviewEntities.add(entity);
        }

        reviewsService.addReviews(reviewEntities);
        return reviews;
    }
}
