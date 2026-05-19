package com.SecureVault;

import java.time.LocalDate;

import com.SecureVault.model.Bill;
import com.SecureVault.model.Expense;
import com.SecureVault.repository.InMemoryRepository;
import com.SecureVault.repository.Repository;

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

    private static class FinanceService {

        public FinanceService(Repository<Expense> expenseRepo, Repository<Bill> billRepo) {
        }

        private void showUpcomingBills() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private static class FileService {

        public static void save(java.util.List<Expense> items, String fileName)
                throws java.io.IOException {
            java.nio.file.Files.writeString(
                    java.nio.file.Path.of(fileName),
                    items.toString());
        }
    }
}