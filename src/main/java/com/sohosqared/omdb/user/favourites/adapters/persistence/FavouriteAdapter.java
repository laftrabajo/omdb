package com.sohosqared.omdb.user.favourites.adapters.persistence;

import com.sohosqared.omdb.user.favourites.domain.Favourite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavouriteAdapter {

    Flux<Favourite> getUserFavourites(String userId);

    Mono<Void> addUserFavourite(Favourite favourite);

    Mono<Void> deleteUserFavourite(String userId, String favouriteId);
}
