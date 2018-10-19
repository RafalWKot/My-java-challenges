package com.rafalwkot.rps;

import java.util.*;

public class LoadScheme {

    private static ResourceBundle rb;

    public static List<Scheme> getScheme(String fileName) {
        rb = ResourceBundle.getBundle(fileName);
        List<Scheme> schemes = new ArrayList<>();
        int noMoves = Integer.valueOf(rb.getString("NO_MOVES"));
        int noOpponent = Integer.valueOf(rb.getString("NO_OPPONENT"));
        for (int i = 0; i < noMoves; i++) {
            Move move = Move.valueOf(rb.getString("MOVE_" + i));
            Set<Move> winWith = new HashSet<>();
            Set<Move> defeatWith = new HashSet<>();
            for (int j = 0; j < noOpponent; j++) {
                winWith.add(Move.valueOf(rb.getString("MOVE_" + i + "_WIN_" + j)));
                defeatWith.add(Move.valueOf(rb.getString("MOVE_" + i + "_DEFEAT_" + j)));
            }
            schemes.add(new Scheme(move, winWith, defeatWith));
        }
        return schemes;
    }
}


