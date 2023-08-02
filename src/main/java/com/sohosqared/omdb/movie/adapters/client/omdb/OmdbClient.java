package com.sohosqared.omdb.movie.adapters.client.omdb;

import com.sohosqared.omdb.kernel.client.ClientAuth;
import com.sohosqared.omdb.movie.adapters.client.MovieClient;
import com.sohosqared.omdb.movie.adapters.client.omdb.mapper.OmdbMovieMapper;
import com.sohosqared.omdb.movie.domain.Movie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Service
public class OmdbClient implements MovieClient {
    private static final String CIRCUIT_BREAKER_NAME = "omdb";

    @Value("${application.config.client.omdb.url}")
    private String omdbUrl;

    private final WebClient client = WebClient.builder().build();

    private final OmdbMovieMapper omdbMovieMapper;

    public OmdbClient(@Autowired OmdbMovieMapper omdbMovieMapper) {
        this.omdbMovieMapper = omdbMovieMapper;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME)
    public Flux<Movie> findMovie (String search, ClientAuth auth, long limit) {
        if (!auth.authData().containsKey(ClientAuth.Datatype.KEY)) {
            return Flux.error(new IllegalArgumentException("This implementation needs a key as authentication"));
        }
        return client.get()
                .uri(uriBuilder ->
                        buildSearchURI(uriBuilder, (String) auth.authData().get(ClientAuth.Datatype.KEY), search, limit))
                .retrieve()
                .bodyToFlux(OmdbResponse.class)
                .take(limit)
                .map(omdbMovieMapper::toMovie);
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME)
    public Mono<Movie> findMovieById(String id, ClientAuth auth) {
        if (!auth.authData().containsKey(ClientAuth.Datatype.KEY)) {
            return Mono.error(new IllegalArgumentException("This implementation needs a key as authentication"));
        }
        return client.get()
                .uri(uriBuilder ->
                        buildSearchByIdURI(uriBuilder, (String) auth.authData().get(ClientAuth.Datatype.KEY), id))
                .retrieve()
                .bodyToMono(OmdbResponse.class)
                .map(omdbMovieMapper::toMovie);
    }

    private URI buildSearchURI (UriBuilder uriBuilder, String key, String search, Long limit) {
        var builder = baseBuilder(uriBuilder, key)
                .queryParam("t", search);
        
        if (limit != null && limit > 0L) {
            builder.queryParam("page", 1);
        }

        return uriBuilder.build();
    }

    private URI buildSearchByIdURI (UriBuilder uriBuilder, String key, String id) {
        return baseBuilder(uriBuilder, key)
                .queryParam("i", id)
                .build();
    }

    private UriBuilder baseBuilder(UriBuilder uriBuilder, String key){
        return uriBuilder
                .scheme("http")
                .host(omdbUrl)
                .queryParam("r", "json")
                .queryParam("apikey", key);
    }

    public Mono<OmdbResponse> fallback(Throwable ex) {
        return Mono.error(ex);
    }
}
