package com.sohosqared.omdb.user.favourites.application;

import com.sohosqared.omdb.movie.application.MovieService;
import com.sohosqared.omdb.movie.domain.Movie;
import com.sohosqared.omdb.user.favourites.adapters.persistence.FavouriteAdapter;
import com.sohosqared.omdb.user.favourites.domain.Favourite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class contains the business logic for favourites with no framework related to it
 */
public class FavouriteMovieService implements FavouriteMovieDescription {

    private final FavouriteAdapter persistence;
    private final MovieService movieService;

    public FavouriteMovieService(FavouriteAdapter persistence, MovieService movieService) {
        this.persistence = persistence;
        this.movieService = movieService;
    }

    public Flux<Movie> getUserFavourites(String userId, String apiKey) {
        return persistence.getUserFavourites(userId)
                .flatMap(it -> movieService.findMovieById(it.movieId(), apiKey)); // TODO: caching
    }

    public Mono<Void> addUserFavourite(Favourite favourite) {
        // TODO: needs validation of film existance
        return persistence.getUserFavourites(favourite.userId())
                .collectList()
                .and(persistence.addUserFavourite(favourite));
    }

    public Mono<Void> deleteUserFavourite(String userId, String favouriteId) {
        return persistence.deleteUserFavourite(userId, favouriteId);
    }
}
