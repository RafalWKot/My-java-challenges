package com.rafalwkot.rps;

import java.util.Arrays;
import java.util.Optional;

public enum Variant {
    RPS(0),
    RPSSL(1);

    private int choice;

    Variant(int choice) {
        this.choice = choice;
    }

    public static Optional<Variant> valueOf(int value) {
        return Arrays.stream(values())
                .filter(variant -> variant.choice == value)
                .findFirst();
    }

    public int getChoice() {
        return choice;
    }
}
