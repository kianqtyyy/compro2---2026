package com.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    private String username;
    private String password;
    private int wins;
    private int losses;

    public Player() {} 

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.wins = 0;
        this.losses = 0;
    }

    public double getWinRate() {
        int total = wins + losses;
        if (total == 0) return 0.0;
        return ((double) wins / total) * 100;
    }

    public void addWin() { this.wins++; }
    public void addLoss() { this.losses++; }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }
    public int getLosses() { return losses; }
    public void setLosses(int losses) { this.losses = losses; }
}