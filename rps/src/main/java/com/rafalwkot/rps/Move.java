package com.rafalwkot.rps;

public enum Move {
    ROCK("kamień"),
    PAPER("papier"),
    SCISSORS("nożyczki"),
    SPOCK("spock"),
    LIZARD("jaszczurka");

    private String text;

    Move(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
