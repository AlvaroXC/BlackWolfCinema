package com.uady.blackWolfCinema.dao;


import java.util.List;

import com.uady.blackWolfCinema.model.CinemaRoom;

public interface CinemaRoomDao {
    CinemaRoom getRoomById(int cinemaRoomId);
    List<CinemaRoom> getAllRooms();

}
