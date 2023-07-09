package com.bookinfo.rataings.repository;

import com.bookinfo.rataings.Rating;
import com.bookinfo.rataings.Ratings;
import com.bookinfo.rataings.RatingsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@ApplicationScoped
public class RatingsServiceImpl implements RatingsService {

    @PersistenceContext
    EntityManager em;

    @Override
    public Ratings getByProductId(int productId) {
        List<RatingEntity> entities = em.createQuery("select r from RatingEntity r where r.productId = :productId", RatingEntity.class)
            .setParameter("productId", productId)
            .getResultList();

        List<Rating> ratingList = new ArrayList<>(entities.size());
        for (RatingEntity entity : entities) {
            ratingList.add(new Rating(entity.getReviewer(), entity.getStars()));
        }

        Ratings ratings = new Ratings(productId, ratingList);
        return ratings;
    }

    @Override
    public void add(Ratings ratings) {
        for (Rating rating : ratings.getRatings()) {
            RatingEntity entity = new RatingEntity();
            entity.setProductId(ratings.getProductId());
            entity.setReviewer(rating.getReviewer());
            entity.setStars(rating.getStars());
            em.persist(entity);
        }
    }
}
