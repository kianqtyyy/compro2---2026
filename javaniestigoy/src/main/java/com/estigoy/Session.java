package com.game;

public class GameSession {
    private Player p1, p2;
    private int s1 = 0, s2 = 0;

    public GameSession(Player p1, Player p2) { this.p1 = p1; this.p2 = p2; }

    public int playRound(int move1, int move2) {
        GameMove m1 = create(move1);
        GameMove m2 = create(move2);
        int res = m1.compare(m2);
        if (res == 1) s1++; else if (res == -1) s2++;
        return res;
    }

    private GameMove create(int i) {
        return switch (i) { case 0 -> new Rock(); case 1 -> new Paper(); default -> new Scissors(); };
    }

    public void updateStats() {
        if (s1 > s2) { p1.addWin(); p2.addLoss(); }
        else if (s2 > s1) { p2.addWin(); p1.addLoss(); }
    }

    public String getSummary() { return p1.getUsername() + ":" + s1 + " | " + p2.getUsername() + ":" + s2; }
}