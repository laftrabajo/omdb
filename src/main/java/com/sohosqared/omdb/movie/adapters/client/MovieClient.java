package com.sohosqared.omdb.movie.adapters.client;

import com.sohosqared.omdb.kernel.client.ClientAuth;
import com.sohosqared.omdb.movie.domain.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieClient {

    Flux<Movie> findMovie(String query, ClientAuth auth, long limit);

    Mono<Movie> findMovieById(String id, ClientAuth auth);
}
