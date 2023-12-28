package com.uady.blackWolfCinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uady.blackWolfCinema.dao.MovieDao;
import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.validation.MovieValidation;

@Service
public class MovieServiceImpl implements MovieService{


    private MovieDao movieDao;
    @Autowired
    private FileStorageServiceImpl servicio;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao){
        this.movieDao=movieDao;
    }

    @Override
    public List<Movie> findAll() {
        return movieDao.getAllMovies();
    }

    @Override
    public Movie findById(int theId) {
        return movieDao.findById(theId);
    }
    
    @Override
    public void save(MovieValidation movieValidation) {

        Movie movie = new Movie();
        movie.setName(movieValidation.getName());
        movie.setSynopsis(movieValidation.getSynopsis());
        movie.setDuration(movieValidation.getDuration());
        movie.setTrailer(movieValidation.getTrailer());
        String rutaPortada = servicio.saveFile(movieValidation.getPortada());
		movie.setImagePath(rutaPortada);
        movieDao.saveMovie(movie);
    }


    @Override
    public void deleteMovie(Movie movie) {
        movieDao.deleteMovie(movie);
    }

    @Override
    public void save(Movie movie) {
        movieDao.saveMovie(movie);
    }
    
}
