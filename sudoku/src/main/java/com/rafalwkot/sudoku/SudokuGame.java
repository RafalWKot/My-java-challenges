package com.rafalwkot.sudoku;

public class SudokuGame {

    public boolean resolveSudoku() {
        init();

        isReplayResolve();
        return true;
    }

    private void init() {
        boolean conditionMark = true;
        while (conditionMark) {
            System.out.println(Load.getText(Application.GAMETEXTS, "#SUDOKU_INPUT_METHOD") + "\n");
            String mark = Load.INPUT_CONSOLE.next();
            if (mark.equals("1")) {
                //getSchemeFromFile();
                conditionMark = false;
            } else if (mark.equals("2")) {
                //getSchemeFromConsole();
                conditionMark = false;
            } else {
                System.out.println(Load.getText(Application.GAMETEXTS, "#INCORRECT_SUDOKU_INPUT_METHOD") + "\n");
            }
        }
    }

    private boolean isReplayResolve() {
        boolean gameFinished = true;
        System.out.println(Load.getText(Application.GAMETEXTS, "#ASK_FOR_REPLAY") + "\n");
        String mark = Load.INPUT_CONSOLE.next();
        if (mark.equals("1")) {
            gameFinished = false;
        }
        return gameFinished;
    }
}
