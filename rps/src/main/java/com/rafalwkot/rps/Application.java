package com.rafalwkot.rps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static final String INTRODUCEFILE = "introduce.txt";
    public static final String RPSTEXTS = "rps_scheme.txt";
    public static final String RPSSLTEXTS = "rpssl_scheme.txt";
    public static String PLAYERNAME;

    public static void main(String[] args) {

        System.out.println(LoadConfiguration.getText(INTRODUCEFILE, "#GAME_NAME"));
        System.out.println(LoadConfiguration.getText(INTRODUCEFILE, "#AUTHOR_NAME"));
        System.out.print(LoadConfiguration.getText(INTRODUCEFILE, "#ASK_PLAYER_NAME"));

        Scanner scanner = new Scanner(System.in);
        PLAYERNAME = scanner.nextLine();

        System.out.println(LoadConfiguration.getText(INTRODUCEFILE, "#INTRODUCE") + " " + PLAYERNAME + "!");
        System.out.println(LoadConfiguration.getText(INTRODUCEFILE, "#BEGINNING"));

        List<Configuration> configurations = new ArrayList<>();
        configurations.add(new Configuration(RPSTEXTS));
        configurations.add(new Configuration(RPSSLTEXTS));

        boolean endGameCondition = true;
        while (endGameCondition) {
            Game game = new Game(configurations);
            game.play();

            System.out.println(LoadConfiguration.getText(INTRODUCEFILE, "#ASK_FOR_REPLAY"));
            if (scanner.nextInt() == 2) {
                endGameCondition = false;
            }
        }
        System.out.println(LoadConfiguration.getText(INTRODUCEFILE, "#GOODBYE"));
    }
}

