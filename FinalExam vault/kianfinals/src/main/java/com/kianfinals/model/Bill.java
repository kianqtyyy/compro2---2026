package com.kianfinals.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Bill {

    private String name;
    private double amount;
    private LocalDate dueDate;

    public Bill() {}

    public Bill(String name, double amount, LocalDate dueDate) {
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public long getDaysUntilDue() {
        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                dueDate
        );
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}