package com.uady.blackWolfCinema.dao;

import com.uady.blackWolfCinema.model.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
    
}
