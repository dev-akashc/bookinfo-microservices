package com.bookinfo.rataings;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;

import static io.quarkiverse.loggingjson.providers.KeyValueStructuredArgument.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@Path("/ratings")
public class RatingsResource {
    private static final Logger logger =
        LoggerFactory.getLogger(RatingsResource.class);

    @Inject
    RatingsService service;

    @Context
    HttpHeaders httpHeaders;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> get(@QueryParam("productId") int productId) {
        logger.info("get ratings",
            kv("end-user", httpHeaders.getRequestHeaders().getFirst("end-user")),
            kv("productId", productId)
        );
        return service.getByProductId(productId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rating put(Rating rating) {
        service.createOrUpdate(rating);;
        return rating;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Rating delete(Rating rating) {
        service.delete(rating);
        return rating;
    }
}
