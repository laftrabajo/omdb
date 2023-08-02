package com.sohosqared.omdb.user.favourites.adapters.persistence.impl;

import com.sohosqared.omdb.user.favourites.domain.Favourite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {

    default Favourite fromEntity(FavouriteEntity entity) {
        return new Favourite(entity.getId().getUserId(), entity.getId().getMovieId());
    }

    default FavouriteEntity toEntity(Favourite favourite) {
        return new FavouriteEntity(new FavouriteId(favourite.userId(), favourite.movieId()));
    }
}
