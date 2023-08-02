package com.sohosqared.omdb.user.favourites.adapters.persistence.impl;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteEntity {
    @EmbeddedId
    private FavouriteId id;
}
