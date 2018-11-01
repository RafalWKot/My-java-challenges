package com.rafalwkot.rps;

import java.util.EnumSet;

public class RpsProvider extends SchemeProvider {

    RpsProvider() {
        moveWithWin.put(Move.ROCK, EnumSet.of(Move.SCISSORS));
        moveWithWin.put(Move.PAPER, EnumSet.of(Move.ROCK));
        moveWithWin.put(Move.SCISSORS, EnumSet.of(Move.PAPER));
    }

}
