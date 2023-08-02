package com.sohosqared.omdb.movie.adapters.controllers;

import com.sohosqared.omdb.movie.application.MovieDescription;
import com.sohosqared.omdb.movie.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("movie")
public class MovieController {

    private final MovieDescription service;

    public MovieController(@Autowired MovieDescription service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Movie> findMovie(@RequestParam String search, @RequestParam String apiKey) {
        return service.findMovie(search, apiKey);
    }
}
