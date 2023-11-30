package com.uady.blackWolfCinema.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uady.blackWolfCinema.dao.RoleDao;
import com.uady.blackWolfCinema.dao.UserDao;
import com.uady.blackWolfCinema.model.User;
import com.uady.blackWolfCinema.validation.UserValidation;




@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

	private RoleDao roleDao;

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	public void save(UserValidation userValidation) {
		User user = new User();

		// assign user details to the user object
		user.setUserName(userValidation.getUserName());
		user.setPassword(passwordEncoder.encode(userValidation.getPassword()));
		user.setName(userValidation.getFirstName());
		user.setLastname(userValidation.getLastName());
		user.setEmail(userValidation.getEmail());

		// give user default role of "employee"
		user.setRole(roleDao.findRoleByName("ROLE_CUSTOMER"));
		// save user in the database
		userDao.save(user);
	}

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userDao.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                Collections.singletonList(authority));
    }

}
