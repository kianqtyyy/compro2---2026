package com.game;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {
    private static List<Player> userList = new ArrayList<>();
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String FILE_PATH = "src/main/resources/users.json";

    public static void main(String[] args) throws IOException {
        loadData();
        ServerSocket server = new ServerSocket(8080);
        System.out.println(">>> SERVER: Waiting for players...");

        Socket s1 = server.accept();
        DataOutputStream out1 = new DataOutputStream(s1.getOutputStream());
        DataInputStream in1 = new DataInputStream(s1.getInputStream());
        Player p1 = handleAuth(in1, out1);

        Socket s2 = server.accept();
        DataOutputStream out2 = new DataOutputStream(s2.getOutputStream());
        DataInputStream in2 = new DataInputStream(s2.getInputStream());
        Player p2 = handleAuth(in2, out2);

        boolean sessionActive = true;
        while (sessionActive) {
            int p1Match = 0, p2Match = 0;
            broadcast(out1, out2, "\n--- MATCH START: " + p1.getUsername() + " vs " + p2.getUsername() + " ---");

            // Strict 10-Round Loop
            for (int r = 1; r <= 10; r++) {
                String prompt = "\nROUND " + r + " | [0]Rock [1]Paper [2]Scissors";
                out1.writeUTF(prompt); out2.writeUTF(prompt);

                int c1 = in1.readInt();
                int c2 = in2.readInt();

                GameMove m1 = MoveFactory.create(c1);
                GameMove m2 = MoveFactory.create(c2);
                int res = m1.compare(m2);

                if (res == 1) { p1Match++; out1.writeUTF("ROUND: Win"); out2.writeUTF("ROUND: Loss"); }
                else if (res == -1) { p2Match++; out1.writeUTF("ROUND: Loss"); out2.writeUTF("ROUND: Win"); }
                else { out1.writeUTF("ROUND: Tie"); out2.writeUTF("ROUND: Tie"); }
            }

            // Update stats after 10 rounds
            if (p1Match > p2Match) { p1.addWin(); p2.addLoss(); }
            else if (p2Match > p1Match) { p2.addWin(); p1.addLoss(); }
            saveData();

            // Display Leaderboard
            String summary = "\nMATCH ENDED: " + p1Match + " - " + p2Match + getLeaderboard();
            broadcast(out1, out2, summary + "\nPlay again? [1]Yes [0]No");

            // REPLAY LOGIC: Both must say Yes (1) to continue
            int choice1 = in1.readInt();
            int choice2 = in2.readInt();
            if (choice1 == 0 || choice2 == 0) {
                sessionActive = false;
            }
        }
        broadcast(out1, out2, "FINISHED");
        server.close();
    }

    private static Player handleAuth(DataInputStream in, DataOutputStream out) throws IOException {
        while (true) {
            int mode = in.readInt();
            String u = in.readUTF().trim(); String p = in.readUTF().trim();
            Optional<Player> exists = userList.stream().filter(pl -> pl.getUsername().equalsIgnoreCase(u)).findFirst();
            if (mode == 1 && exists.isPresent() && exists.get().getPassword().equals(p)) {
                out.writeUTF("SUCCESS"); return exists.get();
            } else if (mode == 2 && !exists.isPresent()) {
                Player n = new Player(u, p); userList.add(n); saveData();
                out.writeUTF("SUCCESS"); return n;
            }
            out.writeUTF("FAIL");
        }
    }

    private static String getLeaderboard() {
        userList.sort((a, b) -> Integer.compare(b.getWins(), a.getWins()));
        StringBuilder sb = new StringBuilder("\n\n--- HALL OF FAME ---\n");
        sb.append(String.format("%-12s | %-3s | %-3s | %-6s\n", "NAME", "W", "L", "WIN%"));
        for (Player p : userList) {
            sb.append(String.format("%-12s | %-3d | %-3d | %.1f%%\n", 
                p.getUsername(), p.getWins(), p.getLosses(), p.getWinRate()));
        }
        return sb.toString();
    }

    private static void broadcast(DataOutputStream o1, DataOutputStream o2, String msg) throws IOException {
        o1.writeUTF(msg); o2.writeUTF(msg);
    }

    private static void loadData() {
        try {
            File f = new File(FILE_PATH);
            if (f.exists()) userList = mapper.readValue(f, new TypeReference<List<Player>>(){});
        } catch (Exception e) { userList = new ArrayList<>(); }
    }

    private static void saveData() {
        try {
            File f = new File(FILE_PATH);
            if (f.getParentFile() != null) f.getParentFile().mkdirs();
            mapper.writerWithDefaultPrettyPrinter().writeValue(f, userList);
        } catch (Exception e) { }
    }
}