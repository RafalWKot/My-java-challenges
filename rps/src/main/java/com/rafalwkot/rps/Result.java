package com.rafalwkot.rps;

public enum Result {
    VICTORY("Zwycięstwo"),
    DRAW("Remis"),
    DEFEAT("Porażka");

    private String text;

    Result(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
