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
public class ReviewsServiceImpl implements ReviewsService {

    @PersistenceContext
    private EntityManager em;

    public ReviewsServiceImpl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ReviewsService");
        this.em = emf.createEntityManager();
    }

    @Override
    public List<ReviewEntity> findReviews(int productId) {
        List<ReviewEntity> reviews = em.createQuery("select r from ReviewEntity r where r.productId = :productId", ReviewEntity.class)
                .setParameter("productId", productId)
                .getResultList();
        return reviews;
    }

    @Override
    public void addReviews(List<ReviewEntity> reviewEntities) {
        em.getTransaction().begin();
        for (ReviewEntity entity : reviewEntities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
    }
}
