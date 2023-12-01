package com.uady.blackWolfCinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uady.blackWolfCinema.model.Show;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ShowDaoImpl implements ShowDao{

    @Autowired
    private EntityManager entityManager;

    public ShowDaoImpl(EntityManager entityManager){
        this.entityManager= entityManager;
    }

    @Override
    public List<Show> getAll() {
        TypedQuery<Show> thQuery = entityManager.createQuery("from Show", Show.class);
        return thQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(Show show) {
        entityManager.merge(show);
    }

    @Override
    public Show findById(int id) {
        TypedQuery<Show> theQuery = entityManager.createQuery("from Show where showId=:showId", Show.class);
		theQuery.setParameter("showId", id);

		Show theShow = null;
		try {
			theShow = theQuery.getSingleResult();
		} catch (Exception e) {
			theShow = null;
		}

		return theShow;
    }

    @Override
    @Transactional
    public void deleteById(int showId) {
        TypedQuery<Show> theQuery = entityManager.createQuery("from Show where showId=:showId", Show.class);
		theQuery.setParameter("showId", showId);

		Show theShow = null;
		try {
			theShow = theQuery.getSingleResult();
            entityManager.remove(theShow);
		} catch (Exception e) {
			theShow = null;
		}
    }

    @Override
    public List<Show> findShowsByMovieId(int movieId) {
        TypedQuery<Show> thQuery = entityManager.createQuery("SELECT s FROM Show s WHERE s.movie.id = :movieId", Show.class);
        thQuery.setParameter("movieId", movieId);        
        return thQuery.getResultList();
    }
    
}
