package com.sohosqared.omdb.movie.application;

import com.sohosqared.omdb.movie.domain.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieDescription {
    Flux<Movie> findMovie(String query, String apiKey);

    Mono<Movie> findMovieById(String id, String apiKey);
}
