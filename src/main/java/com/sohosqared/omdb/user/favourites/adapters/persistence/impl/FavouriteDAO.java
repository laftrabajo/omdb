package com.sohosqared.omdb.user.favourites.adapters.persistence.impl;

import com.sohosqared.omdb.user.favourites.adapters.persistence.FavouriteAdapter;
import com.sohosqared.omdb.user.favourites.domain.Favourite;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FavouriteDAO implements FavouriteAdapter {

    private final FavouriteRepository repository;
    private final FavouriteMapper mapper;

    public FavouriteDAO(FavouriteRepository repository, FavouriteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Flux<Favourite> getUserFavourites(String userId) {
        return Flux.fromIterable(repository.findFavouriteEntitiesById_UserId(userId))
                .map(mapper::fromEntity);
    }

    @Override
    public Mono<Void> addUserFavourite(Favourite favourite) {
        try {
            repository.save(mapper.toEntity(favourite));
            return Mono.empty();
        }
        catch (Exception e) {
            return Mono.error(e);
        }
    }

    @Override
    public Mono<Void> deleteUserFavourite(String userId, String favouriteId) {
        try {
            repository.deleteFavouriteEntityById(new FavouriteId(userId, favouriteId));
        }
        catch (Exception e) {
            return Mono.error(e);
        }
        return Mono.empty();
    }
}
