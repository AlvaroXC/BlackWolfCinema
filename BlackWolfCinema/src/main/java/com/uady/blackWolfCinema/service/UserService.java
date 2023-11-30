package com.uady.blackWolfCinema.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.uady.blackWolfCinema.model.User;
import com.uady.blackWolfCinema.validation.UserValidation;


public interface UserService extends UserDetailsService{
    public User findByUserName(String userName);

	void save(UserValidation userValidation);
}
