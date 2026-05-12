package com.SecureVault;

import com.securevault.model.*;
import com.securevault.repository.*;
import com.securevault.service.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args)
            throws Exception {

        Repository<Expense> expenseRepo =
                new InMemoryRepository<>();

        Repository<Bill> billRepo =
                new InMemoryRepository<>();

        expenseRepo.add(
                new Expense("Groceries", 1200));

        billRepo.add(
                new Bill(
                        "Electricity",
                        3500,
                        LocalDate.now().plusDays(5)
                ));

        FinanceService service =
                new FinanceService(
                        expenseRepo,
                        billRepo);

        service.showUpcomingBills();

        FileService.save(
                expenseRepo.getAll(),
                "expenses.json");

        System.out.println(
                "Data saved successfully."
        );
    }
}