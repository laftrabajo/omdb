package com.sohosqared.omdb.user.favourites.adapters.persistence.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FavouriteRepository extends JpaRepository<FavouriteEntity, String> {

    List<FavouriteEntity> findFavouriteEntitiesById_UserId(String userId);
    void deleteFavouriteEntityById(FavouriteId id);

}
