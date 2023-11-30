package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.model.Show;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowService {
    List<Show> findAll();
    Show findById(int theId);
    Show save(Show theShow);
    void deleteById(int theId);

}
