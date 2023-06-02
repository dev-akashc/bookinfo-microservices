package com.bookinfo.reviews.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.*;


@ApplicationScoped
@Transactional
public class ReviewService {

    @PersistenceContext
    private EntityManager em;

    public ReviewService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReviewService");
        this.em = emf.createEntityManager();
    }

    public List<ReviewEntity> findReviews(int productId) {
        List<ReviewEntity> reviews = em.createQuery("select r from ReviewEntity r where r.productId = :productId", ReviewEntity.class)
                .setParameter("productId", productId)
                .getResultList();
        return reviews;
    }
}
