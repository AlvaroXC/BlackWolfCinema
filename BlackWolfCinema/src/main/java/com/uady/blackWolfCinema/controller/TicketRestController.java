package com.uady.blackWolfCinema.controller;

import com.uady.blackWolfCinema.model.Ticket;
import com.uady.blackWolfCinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/getSaleTickets/{showId}")
    public List<Ticket> getSoldTickets(@PathVariable int showId){
        return ticketService.findAllByShow(showId);
    }
}
