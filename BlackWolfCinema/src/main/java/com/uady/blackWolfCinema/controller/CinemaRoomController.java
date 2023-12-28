package com.uady.blackWolfCinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uady.blackWolfCinema.model.Show;
import com.uady.blackWolfCinema.service.ShowService;

@Controller
public class CinemaRoomController {

    @Autowired
    ShowService showService;

    @GetMapping("/seats/{showId}")
    public String showSeatSelectionPage(@PathVariable int showId, Model theModel) {
        // Get the show and cinema room for ticket purchase
        Show show = showService.findById(showId);
        theModel.addAttribute("show", show);
        // Redirect to the seat selection page
        return "select-seats";
    }
}
