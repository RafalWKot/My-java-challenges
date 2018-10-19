package com.rafalwkot.rps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static final String RPSTEXTS = "rps_text.txt";
    public static final String RPSSCHEME = "rps_scheme";
    public static final String RPSSLTEXTS = "rps_text.txt";
    public static final String RPSSLSCHEME = "rpssl_scheme";
    public static final String GAMEFILE ="game_texts.txt";
    public static String PLAYERNAME;
    public static Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(LoadText.getText(GAMEFILE, "#GAME_NAME"));
        System.out.println(LoadText.getText(GAMEFILE, "#AUTHOR_NAME"));

        System.out.print(LoadText.getText(GAMEFILE, "#ASK_PLAYER_NAME"));
        PLAYERNAME = INPUT.nextLine();

        System.out.println();
        System.out.println(LoadText.getText(GAMEFILE, "#INTRODUCE") + " " + PLAYERNAME + "!");
        System.out.println(LoadText.getText(GAMEFILE, "#BEGINNING"));

        List<Configuration> configurations = new ArrayList<>();
        configurations.add(new Configuration(RPSTEXTS, RPSSCHEME));
        configurations.add(new Configuration(RPSSLTEXTS, RPSSLSCHEME));

        boolean endGameCondition = true;
        while (endGameCondition) {
            Game game = new Game(configurations);
            game.play();

            System.out.println(LoadText.getText(GAMEFILE, "#ASK_FOR_REPLAY"));
            if (INPUT.nextInt() == 2) {
                endGameCondition = false;
            }
        }
        System.out.println(LoadText.getText(GAMEFILE, "#GOODBYE"));
    }
}

