package com.example.model;

public class LoginView {
    private String rememberme;
    private String username;

    public LoginView(String rememberme, String username) {
        this.rememberme = rememberme;
        this.username = username;
    }

    public String getRememberme() {
        return rememberme;
    }

    public void setRememberme(String rememberme) {
        this.rememberme = rememberme;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
