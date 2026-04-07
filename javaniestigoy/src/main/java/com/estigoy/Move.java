package com.game;



public abstract class GameMove {
    public abstract String getName();
    public abstract int compare(GameMove other); // 1: Win, -1: Loss, 0: Tie
}

class Rock extends GameMove {
    public String getName() { return "ROCK"; }
    public int compare(GameMove other) {
        if (other instanceof Rock) return 0;
        return (other instanceof Scissors) ? 1 : -1;
    }
}

class Paper extends GameMove {
    public String getName() { return "PAPER"; }
    public int compare(GameMove other) {
        if (other instanceof Paper) return 0;
        return (other instanceof Rock) ? 1 : -1;
    }
}

class Scissors extends GameMove {
    public String getName() { return "SCISSORS"; }
    public int compare(GameMove other) {
        if (other instanceof Scissors) return 0;
        return (other instanceof Paper) ? 1 : -1;
    }
}

// THIS FIXES YOUR ERROR: The Factory Method
class MoveFactory {
    public static GameMove create(int choice) {
        return switch (choice) {
            case 0 -> new Rock();
            case 1 -> new Paper();
            case 2 -> new Scissors();
            default -> new Rock();
        };
    }
}