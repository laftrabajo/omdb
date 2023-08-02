package com.sohosqared.omdb.user.favourites.adapters.controllers;

import com.sohosqared.omdb.user.favourites.application.FavouriteMovieDescription;
import com.sohosqared.omdb.user.favourites.domain.Favourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/{userId}/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteMovieDescription service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Favourite> getFavourites(@PathVariable String userId) {
        return service.getUserFavourites(userId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> addFavourites(@RequestBody Favourite favourite) {
        return service.addUserFavourite(favourite);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> deleteFavourites(@RequestBody Favourite favourite) {
        return service.deleteUserFavourite(favourite.userId(), favourite.movieId());
    }

}
