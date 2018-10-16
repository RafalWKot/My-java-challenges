package com.rafalwkot.rps;

import java.util.List;

public class Battle {

    private final Variant variant;
    private final List<Scheme> scheme;
    private int quantityOfRounds;
    private int humanPoints;
    private int computerPoints;

    public Battle(Variant variant, List<Scheme> scheme) {
        this.variant = variant;
        this.scheme = scheme;
    }

    public void run() {

    }
}
