package com.uady.blackWolfCinema.service;

import java.util.List;

import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.validation.MovieValidation;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(int theId);
    void save(MovieValidation movieValidation);
    void save(Movie movie);
    void deleteMovie(Movie movie);
}
