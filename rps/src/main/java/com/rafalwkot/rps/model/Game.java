package com.rafalwkot.rps.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Round> rounds = new ArrayList<>();
    private int humanPoints;
    private int computerPoints;

    public Result play(Round round) {
        rounds.add(round);
        Result result = round.run();
        addPoint(result);
        return result;
    }

    private void addPoint(Result result) {
        if (result.equals(Result.VICTORY)) {
            humanPoints++;
        } else if (result.equals(Result.DEFEAT)) {
            computerPoints++;
        } else {
            humanPoints++;
            computerPoints++;
        }
    }

    public int getHumanPoints() {
        return humanPoints;
    }

    public int getComputerPoints() {
        return computerPoints;
    }

    public boolean isHumanWin() {
        return (humanPoints > computerPoints);
    }

    public Move getHumanMove(int i) {
        return rounds.get(i).getHumanMove();
    }

    public Move getComputerMove(int i) {
        return  rounds.get(i).getComputerMove();
    }
}
