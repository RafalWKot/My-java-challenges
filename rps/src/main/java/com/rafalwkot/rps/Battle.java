package com.rafalwkot.rps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle {

    private final Variant variant;
    private final List<Scheme> scheme;
    private List<Round> rounds;
    private int quantityOfRounds;
    private int humanPoints;
    private int computerPoints;

    public Battle(Variant variant, List<Scheme> scheme) {
        this.variant = variant;
        this.scheme = scheme;
        rounds = new ArrayList<>();
    }

    public void run() {
        addQuantityOfRounds();
        for (int i = 0; i < quantityOfRounds; i++) {
            System.out.println(LoadText.getText(Application.GAMEFILE, "#NO_ROUND") + i + 1);
            rounds.add(new Round(scheme));
            rounds.get(i).run();
            addPoint(rounds.get(i).getResult());
            printResult(i);
        }
    }

    private void addQuantityOfRounds() {
        System.out.println(LoadText.getText(Application.GAMEFILE, "#QUANTITY_ROUNDS"));
        Scanner scanner = new Scanner(System.in);
        quantityOfRounds = scanner.nextInt();
    }

    private void addPoint(Result result) {
        if (result.equals(Result.VICTORY)) {
            humanPoints++;
        } else if (result.equals(Result.DEFEAT)) {
            computerPoints++;
        }
    }

    private void printResult(int noRound) {
        if (noRound < quantityOfRounds - 1) {
            System.out.println(LoadText.getText(Application.GAMEFILE, "#CURRENT_RESULT") + toString());
        } else {
            System.out.println(LoadText.getText(Application.GAMEFILE, "#END_RESULT") + toString());
            if (humanPoints > computerPoints) {
                System.out.println(LoadText.getText(Application.GAMEFILE, "#CONGRATULATIONS"));
            } else {
                System.out.println(LoadText.getText(Application.GAMEFILE, "#DEFEAT"));
            }
        }
    }

    @Override
    public String toString() {
        return Application.PLAYERNAME + ": " + humanPoints +
                " vs " +
                Player.Computer.getText() + ": " + computerPoints;
    }
}
