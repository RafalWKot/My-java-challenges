package com.rafalwkot.rps;

public enum Player {
    Human ("człowiek"),
    Computer ("komputer");

    private String text;

    Player(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
