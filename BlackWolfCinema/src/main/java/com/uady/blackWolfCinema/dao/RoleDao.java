package com.uady.blackWolfCinema.dao;

import com.uady.blackWolfCinema.model.Role;
public interface RoleDao {
    public Role findRoleByName(String theRoleName);
}
