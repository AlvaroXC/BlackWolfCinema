package com.uady.blackWolfCinema.dao;

import com.uady.blackWolfCinema.model.Show;

import java.util.List;

//Spring Data JPA automatically generates common methods for CRUD operations
public interface ShowDao{
    Show findById(int id);
    List<Show> getAll();
    void save(Show show);
    void deleteById(int showId);
    List<Show> findShowsByMovieId(int movieId);
}
