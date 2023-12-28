package com.uady.blackWolfCinema.dao;

import java.util.List;
import com.uady.blackWolfCinema.model.Ticket;

public interface TicketDao {
    List<Ticket> getAllByShow(int showId);
    void save(Ticket ticket);
}
