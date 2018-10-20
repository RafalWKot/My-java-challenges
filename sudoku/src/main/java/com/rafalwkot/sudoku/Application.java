package com.rafalwkot.sudoku;

public class Application {

    public static final String GAMETEXTS = "game_texts.txt";

    public static void main(String[] args) {

        System.out.println(Load.getText(GAMETEXTS, "#GAME_NAME"));
        System.out.println(Load.getText(GAMETEXTS, "#AUTHOR_NAME") + "\n");
        System.out.println(Load.getText(GAMETEXTS, "#ABOUT"));
        System.out.println(Load.getText(GAMETEXTS, "#INTRODUCE") + "\n");

        boolean gameFinished = false;
        while(!gameFinished) {
            SudokuGame theGame = new SudokuGame();
            gameFinished = theGame.resolveSudoku();
        }

        System.out.println(Load.getText(GAMETEXTS, "#GOODBYE") + "\n");
    }
}
