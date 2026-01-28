package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.ShowDao;
import com.uady.blackWolfCinema.model.Show;
import com.uady.blackWolfCinema.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImp implements ShowService {

    private final ShowRepository showRepository;

    // Constructor for dependency injection
    @Autowired
    public ShowServiceImp(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    // Get all shows from the repository
    @Override
    public List<Show> findAll() {
        return showRepository.findAll();
    }

    // Get a specific show by its ID
    @Override
    public Show findById(int theId) {

        Optional<Show> result = showRepository.findById(theId);
        Show show = null;

        if(result.isEmpty()){
            return null;
        }

        show = result.get();

        return show ;
    }

    @Override
    public void save(Show theShow) {
        showRepository.save(theShow);
    }

    @Override
    public void deleteById(int showId) {
        showRepository.deleteById(showId);
    }

    @Override
    public List<Show> findShowsByMovieId(int movieId) {
        return showRepository.findByMovieId(movieId);
    }
}
