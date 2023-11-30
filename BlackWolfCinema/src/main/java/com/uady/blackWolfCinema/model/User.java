package com.uady.blackWolfCinema.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username")
    private String userName;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "lastname")
    private String lastname;    
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idrole", referencedColumnName = "idrole")
    private Role role;


    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,
         CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Receipt> receipts;

    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
 
}