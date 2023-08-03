package com.sohosqared.omdb.movie.domain;

public record Movie(String mdbId, String title, String year, String genre, String language, String plot) { }
