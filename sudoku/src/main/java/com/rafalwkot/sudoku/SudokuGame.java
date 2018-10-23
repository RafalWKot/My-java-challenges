package com.rafalwkot.sudoku;

public class SudokuGame {

    private SudokuBoard sudokuBoard;

    public SudokuGame() {
        this.sudokuBoard = new SudokuBoard();
    }

    public boolean resolveSudoku() {
        init();
        System.out.println(Load.getText(Application.GAMETEXTS, "#YOUR_SUDOKU"));
        System.out.println(sudokuBoard.toString());
        System.out.println(Load.getText(Application.GAMETEXTS, "#ASK_TO_RESOLVE_SUDOKU"));
        String inputResolve = Load.INPUT_CONSOLE.next();
        if (inputResolve.equals("1")) {

        }
        return isReplayResolve();
    }

    private void init() {
        boolean correctData = true;
        while (correctData) {
            System.out.println(Load.getText(Application.GAMETEXTS, "#SUDOKU_INPUT_METHOD") + "\n");
            System.out.print(Load.getText(Application.GAMETEXTS, "#YOUR_CHOICE"));
            String inputMethod = Load.INPUT_CONSOLE.next();
            if (inputMethod.equals("1")) {
                setValuesFromFile();
                System.out.println(sudokuBoard.toString());
                setValueFromConsole();
                correctData = false;
            } else if (inputMethod.equals("2")) {
                System.out.println(Load.getText(Application.GAMETEXTS, "#MANUAL_FILLING_SUDOKU") + "\n");
                correctData = false;
                setValueFromConsole();
            } else {
                System.out.println(Load.getText(Application.GAMETEXTS, "#INCORRECT_SUDOKU_INPUT_METHOD") + "\n");
            }
        }
    }

    private boolean insertValueToCell(SudokuBoard sudokuBoard) {
        Validation validation = new Validation(sudokuBoard);
        System.out.println(Load.getText(Application.GAMETEXTS, "#ASK_FOR_VALUE"));
        System.out.println(Load.getText(Application.GAMETEXTS, "#ROW"));
        String row = Load.INPUT_CONSOLE.next();
        System.out.println(Load.getText(Application.GAMETEXTS, "#COLUMN"));
        String column = Load.INPUT_CONSOLE.next();
        System.out.println(Load.getText(Application.GAMETEXTS, "#VALUE"));
        String value = Load.INPUT_CONSOLE.next();
        return validation.setValueToBoard(row, column, value);
    }

    private void setValueFromConsole() {
        boolean endInsertValue = false;
        while (!endInsertValue) {
            if (!insertValueToCell(sudokuBoard)) {
                System.out.println(Load.getText(Application.GAMETEXTS, "#INCORRECT_VALUE"));
            } else {
                System.out.println(sudokuBoard.toString());
            }
            System.out.println(Load.getText(Application.GAMETEXTS, "#NEXT_VALUE"));
            System.out.print(Load.getText(Application.GAMETEXTS, "#YOUR_CHOICE"));
            String conditionNextValue = Load.INPUT_CONSOLE.next();
            if (!conditionNextValue.equals("1")) {
                endInsertValue = true;
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

    private void setValuesFromFile() {
        System.out.println(Load.getText(Application.GAMETEXTS, "#FILE_NAME"));
        System.out.print(Load.getText(Application.GAMETEXTS, "#FILE"));
        String fileName = Load.INPUT_CONSOLE.next();
        Validation validation = new Validation(Load.getInitialBoardFromFile(fileName));
        if (validation.validWholeBoard()) {
            sudokuBoard = Load.getInitialBoardFromFile(fileName);
        }
        else {
            System.out.print(Load.getText(Application.GAMETEXTS, "#SUDOKU_LOGIC_INCORRECT"));
            sudokuBoard = new SudokuBoard();
        }
    }
}
