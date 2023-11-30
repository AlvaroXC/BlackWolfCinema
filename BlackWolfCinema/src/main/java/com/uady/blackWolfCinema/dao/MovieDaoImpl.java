package com.uady.blackWolfCinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uady.blackWolfCinema.model.Movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class MovieDaoImpl implements MovieDao {


    @Autowired
    private EntityManager entityManager;

    public MovieDaoImpl(EntityManager entityManager){
        this.entityManager= entityManager;
    }

    @Override
    public List<Movie> getAllMovies() {
        TypedQuery<Movie> thQuery = entityManager.createQuery("from Movie", Movie.class);
        return thQuery.getResultList();
    }

    @Override
    @Transactional
    public void saveMovie(Movie movie) {
        entityManager.merge(movie);
    }

    @Override
    public Movie findById(int id) {
        TypedQuery<Movie> theQuery = entityManager.createQuery("from Movie where id=:idMovie", Movie.class);
		theQuery.setParameter("idMovie", id);

		Movie theMovie = null;
		try {
			theMovie = theQuery.getSingleResult();
		} catch (Exception e) {
			theMovie = null;
		}

		return theMovie;
    }

    @Override
    @Transactional
    public void deleteMovie(Movie movie) {
        entityManager.remove(movie);
    }



    
}
