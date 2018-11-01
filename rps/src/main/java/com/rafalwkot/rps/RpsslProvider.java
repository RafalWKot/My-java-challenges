package com.rafalwkot.rps;

import java.util.EnumSet;

public class RpsslProvider extends SchemeProvider {

    RpsslProvider() {
        moveWithWin.put(Move.ROCK, EnumSet.of(Move.SCISSORS, Move.LIZARD));
        moveWithWin.put(Move.PAPER, EnumSet.of(Move.ROCK, Move.SPOCK));
        moveWithWin.put(Move.SCISSORS, EnumSet.of(Move.PAPER, Move.LIZARD));
        moveWithWin.put(Move.SPOCK, EnumSet.of(Move.ROCK, Move.SCISSORS));
        moveWithWin.put(Move.LIZARD, EnumSet.of(Move.PAPER, Move.SPOCK));
    }
}
