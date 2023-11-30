package com.uady.blackWolfCinema.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uady.blackWolfCinema.dao.CinemaRoomDao;
import com.uady.blackWolfCinema.model.CinemaRoom;

@Service
public class CinemaRoomServiceImp implements CinemaRoomService{
    private final CinemaRoomDao cinemaRoomDao;

    @Autowired
    public CinemaRoomServiceImp(CinemaRoomDao cinemaRoomDao){
        this.cinemaRoomDao= cinemaRoomDao;
    }
    @Override
    public CinemaRoom findRoomById(int theCinemaRoomID) {
        return cinemaRoomDao.getRoomById(theCinemaRoomID);
    }
    @Override
    public List<CinemaRoom> getAllRooms() {
        return cinemaRoomDao.getAllRooms();
        
    }


}
