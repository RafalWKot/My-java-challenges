package com.rafalwkot.rps;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private TextProvider textProvider;
    private List<Round> rounds = new ArrayList<>();
    private int humanPoints;
    private int computerPoints;

    public Game(TextProvider textProvider) {
        this.textProvider = textProvider;
    }

    public void play(Round round) {
        rounds.add(round);
        round.run();
        addPoint(round.getResult());
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

    public void showActualResult() {
        System.out.print(textProvider.getText("CURRENT_RESULT"));
        System.out.println(Application.PLAYERNAME + " " + humanPoints + " " +
                "Computer " + computerPoints);
    }

    public void showHistory() {
        for (int i = 0; i < rounds.size(); i++) {
            System.out.println(textProvider.getText("NO_ROUND") + i + " " +
                    textProvider.getText("PLAYER_CHOICE") +
                    rounds.get(i).getHumanMove().getText() +
                    " vs " +
                    textProvider.getText("COMPUTER_SYMBOL") +
                    rounds.get(i).getComputerMove().getText() +
                    "\n");

        }
    }

    public boolean isHumanWin() {
        if (humanPoints > computerPoints) {
            return true;
        }
        return false;
    }
}
