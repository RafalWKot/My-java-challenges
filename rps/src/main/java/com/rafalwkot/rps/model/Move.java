package com.rafalwkot.rps.model;

import java.util.Arrays;
import java.util.Optional;

public enum Move {
    ROCK("kamień", 1),
    PAPER("papier", 2),
    SCISSORS("nożyczki", 3),
    SPOCK("spock", 4),
    LIZARD("jaczurka", 5);

    private String text;
    private int choice;

    Move(String text, int choice) {
        this.text = text;
        this.choice = choice;
    }

    public static Optional<Move> valueOf(int choice) {
        return Arrays.stream(values())
                .filter(move -> move.choice == choice)
                .findFirst();
    }

    public String getText() {
        return text;
    }
}
