package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.TicketRepository;
import com.uady.blackWolfCinema.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class TicketServiceImp implements TicketService {
    private TicketRepository ticketRepository;
    @Autowired
    public TicketServiceImp(TicketRepository theTicketRepository){
        ticketRepository=theTicketRepository;

    }
    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(int theId) {
        Optional<Ticket> result=ticketRepository.findById(theId);
        Ticket theTicket=null;
        if(result.isPresent()){
            theTicket=result.get();
        }else{
            throw new RuntimeException("No se encontro el ticket con el ID: "+theId);
        }
        return theTicket;
    }

    @Override
    public Ticket save(Ticket theTicket) {
        return ticketRepository.save(theTicket);
    }


    @Override
    public void deleteById(int theId) {
        ticketRepository.deleteById(theId);
    }
}
