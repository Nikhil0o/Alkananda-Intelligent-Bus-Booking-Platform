package com.example.alkananda.dto;

import com.example.alkananda.entity.Role;

public class LoginResponse {

    private String token;
    private String email;
    private Role role;
    private Long userId;

    public LoginResponse(String token,Long userId, String email, Role role, Role userRole) {
        this.token = token;
        this.userId=userId;
        this.email = email;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}