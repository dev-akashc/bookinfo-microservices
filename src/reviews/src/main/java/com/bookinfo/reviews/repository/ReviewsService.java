package com.bookinfo.reviews.repository;

import java.util.List;

public interface ReviewsService {

   public List<ReviewEntity> findReviews(int productId);

}
