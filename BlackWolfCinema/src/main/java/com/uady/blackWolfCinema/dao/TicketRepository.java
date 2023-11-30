package com.uady.blackWolfCinema.dao;

import com.uady.blackWolfCinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

//Spring Data JPA automatically generates common methods for CRUD operations
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
