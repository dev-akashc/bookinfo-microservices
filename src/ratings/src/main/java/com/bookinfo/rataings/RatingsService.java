package com.bookinfo.rataings;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface RatingsService {

    public Ratings getByProductId(int productId);

    public void add(Ratings ratings);
}
