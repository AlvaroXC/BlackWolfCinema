package com.uady.blackWolfCinema.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.uady.blackWolfCinema.model.CinemaRoom;

@Repository
public class CinemaRoomDaoImp implements CinemaRoomDao{

    private EntityManager entityManager;

    @Autowired
    public CinemaRoomDaoImp(EntityManager entityManager){
        this.entityManager= entityManager;
    }
    @Override
    public CinemaRoom getRoomById(int cinemaRoomId) {
        CinemaRoom theCinemaRoom = entityManager.find(CinemaRoom.class, cinemaRoomId);
        return theCinemaRoom;
    }
    @Override
    public List<CinemaRoom> getAllRooms() {
        TypedQuery<CinemaRoom> thQuery = entityManager.createQuery("from CinemaRoom", CinemaRoom.class);
        return thQuery.getResultList();
    }
}
