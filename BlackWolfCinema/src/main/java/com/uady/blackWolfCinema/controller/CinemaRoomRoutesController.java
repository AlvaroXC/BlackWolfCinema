package com.uady.blackWolfCinema.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CinemaRoomRoutesController {
    @GetMapping("/asientos")
    public String showSeats(){
        return "asientos";
    }
}
