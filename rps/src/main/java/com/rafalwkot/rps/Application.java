package com.rafalwkot.rps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static final String INTRODUCEFILE = "introduce.txt";
    public static final String RPSTEXTS = "rps_scheme.txt";
    public static final String RPSSLTEXTS = "rpssl_scheme.txt";

    public static void main(String[] args) {

        LoadConfiguration loadConfiguration = new LoadConfiguration();
        System.out.println(loadConfiguration.getText(INTRODUCEFILE, "#GAME_NAME"));
        System.out.println(loadConfiguration.getText(INTRODUCEFILE, "#AUTHOR_NAME"));
        System.out.print(loadConfiguration.getText(INTRODUCEFILE, "#ASK_PLAYER_NAME"));

        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();

        System.out.println(loadConfiguration.getText(INTRODUCEFILE, "#INTRODUCE") + " " + playerName + "!");
        System.out.println(loadConfiguration.getText(INTRODUCEFILE, "#BEGINNING"));

        List<Configuration> configurations = new ArrayList<>();
        configurations.add(new Configuration(RPSTEXTS));
        configurations.add(new Configuration(RPSSLTEXTS));

    }


}

