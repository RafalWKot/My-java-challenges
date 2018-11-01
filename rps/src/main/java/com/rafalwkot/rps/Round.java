package com.rafalwkot.rps;

import java.util.Random;

public class Round {
    private TextProvider textProvider;
    private SchemeProvider scheme;
    private Result result;
    private Move humanMove;
    private Move computerMove;

    public Round(TextProvider textProvider, SchemeProvider scheme, Move humanMove) {
        this.textProvider = textProvider;
        this.scheme = scheme;
        this.humanMove = humanMove;
    }

    public void run() {
        setComputerMove();
        result = fight();
        System.out.println(Application.PLAYERNAME + ": " + humanMove.getText() +
                " " + textProvider.getText("COMPUTER_SYMBOL") + " " + computerMove.getText() +
                " " + textProvider.getText("RESULT") + result.getText());
    }

    private void setComputerMove() {  //??
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

    public Result getResult() {
        return result;
    }

    public Move getHumanMove() {
        return humanMove;
    }

    public Move getComputerMove() {
        return computerMove;
    }
}
