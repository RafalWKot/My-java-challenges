package com.rafalwkot.sudoku;

public class SudokuGame {

    public boolean resolveSudoku() {
        SudokuBoard sudokuBoard = init();
        System.out.println(Load.getText(Application.GAMETEXTS, "#YOUR_SUDOKU"));
        System.out.println(sudokuBoard.toString());
        return isReplayResolve();
    }

    private SudokuBoard init() {
        SudokuBoard sudokuBoard;
        boolean conditionOfCorrectData = true;
        while (conditionOfCorrectData) {
            System.out.println(Load.getText(Application.GAMETEXTS, "#SUDOKU_INPUT_METHOD") + "\n");
            System.out.print(Load.getText(Application.GAMETEXTS, "#YOUR_CHOICE"));
            String mark = Load.INPUT_CONSOLE.next();
            if (mark.equals("1")) {
                conditionOfCorrectData = false;
                System.out.println(Load.getText(Application.GAMETEXTS, "#FILE_NAME"));
                System.out.print(Load.getText(Application.GAMETEXTS, "#FILE"));
                String fileName = Load.INPUT_CONSOLE.next();
                sudokuBoard = Load.getInitialBoardFromFile(fileName);
                return sudokuBoard;
            } else if (mark.equals("2")) {
                System.out.println(Load.getText(Application.GAMETEXTS, "#MANUAL_FILLING_SUDOKU") + "\n");
                conditionOfCorrectData = false;
                boolean endInsertValue = false;
                while (!endInsertValue) {
                    boolean conditionOfCorrectDataConsoleInput = false;
                    while (!conditionOfCorrectDataConsoleInput) {

                    }
                }
            } else {
                System.out.println(Load.getText(Application.GAMETEXTS, "#INCORRECT_SUDOKU_INPUT_METHOD") + "\n");
            }
        }
        return null;
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

    private SudokuBoard insertValueToBoard(SudokuBoard sudokuBoard) {
        System.out.println(Load.getText(Application.GAMETEXTS, "##ASK_FOR_VALUE"));
        System.out.println(Load.getText(Application.GAMETEXTS, "#ROW"));
        String row = Load.INPUT_CONSOLE.nextLine();
        System.out.println(Load.getText(Application.GAMETEXTS, "#COLUMN"));
        String columnn = Load.INPUT_CONSOLE.nextLine();
        System.out.println(Load.getText(Application.GAMETEXTS, "#VALUE"));
        String value = Load.INPUT_CONSOLE.nextLine();

        return sudokuBoard;
    }


}
