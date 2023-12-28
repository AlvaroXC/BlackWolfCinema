package com.uady.blackWolfCinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uady.blackWolfCinema.model.CinemaRoom;
import com.uady.blackWolfCinema.service.CinemaRoomService;

@RestController
@RequestMapping("/api")
public class CinemaRoomRestController {

    private CinemaRoomService cinemaRoomService;
    //Inject cinema room dao
    @Autowired
    public CinemaRoomRestController(CinemaRoomService theCinemaRoomService){
        cinemaRoomService =theCinemaRoomService;
    }
    
    //find a Cinema Room by its id 
    @GetMapping("/cinemaRoom/{cinemaRoomID}")
    public CinemaRoom getCinemaRoom(@PathVariable int cinemaRoomID){
        CinemaRoom theCinemaRoom = cinemaRoomService.findRoomById(cinemaRoomID);

        if(theCinemaRoom ==null){
            throw new RuntimeException("The ID: "+ cinemaRoomID +" does not exist");
        }
        return
         theCinemaRoom;
    }


}
