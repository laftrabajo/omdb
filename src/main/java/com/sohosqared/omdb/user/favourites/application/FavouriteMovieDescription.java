package com.sohosqared.omdb.user.favourites.application;

import com.sohosqared.omdb.movie.domain.Movie;
import com.sohosqared.omdb.user.favourites.domain.Favourite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavouriteMovieDescription {

    Flux<Movie> getUserFavourites(String userId, String apiKey);

    Mono<Void> addUserFavourite(Favourite favourite);

    Mono<Void> deleteUserFavourite(String userId, String favouriteId);
}
