package com.rafalwkot.rps;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Game {

    public static final String GAMESETTING = "game.txt";
    List<Configuration> configurations;
    LoadConfiguration loadConfiguration;
    Scanner scanner;

    public Game(List<Configuration> configurations, LoadConfiguration loadConfiguration) {
        this.configurations = configurations;
        this.loadConfiguration = loadConfiguration;
        scanner = new Scanner(System.in);
    }

    public void play() {
        Variant variant = init();
        List<Scheme> scheme;
        if (variant == Variant.RPS) {
            scheme = configurations.get(0).getPossibleMoves();
        } else {
            scheme = configurations.get(1).getPossibleMoves();
        }

        Battle battle = new Battle(variant, scheme);
        battle.run();
        //readHistory(battle);
    }

    private Variant init() {
        System.out.println(loadConfiguration.getText(GAMESETTING, "#GAME_VARIANT"));
        IntStream.range(0, configurations.size())
                .forEach(i -> System.out.println(i + 1 + " " + configurations.get(i).getGameName()));
        System.out.println(loadConfiguration.getText(GAMESETTING, "#PLAYER_CHOICE"));
        Variant variant = Variant.valueOf(scanner.nextInt() - 1).orElseThrow(NullPointerException::new);
        System.out.println("\n" + configurations.get(variant.getChoice()).getDescription());

        return variant;
    }

    private void readHistory() {}
}
