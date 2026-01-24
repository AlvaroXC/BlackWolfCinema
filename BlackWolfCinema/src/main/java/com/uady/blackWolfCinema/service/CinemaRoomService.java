package com.uady.blackWolfCinema.service;


import java.util.List;

import com.uady.blackWolfCinema.model.CinemaRoom;

public interface CinemaRoomService {
    CinemaRoom findById(int cinemaRoomId);
    List<CinemaRoom> getAll();
}
