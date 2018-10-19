package com.rafalwkot.rps;

import java.util.*;

public class Round {

    private final List<Scheme> schemes;
    private Result result;
    private Move humanMove;
    private Move computerMove;

    public Round(List<Scheme> schemes) {
        this.schemes = schemes;
    }

    public void run() {
        addHumanMove();
        addComputerMove();
        setResult(fight());
        System.out.println();
        System.out.println(LoadText.getText(Application.GAMEFILE, "RESULT"));
    }

    private void addHumanMove() {
        System.out.println(LoadText.getText(Application.GAMEFILE, "SYMBOL"));
        for (int j = 0; j < schemes.size(); j++) {
            System.out.println(j + 1 + " " + schemes.get(j).getMove().getText());
        }
        System.out.println(LoadText.getText(Application.GAMEFILE, "PLAYER_SYMBOL"));
        Scanner scanner = new Scanner(System.in);
        setHumanMove(schemes.get(scanner.nextInt() - 1).getMove());
    }

    private void addComputerMove() {
        Random random = new Random();
        setComputerMove(schemes.get(random.nextInt(schemes.size())).getMove());
    }

    private Result fight() {
        if (humanMove.equals(computerMove)) {
            return Result.DRAW;
        } else if (getWinWith(humanMove).contains(computerMove)) {
            return Result.VICTORY;
        }
        return Result.DEFEAT;
    }

    public Result getResult() {
        return result;
    }

    private void setHumanMove(Move humanMove) {
        this.humanMove = humanMove;
    }

    private void setComputerMove(Move computerMove) {
        this.computerMove = computerMove;
    }

    private void setResult(Result result) {
        this.result = result;
    }

    private Set<Move> getWinWith(Move move) {
        for (Scheme scheme : schemes) {
            if (scheme.getMove().equals(move)) {
                return scheme.getWinWith();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return Objects.equals(result, round.result) &&
                humanMove == round.humanMove &&
                computerMove == round.computerMove &&
                Objects.equals(schemes, round.schemes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, humanMove, computerMove, schemes);
    }


}
