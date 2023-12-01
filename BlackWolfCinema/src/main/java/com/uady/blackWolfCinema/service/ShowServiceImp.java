package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.ShowDao;
import com.uady.blackWolfCinema.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImp implements ShowService {

    private ShowDao showDao;

    // Constructor for dependency injection
    @Autowired
    public ShowServiceImp(ShowDao theShowDao) {
        showDao = theShowDao;
    }

    // Get all shows from the repository
    @Override
    public List<Show> findAll() {
        return showDao.getAll();
    }

    // Get a specific show by its ID
    @Override
    public Show findById(int theId) {
        return showDao.findById(theId);
    }

    @Override
    public void save(Show theShow) {
        showDao.save(theShow);
    }

    @Override
    public void deleteById(int showId) {
        showDao.deleteById(showId);
    }

    @Override
    public List<Show> findShowsByMovieId(int movieId) {
        return showDao.findShowsByMovieId(movieId);
    }
}
