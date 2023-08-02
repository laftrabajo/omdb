package com.sohosqared.omdb.user.favourites.application.impl;

import com.sohosqared.omdb.user.favourites.adapters.persistence.FavouriteAdapter;
import com.sohosqared.omdb.user.favourites.application.FavouriteMovieDescription;
import com.sohosqared.omdb.user.favourites.application.FavouriteMovieService;
import com.sohosqared.omdb.user.favourites.domain.Favourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FavouriteMovieServiceImpl implements FavouriteMovieDescription {

    private final FavouriteMovieService service;

    public FavouriteMovieServiceImpl(@Autowired FavouriteAdapter persistence) {
        service = new FavouriteMovieService(persistence);
    }

    @Override
    public Flux<Favourite> getUserFavourites(String userId) {
        return service.getUserFavourites(userId);
    }

    @Override
    public Mono<Void> addUserFavourite(Favourite favourite) {
        return service.addUserFavourite(favourite);
    }

    @Override
    public Mono<Void> deleteUserFavourite(String userId, String favouriteId) {
        return service.deleteUserFavourite(userId, favouriteId);
    }
}
