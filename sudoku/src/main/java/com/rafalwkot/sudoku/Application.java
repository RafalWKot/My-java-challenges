package com.rafalwkot.sudoku;

import com.rafalwkot.sudoku.provider.impl.FileTextProvider;

import java.io.UnsupportedEncodingException;

public class Application {

    public static final String GAMETEXTS = "game_texts.txt";
    public static final String TEXTS = "game_texts";

    public static void main(String[] args) {
        FileTextProvider textProvider = new FileTextProvider();
        try {
            textProvider.loadTexts(TEXTS);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(textProvider.getText("GAME_NAME"));
        System.out.println(textProvider.getText("#AUTHOR_NAME") + "\n");
        System.out.println(textProvider.getText("#ABOUT"));
        System.out.println(textProvider.getText("#INTRODUCE") + "\n");
        boolean gameFinished = false;
        while (!gameFinished) {
            SudokuGame theGame = new SudokuGame();
            gameFinished = theGame.resolveSudoku();
        }
        System.out.println(textProvider.getText("#GOODBYE") + "\n");
    }
}
