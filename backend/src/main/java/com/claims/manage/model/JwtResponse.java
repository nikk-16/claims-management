package com.claims.manage.model;


import com.claims.manage.domain.Users;

public class JwtResponse {
    private String token;
    private Users user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
