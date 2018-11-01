package com.rafalwkot.rps;

import java.util.Scanner;

public class Application {

    public static final String GAMEFILETEXTS = "game_texts";
    public static String PLAYERNAME;

    public static void main(String[] args) {
        TextProvider textProvider = new FileTextProvider();

        try {
            textProvider.loadTexts(GAMEFILETEXTS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(textProvider.getText("GAME_NAME"));
        System.out.println(textProvider.getText("AUTHOR_NAME"));
        System.out.print(textProvider.getText("ASK_PLAYER_NAME"));

        Scanner input = new Scanner(System.in);
        PLAYERNAME = input.nextLine();

        System.out.println("\n" + textProvider.getText("INTRODUCE") + " " + PLAYERNAME + "!");
        System.out.println(textProvider.getText("BEGINNING"));

        boolean endGameCondition = true;
        while (endGameCondition) {
            System.out.println(textProvider.getText("GAME_VARIANT"));
            System.out.println(textProvider.getText("RPS_GAME_NAME"));
            System.out.println(textProvider.getText("RPSSL_GAME_NAME"));
            System.out.print(textProvider.getText("PLAYER_CHOICE"));
            Variant variant = Variant.valueOf(input.nextInt()).orElse(Variant.RPS);  //wyjątek

            SchemeProvider scheme;
            if (variant == Variant.RPS) {
                scheme = new RpsProvider();
                System.out.println("\n" + textProvider.getText("RPS_ABOUT_BATTLE"));
            } else {
                scheme = new RpsslProvider();
                System.out.println("\n" + textProvider.getText("RPSSL_ABOUT_BATTLE"));
            }

            System.out.print(textProvider.getText("QUANTITY_ROUNDS"));
            int numberOfRounds = input.nextInt();
            Game game = new Game(textProvider);

            for (int i = 0; i < numberOfRounds; i++) {
                System.out.println("\n" + textProvider.getText("NO_ROUND") + (i + 1));
                System.out.println(textProvider.getText("SYMBOL"));
                System.out.println(scheme.keysToString());
                System.out.print(textProvider.getText("PLAYER_CHOICE"));
                game.play(new Round(textProvider, scheme, Move.valueOf(input.nextInt()).orElse(Move.ROCK)));  //wyjątek
                game.showActualResult();
            }

            if (game.isHumanWin()) {
                System.out.println(textProvider.getText("CONGRATULATIONS") + "\n");
            } else {
                System.out.println(textProvider.getText("DEFEAT") + "\n");
            }

            System.out.println(textProvider.getText("HISTORY"));
            System.out.println(textProvider.getText("PLAYER_CHOICE"));
            if (input.next().equals("1")) {
                game.showHistory();
            }

            System.out.println(textProvider.getText("ASK_FOR_REPLAY"));
            if (!(input.next().equals("1"))) {
                endGameCondition = false;
            }
        }
        System.out.println(textProvider.getText("GOODBYE"));
    }
}

