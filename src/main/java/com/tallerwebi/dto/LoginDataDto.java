package com.tallerwebi.dto;

public class LoginDataDto {
    private String email;
    private String password;

    public LoginDataDto() {
    }

    public LoginDataDto(String email, String password) {
        this.email = email;
        this.password = password;
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
}

