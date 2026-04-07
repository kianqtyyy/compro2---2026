package com.game;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Socket s = new Socket("192.168.109.58", 8080);
             DataOutputStream out = new DataOutputStream(s.getOutputStream());
             DataInputStream in = new DataInputStream(s.getInputStream())) {

            boolean ready = false;
            while (!ready) {
                System.out.println("\n[1]Login [2]Signup");
                System.out.print("Choice: "); int m = Integer.parseInt(sc.nextLine());
                System.out.print("User: "); String u = sc.nextLine();
                System.out.print("Pass: "); String p = sc.nextLine();
                out.writeInt(m); out.writeUTF(u); out.writeUTF(p);
                if (in.readUTF().equals("SUCCESS")) ready = true;
                else System.out.println(">>> Error: Invalid credentials.");
            }

            while (true) {
                String msg = in.readUTF();
                System.out.println(msg);

                if (msg.contains("ROUND ")) {
                    System.out.print("Move (0-Rock, 1-Paper, 2-Scissors): ");
                    out.writeInt(Integer.parseInt(sc.nextLine()));
                } else if (msg.contains("Play again?")) {
                    System.out.print("Replay (1-Yes, 0-No): ");
                    out.writeInt(Integer.parseInt(sc.nextLine()));
                }

                if (msg.contains("FINISHED")) break;
            }
        } catch (Exception e) { System.out.println("Connection closed."); }
    }
}