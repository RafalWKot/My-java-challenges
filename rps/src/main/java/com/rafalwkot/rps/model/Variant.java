package com.rafalwkot.rps.model;

import java.util.Arrays;
import java.util.Optional;

public enum Variant {
    RPS(1),
    RPSSL(2);

    private int choice;

    Variant(int choice) {
        this.choice = choice;
    }

    public static Optional<Variant> valueOf(int value) {
        return Arrays.stream(values())
                .filter(variant -> variant.choice == value)
                .findFirst();
    }
}

