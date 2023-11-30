package com.uady.blackWolfCinema.controller;

import com.uady.blackWolfCinema.model.Show;
import com.uady.blackWolfCinema.model.Ticket;
import com.uady.blackWolfCinema.service.ShowService;
import com.uady.blackWolfCinema.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;
    private ShowService showService;

    // Constructor injection for TicketService and ShowService
    public TicketController(TicketService ticketService, ShowService showService) {
        this.ticketService = ticketService;
        this.showService = showService;
    }

    // Handling GET request to display a list of tickets
    @GetMapping("/list")
    public String listTickets(Model theModel) {
        // Retrieve all tickets from the service
        List<Ticket> theTickets = ticketService.findAll();

        // Add the list of tickets to the model for rendering in the view
        theModel.addAttribute("tickets", theTickets);

        // Return the view name for rendering
        return "tickets/list-tickets";
    }

    // Handling POST request to save tickets
    @PostMapping("/save")
    public String save(@ModelAttribute("ticket") Ticket theTicket,
                       @RequestParam("showId") int showId,
                       @RequestParam("seat") String[] seat) {
        // Find the show using the showId
        Show show = showService.findById(showId);

        // Loop through the selected seats and save tickets for each seat
        for (int i = 0; i < seat.length; i++) {
            ticketService.save(new Ticket(seat[i], show));
        }

        // Redirect to the list page after saving tickets
        return "redirect:/tickets/list";
    }
}
