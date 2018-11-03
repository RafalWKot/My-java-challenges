package com.rafalwkot.rps.model;

import com.rafalwkot.rps.model.Move;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public class SchemeProvider {

    private EnumMap<Move, EnumSet<Move>> moveWithWin;

    public SchemeProvider(EnumMap<Move, EnumSet<Move>> moveWithWin) {
        this.moveWithWin = moveWithWin;
    }

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

    public EnumSet<Move> getMoves() { //zwrócić zwykłego seta
        return moveWithWin.entrySet().stream()
                .map(i -> i.getKey())
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(Move.class)));
    }

    public EnumSet getDefeatedMoves(Move move) {
        return moveWithWin.get(move);
    }
}
