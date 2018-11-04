package com.rafalwkot.rps.model;

import java.util.EnumMap;
import java.util.EnumSet;

public class SchemeCreator {

    public EnumMap<Move, EnumSet<Move>> getScheme(Variant variant) {

        EnumMap<Move, EnumSet<Move>> moveWithWin = new EnumMap<>(Move.class);
        if (variant.equals(Variant.RPSSL)) {
            moveWithWin.put(Move.ROCK, EnumSet.of(Move.SCISSORS, Move.LIZARD));
            moveWithWin.put(Move.PAPER, EnumSet.of(Move.ROCK, Move.SPOCK));
            moveWithWin.put(Move.SCISSORS, EnumSet.of(Move.PAPER, Move.LIZARD));
            moveWithWin.put(Move.SPOCK, EnumSet.of(Move.ROCK, Move.SCISSORS));
            moveWithWin.put(Move.LIZARD, EnumSet.of(Move.PAPER, Move.SPOCK));
        } else {
            moveWithWin.put(Move.ROCK, EnumSet.of(Move.SCISSORS));
            moveWithWin.put(Move.PAPER, EnumSet.of(Move.ROCK));
            moveWithWin.put(Move.SCISSORS, EnumSet.of(Move.PAPER));
        }
        return moveWithWin;
    }

}
