package com.kianfinals.model;

public class User {

    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        
        if(password.length() < 8 ||
           !password.matches(".*[A-Z].*") ||
           !password.matches(".*\\d.*")) {

            throw new IllegalArgumentException(
                "Password must contain uppercase and number."
            );
        }
        
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if(password.length() < 8 ||
           !password.matches(".*[A-Z].*") ||
           !password.matches(".*\\d.*")) {

            throw new IllegalArgumentException(
                "Password must contain uppercase and number."
            );
        }

        this.password = password;
    }
}