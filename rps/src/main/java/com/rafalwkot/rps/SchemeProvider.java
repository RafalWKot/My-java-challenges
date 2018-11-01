package com.rafalwkot.rps;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class SchemeProvider {

    protected EnumMap<Move, EnumSet<Move>> moveWithWin = new EnumMap<>(Move.class);

    public String keysToString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Map.Entry move : moveWithWin.entrySet()) {
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(((Move) move.getKey()).getText());
            stringBuilder.append("\n");
            i++;
        }
        return stringBuilder.toString();
    }

    public EnumSet getMoves() {
        return moveWithWin.entrySet().stream()
                .map(i -> i.getKey())
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Move.class)));
    }

    public EnumSet getDefeatedMoves(Move move) {
        return moveWithWin.get(move);
    }
}
