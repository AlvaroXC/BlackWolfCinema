package com.uady.blackWolfCinema.dao;

import java.util.List;

import com.uady.blackWolfCinema.model.Movie;

public interface MovieDao {

    Movie findById(int id);
    List<Movie> getAllMovies();
    void saveMovie(Movie movie);
    void deleteMovie(Movie movie);
    
    
}
