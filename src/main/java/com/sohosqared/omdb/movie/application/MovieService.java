package com.sohosqared.omdb.movie.application;

import com.sohosqared.omdb.kernel.client.ClientAuth;
import com.sohosqared.omdb.movie.adapters.client.MovieClient;
import com.sohosqared.omdb.movie.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.Map;

@Service
public class MovieService implements MovieDescription {

    private final MovieClient client;

    public MovieService(@Autowired MovieClient client) {
        this.client = client;
    }

    @Override
    public Flux<Movie> findMovie(String query, String apiKey) {
        return client.findMovie(query, new KeyClientAuth(apiKey), 3);
    }

    @Override
    public Mono<Movie> findMovieById(String id, String apiKey) {
        return client.findMovieById(id, new KeyClientAuth(apiKey));
    }

    private record KeyClientAuth (String key) implements ClientAuth  {

        @Override
        public EnumMap<Datatype, Object> authData() {
            return new EnumMap<>(Map.of(Datatype.KEY, key));
        }
    }
}
