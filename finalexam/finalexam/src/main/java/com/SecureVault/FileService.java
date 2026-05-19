/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.SecureVault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.SecureVault.model.Expense;

class FileService {

    static void save(List<Expense> all, String expensesjson) {
        if (expensesjson == null || expensesjson.isBlank()) {
            throw new IllegalArgumentException("File path must not be null or empty.");
        }
        try {
            Path path = Paths.get(expensesjson);
            String content = all == null ? "[]" : all.toString();
            Files.writeString(path, content);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to save expenses to " + expensesjson, ex);
        }
    }

}
