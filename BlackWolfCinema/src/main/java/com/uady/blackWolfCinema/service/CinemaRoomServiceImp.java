package com.uady.blackWolfCinema.service;


import java.util.List;
import java.util.Optional;

import com.uady.blackWolfCinema.repository.CinemaRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uady.blackWolfCinema.model.CinemaRoom;

@Service
public class CinemaRoomServiceImp implements CinemaRoomService{


    private final CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    public CinemaRoomServiceImp(CinemaRoomRepository cinemaRoomRepository){
        this.cinemaRoomRepository = cinemaRoomRepository;
    }
    @Override
    public CinemaRoom findById(int cinemaRoomId) {
        Optional<CinemaRoom> result = cinemaRoomRepository.findById(cinemaRoomId);
        CinemaRoom cinemaRoom = null;

        if(result.isEmpty()){
            return null;
        }
        cinemaRoom = result.get();
        return cinemaRoom ;
    }

    @Override
    public List<CinemaRoom> getAll() {
        return cinemaRoomRepository.findAll();
    }
}
