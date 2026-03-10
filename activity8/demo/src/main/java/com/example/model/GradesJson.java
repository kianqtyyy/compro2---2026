package com.example.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GradesJson {

    static String[] subjects = new String[50];
    static double[][] grades = new double[50][3];
    static int count = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMAIN MENU");
            System.out.println("[1] Enter Subject Grades");
            System.out.println("[2] Display Grades");
            System.out.println("[3] Save to JSON");
            System.out.println("[4] Exit");
            System.out.print("Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                enterGrades(sc);
            }
            else if (choice == 2) {
                displayGrades();
            }
            else if (choice == 3) {
                writeJSON();
            }

        } while (choice != 4);

        System.out.println("Program terminated.");
    }

    public static void enterGrades(Scanner sc) {

        System.out.print("Enter Subject: ");
        subjects[count] = sc.nextLine();

        System.out.print("Enter Prelim: ");
        grades[count][0] = sc.nextDouble();

        System.out.print("Enter Midterm: ");
        grades[count][1] = sc.nextDouble();

        System.out.print("Enter Finals: ");
        grades[count][2] = sc.nextDouble();

        sc.nextLine();
        count++;
    }

    public static void displayGrades() {

        System.out.println("\nSUBJECT GRADES");

        for (int i = 0; i < count; i++) {

            System.out.println(subjects[i] +
                    " | Prelim: " + grades[i][0] +
                    " | Midterm: " + grades[i][1] +
                    " | Finals: " + grades[i][2]);
        }
    }

    public static void writeJSON() {

        StringBuilder sb = new StringBuilder();

        sb.append("{\n");
        sb.append("  \"subjects\": [\n");

        for (int i = 0; i < count; i++) {

            sb.append("    {\n");
            sb.append("      \"name\": \"").append(subjects[i]).append("\",\n");
            sb.append("      \"prelim\": ").append(grades[i][0]).append(",\n");
            sb.append("      \"midterm\": ").append(grades[i][1]).append(",\n");
            sb.append("      \"finals\": ").append(grades[i][2]).append("\n");
            sb.append("    ");

            if (i < count - 1) {
                sb.append("},\n");
            } else {
                sb.append("}\n");
            }
        }

        sb.append("  ]\n");
        sb.append("}");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.json"))) {

            bw.write(sb.toString());
            bw.flush();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nSaved to data.json");
    }
}