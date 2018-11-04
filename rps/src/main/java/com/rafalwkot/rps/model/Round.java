package com.rafalwkot.rps.model;

import java.util.Random;

public class Round {
    private SchemeProvider scheme;
    private Move humanMove;
    private Move computerMove;

    public Round(SchemeProvider scheme, Move humanMove) {
        this.scheme = scheme;
        this.humanMove = humanMove;
    }

    Result run() {
        setComputerMove();
        return fight();
    }

    private void setComputerMove() {
        computerMove = getRandomMove();
    }

    private Move getRandomMove() {
        Random random = new Random();
        return (Move) scheme.getMoves().stream()
                .toArray()[random.nextInt(scheme.getMoves().size())];
    }

    private Result fight() {
        if (humanMove.equals(computerMove)) {
            return Result.DRAW;
        } else if (scheme.getDefeatedMoves(humanMove).contains(computerMove)) {
            return Result.VICTORY;
        }
        return Result.DEFEAT;
    }

    Move getHumanMove() {
        return humanMove;
    }

    Move getComputerMove() {
        return computerMove;
    }
}
