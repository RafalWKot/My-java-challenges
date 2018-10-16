package com.rafalwkot.rps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LoadConfiguration {

    private static final String ENDFILE = "#ENDFILE";
    private static final String WIN = "#WIN";
    private static final String DEFEAT = "#DEFEAT";
    private static final String EMPTY = "";


    public String getText(String fileName, String text) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(getFile(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String textFromFile;
        while (true) {
            if (scanner.nextLine().equals(text)) {
                textFromFile = scanner.nextLine();
                String line = scanner.nextLine();
                while (!line.equals("")) {
                    textFromFile = textFromFile + "\n" + line;
                    line = scanner.nextLine();
                }
                return textFromFile;
            }
        }
    }

    public List<Scheme> getScheme(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(getFile(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Scheme> schemes = new ArrayList<>();
        int noOfMove = 1;
        String line = scanner.nextLine();
        while (!line.equals(ENDFILE)) {
            Move move;
            Set<Move> winMoves = new HashSet<>();
            Set<Move> defeatMoves = new HashSet<>();
            if (line.equals("#" + noOfMove)) {
                move = getMove(scanner.nextLine());
                scanner.nextLine();
                addMoves(scanner, winMoves, WIN);
                addMoves(scanner, defeatMoves, DEFEAT);
                schemes.add(new Scheme(move, winMoves, defeatMoves));
                noOfMove++;
            }
            line = scanner.nextLine();
        }
        return schemes;
    }

    private void addMoves(Scanner scanner, Set<Move> winMoves, String win) {
        String line;
        String lineWin = scanner.nextLine();
        if (lineWin.equals(win)) {
            line = scanner.nextLine();
            while (!line.equals(EMPTY)) {
                winMoves.add(getMove(line));
                line = scanner.nextLine();
            }
        }
    }

    private Move getMove(String move) {
        switch (move) {
            case "Kamień":
                return Move.ROCK;
            case "Papier":
                return Move.PAPER;
            case "Nożyce":
                return Move.SCISSORS;
            case "Spock":
                return Move.SPOCK;
            case "Jaszczurka":
                return Move.LIZARD;
            default:
                return null;
        }
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }
}
