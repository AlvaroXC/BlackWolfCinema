package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.ShowRepository;
import com.uady.blackWolfCinema.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImp implements ShowService {

    private ShowRepository showRepository;

    // Constructor for dependency injection
    @Autowired
    public ShowServiceImp(ShowRepository theShowRepository) {
        showRepository = theShowRepository;
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
        Show theShow = null;
        if (result.isPresent()) {
            // If the show is found, gets it
            theShow = result.get();
        } else {
            // If the show is not found, throw a runtime exception
            throw new RuntimeException("No se encontro el show con el id: " + theId);
        }
        return theShow;
    }

    @Override
    public Show save(Show theShow) {
        return showRepository.save(theShow);
    }

    @Override
    public void deleteById(int theId) {
        showRepository.deleteById(theId);
    }
}
