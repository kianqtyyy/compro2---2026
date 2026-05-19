package com.kianfinals.model;

import java.time.LocalDateTime;

class Expense {

    private String description;
    private double amount;
    private LocalDateTime timestamp;

    public Expense() {}

    public Expense(String description, double amount) {
        this.description = description;
        if(amount < 0) {
            throw new IllegalArgumentException(
                "Amount cannot be negative."
            );
        }
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public void setAmount(double amount) {

        if(amount < 0) {
            throw new IllegalArgumentException(
                "Amount cannot be negative."
            );
        }

        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}