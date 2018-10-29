package com.rafalwkot.rps;

import java.util.List;
import java.util.stream.IntStream;

public class Game {

    List<Configuration> configurations;

    public Game(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public void play() {
        Variant variant = init();
        List<Scheme> scheme;
        if (variant == Variant.RPS) {
            scheme = configurations.get(0).getPossibleMoves(); //zaczytywanie z mapy
        } else {
            scheme = configurations.get(1).getPossibleMoves();
        }
        Duel duel = new Duel(variant, scheme);
        duel.run();
        System.out.println(LoadText.getText(Application.GAMEFILE, "#HISTORY"));
        int historyCondition=  Application.INPUT.nextInt();
        if(historyCondition == 1) {
            showHistory(duel);
        }

    }

    private Variant init() {
        System.out.println(LoadText.getText(Application.GAMEFILE, "#GAME_VARIANT"));
        IntStream.range(0, configurations.size())
                .forEach(i -> System.out.println(i + 1 + " " + configurations.get(i).getGameName()));
        System.out.println(LoadText.getText(Application.GAMEFILE, "#PLAYER_CHOICE"));
        Variant variant = Variant.valueOf(Application.INPUT.nextInt() - 1).orElseThrow(InputDataException::new);
        System.out.println("\n" + configurations.get(variant.getChoice()).getDescription());

        return variant;
    }

    private void showHistory(Duel duel) {
        for (int i = 0; i < duel.getRounds().size(); i++) {
            System.out.println(LoadText.getText(Application.GAMEFILE, "#NO_ROUND") +  i);
            System.out.print(LoadText.getText(Application.GAMEFILE, "#PLAYER_CHOICE") +
                            duel.getRounds().get(i).getHumanMove().getText() +
                            " vs " +
                            LoadText.getText(Application.GAMEFILE, "#COMPUTER_SYMBOL") +
                            duel.getRounds().get(i).getComputerMove().getText() +
                            "\n");

        }
    }
}
