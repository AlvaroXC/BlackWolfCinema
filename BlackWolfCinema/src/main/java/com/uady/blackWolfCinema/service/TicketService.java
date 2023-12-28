package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    List<Ticket> findAllByShow(int showId);

    // Ticket findById(int theId);
    void save(Ticket theTicket);
    // void deleteById(int theId);
}
