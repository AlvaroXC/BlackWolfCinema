package com.uady.blackWolfCinema.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uady.blackWolfCinema.model.Ticket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TicketDaoImpl implements TicketDao{

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public void save(Ticket ticket) {
        entityManager.merge(ticket);
    }

    @Override
    public List<Ticket> getAllByShow(int showId) {
        TypedQuery<Ticket> thQuery = entityManager.createQuery("SELECT t.seat FROM Ticket t WHERE t.show.showId = :showId", Ticket.class);
        thQuery.setParameter("showId", showId);        
        return thQuery.getResultList();
    }



}
