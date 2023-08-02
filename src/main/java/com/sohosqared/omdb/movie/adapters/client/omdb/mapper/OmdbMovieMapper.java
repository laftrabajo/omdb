package com.sohosqared.omdb.movie.adapters.client.omdb.mapper;

import com.sohosqared.omdb.movie.adapters.client.omdb.OmdbResponse;
import com.sohosqared.omdb.movie.domain.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OmdbMovieMapper {

    @Mapping(source = "imdbID", target = "mdbId")
    Movie toMovie(OmdbResponse response);

}
