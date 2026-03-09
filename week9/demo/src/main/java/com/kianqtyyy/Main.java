package com.kianqtyyy;

import java.io.InputStream;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        InputStream input = Main.class
                .getClassLoader()
                .getResourceAsStream("person.json");

        Scanner scanner = new Scanner(input).useDelimiter("\\A");
        String jsonText = scanner.next();
        scanner.close();

        JSONObject obj = new JSONObject(jsonText);

        System.out.println("First Name: " + obj.getString("firstName"));
        System.out.println("Last Name: " + obj.getString("lastName"));
        System.out.println("Age: " + obj.getInt("age"));
        System.out.println("Email: " + obj.getString("emailAddress"));
        System.out.println("Phone: " + obj.getString("phoneNumber"));
        System.out.println("Date of Birth: " + obj.getString("dateOfBirth"));
        System.out.println("Home Address: " + obj.getString("homeAddress"));
        System.out.println("Employed: " + obj.getBoolean("isEmployed"));
        System.out.println("Nationality: " + obj.getString("nationality"));
        System.out.println("Gender: " + obj.getString("gender"));
    }
}