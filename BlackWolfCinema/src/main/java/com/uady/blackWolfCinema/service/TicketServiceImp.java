package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.TicketDao;
import com.uady.blackWolfCinema.model.Ticket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TicketServiceImp implements TicketService {

    private TicketDao ticketDao;
    @Autowired
    public TicketServiceImp(TicketDao theTicketDao){
        ticketDao=theTicketDao;

    }

    @Override
    public void save(Ticket theTicket) {
        ticketDao.save(theTicket);
    }
    
    @Override
    public List<Ticket> findAllByShow(int showId) {       
        return ticketDao.getAllByShow(showId);
    }

}
