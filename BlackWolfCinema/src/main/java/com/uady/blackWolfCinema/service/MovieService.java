package com.uady.blackWolfCinema.service;

import java.util.List;
import java.util.Optional;

import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.validation.MovieValidation;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(int theId);
    void save(MovieValidation movieValidation);
    void deleteById(int theId);
    void update(int id, MovieValidation movieValidation);
}
