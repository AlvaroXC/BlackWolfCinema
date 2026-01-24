package com.uady.blackWolfCinema.service;

import java.util.List;
import java.util.Optional;

import com.uady.blackWolfCinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.validation.MovieValidation;

@Service
public class MovieServiceImpl implements MovieService{


    private final MovieRepository movieRepository;
    private final FilesStorageService filesStorageService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, FilesStorageService filesStorageService){
        this.movieRepository=movieRepository;
        this.filesStorageService = filesStorageService;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int theId) {

        Optional<Movie> movie = movieRepository.findById(theId);
        Movie movieToReturn = null;

        if(movie.isPresent()){
            movieToReturn = movie.get();
        }

        return movieToReturn;
    }
    
    @Override
    public void save(MovieValidation movieValidation) {

        Movie movie = new Movie();
        movie.setName(movieValidation.getName());
        movie.setSynopsis(movieValidation.getSynopsis());
        movie.setDuration(movieValidation.getDuration());
        movie.setTrailer(movieValidation.getTrailer());
        String imagePath = filesStorageService.saveFile(movieValidation.getPortada());
		movie.setImagePath(imagePath);
        movieRepository.save(movie);
    }

    @Override
    public void deleteById(int theId) {
        Optional<Movie> result = movieRepository.findById(theId);
        Movie movie = null;
        if(result.isEmpty()){
            return;
        }
        movie = result.get();
        filesStorageService.deleteFile(movie.getImagePath());
        movieRepository.deleteById(theId);
    }

    @Override
    public void update(int id, MovieValidation movieValidation) {
        Optional<Movie> result = movieRepository.findById(id);
        Movie movie = null;

        if (result.isEmpty()){
            return;
        }
        movie = result.get();

        movie.setName(movieValidation.getName());
        movie.setSynopsis(movieValidation.getSynopsis());
        movie.setDuration(movieValidation.getDuration());
        movie.setTrailer(movieValidation.getTrailer());

        if(!movieValidation.getPortada().isEmpty()) {
            filesStorageService.deleteFile(movie.getImagePath());
            String imagePath = filesStorageService.saveFile(movieValidation.getPortada());
            movie.setImagePath(imagePath);
        }

        movieRepository.save(movie);

    }


}
