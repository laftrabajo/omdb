package com.sohosqared.omdb.user.favourites.adapters.persistence.impl;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteId implements Serializable {
    private String userId;
    private String movieId;
}
