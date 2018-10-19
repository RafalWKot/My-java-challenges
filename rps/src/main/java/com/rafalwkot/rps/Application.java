package com.rafalwkot.rps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static final String RPSTEXTS = "rps_scheme";
    public static final String RPSSLTEXTS = "rpssl_scheme";
    public static final String GAMEFILE ="game.txt";
    public static String PLAYERNAME;

    public static void main(String[] args) {

        System.out.println(LoadText.getText(GAMEFILE, "#GAME_NAME"));
        System.out.println(LoadText.getText(GAMEFILE, "#AUTHOR_NAME"));
        System.out.print(LoadText.getText(GAMEFILE, "#ASK_PLAYER_NAME"));

        Scanner scanner = new Scanner(System.in);
        PLAYERNAME = scanner.nextLine();

        System.out.println(LoadText.getText(GAMEFILE, "#INTRODUCE") + " " + PLAYERNAME + "!");
        System.out.println(LoadText.getText(GAMEFILE, "#BEGINNING"));

        List<Configuration> configurations = new ArrayList<>();
        configurations.add(new Configuration(GAMEFILE, RPSTEXTS));
        configurations.add(new Configuration(GAMEFILE, RPSSLTEXTS));

        boolean endGameCondition = true;
        while (endGameCondition) {
            Game game = new Game(configurations);
            game.play();

            System.out.println(LoadText.getText(GAMEFILE, "#ASK_FOR_REPLAY"));
            if (scanner.nextInt() == 2) {
                endGameCondition = false;
            }
        }
        System.out.println(LoadText.getText(GAMEFILE, "#GOODBYE"));
    }
}

