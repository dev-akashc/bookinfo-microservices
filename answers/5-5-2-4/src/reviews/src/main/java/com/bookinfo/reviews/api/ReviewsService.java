package com.bookinfo.reviews.api;

import java.util.List;

public interface ReviewsService {

   public List<Review> findReviews(int productId);

   public void createOrUpdateReview(Review review);

}
