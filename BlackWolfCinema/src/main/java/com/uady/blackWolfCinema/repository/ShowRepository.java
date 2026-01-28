package com.uady.blackWolfCinema.repository;

import com.uady.blackWolfCinema.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    List<Show> findByMovieId(int id);

}
