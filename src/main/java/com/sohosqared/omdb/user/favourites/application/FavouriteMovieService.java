package com.sohosqared.omdb.user.favourites.application;

import com.sohosqared.omdb.user.favourites.adapters.persistence.FavouriteAdapter;
import com.sohosqared.omdb.user.favourites.domain.Favourite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * This class contains the business logic for favourites with no framework related to it
 */
public class FavouriteMovieService implements FavouriteMovieDescription {

    private final FavouriteAdapter persistence;

    public FavouriteMovieService(FavouriteAdapter persistence) {
        this.persistence = persistence;
    }

    public Flux<Favourite> getUserFavourites(String userId) {
        return persistence.getUserFavourites(userId);
    }

    public Mono<Void> addUserFavourite(Favourite favourite) {
        return persistence.getUserFavourites(favourite.userId())
                .collectList()
                .and(persistence.addUserFavourite(favourite));
    }

    public Mono<Void> deleteUserFavourite(String userId, String favouriteId) {
        return persistence.deleteUserFavourite(userId, favouriteId);
    }
}
