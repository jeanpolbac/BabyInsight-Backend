package com.example.babyinsightbackend.models.response;

import com.example.babyinsightbackend.models.User;

public class LoginResponse {

    private String jwt;
    public User user;


    public LoginResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}