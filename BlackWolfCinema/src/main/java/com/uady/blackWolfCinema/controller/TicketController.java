package com.uady.blackWolfCinema.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uady.blackWolfCinema.model.Receipt;
import com.uady.blackWolfCinema.model.Show;
import com.uady.blackWolfCinema.model.Ticket;
import com.uady.blackWolfCinema.service.CinemaRoomService;
import com.uady.blackWolfCinema.service.ReceiptService;
import com.uady.blackWolfCinema.service.ShowService;

@Controller
@RequestMapping("/")
public class TicketController {
	@Autowired
	private ShowService showService;
	@Autowired
	CinemaRoomService cinemaRoomService;
    @Autowired
    private ReceiptService receiptService;


    @PostMapping("/tickets/save")
    public ModelAndView save(@ModelAttribute("ticket") Ticket theTicket,
                       @RequestParam("showId") int showId,
                       @RequestParam("seat") String[] selectedSeat) {
        // Find the show using the showId
        Show show = showService.findById(showId);
        int totalPrice=selectedSeat.length*show.getCinemaRoom().getSeatPrice();
        Receipt receipt = new Receipt(LocalDate.now(), totalPrice);

        for (String seat : selectedSeat) {
            Ticket ticket = new Ticket(seat, show);
            receipt.add(ticket);
        }
        receiptService.saveReceipt(receipt);

        ModelAndView modelAndView = new ModelAndView("compra");
        modelAndView.addObject("ticket",theTicket);

        // Redirect to the list page after saving tickets
        // return "redirect:/tickets/list";
        return modelAndView;
    }


}
