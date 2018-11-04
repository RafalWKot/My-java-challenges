package com.rafalwkot.rps.model;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public class SchemeProvider {

    private EnumMap<Move, EnumSet<Move>> moveWithWin;

    public SchemeProvider(EnumMap<Move, EnumSet<Move>> moveWithWin) {
        this.moveWithWin = moveWithWin;
    }

    public EnumSet<Move> getMoves() {
        return moveWithWin.entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Move.class)));
    }

    EnumSet getDefeatedMoves(Move move) {
        return moveWithWin.get(move);
    }
}
