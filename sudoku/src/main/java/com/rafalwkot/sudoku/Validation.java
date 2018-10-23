package com.rafalwkot.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validation {

    private static int QUANTITYELEMENTS = 9;
    private static int ROWELEMENTSINSQUARE = 3;
    private static int COLUMNELEMENTSINSQUARE = 3;

    private SudokuBoard sudokuBoard;

    public Validation(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    private boolean checkDigit(String value) {
        if (value.length() == 1) {
            if (Character.isDigit(value.charAt(0))) {
                return true;
            }
        }
        return false;
    }

    public boolean setValueToBoard(String row, String column, String value) {
        if (checkIfCanInsertValue(row, column, value)) {
            if (value.equals("0")) {
                value = "-1";
            }
            sudokuBoard.getSudokuRows().get(Integer.valueOf(row) - 1).getSudokuElementsInRow()
                    .get(Integer.valueOf(column) - 1).setValue(Integer.valueOf(value));
            return true;
        }
        return false;
    }

    public boolean validWholeBoard() {
        boolean isBoardCorrect = true;
        for (int i = 0; i < QUANTITYELEMENTS; i++) {
            isBoardCorrect = isBoardCorrect && checkIfRowIsCorrect(i);
            isBoardCorrect = isBoardCorrect && checkIfColumnIsCorrect(i);
        }
        for (int i = 0; i < QUANTITYELEMENTS; i = i + 3) {
            for (int j = 0; j < QUANTITYELEMENTS; j = j + 3) {
                isBoardCorrect = isBoardCorrect && checkIfSquareIsCorrect(i, j);
            }
        }
        return isBoardCorrect;
    }

    private boolean checkIfCanInsertValue(String row, String column, String value) {
        if (checkDigit(row) && checkDigit(column) && checkDigit(value)) {
            if (checkIfCanInsertValueInRow(Integer.valueOf(row), Integer.valueOf(value)) &&
                    checkIfCanInsertValueInColumn(Integer.valueOf(column), Integer.valueOf(value)) &&
                    checkIfCanInsertValueInSquare(Integer.valueOf(row), Integer.valueOf(column), Integer.valueOf(value))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfCanInsertValueInRow(int row, int value) {
        if (sudokuBoard.getSudokuRows().get(row - 1).getSudokuElementsInRow().stream()
                .filter(i -> i.getValue().equals(value))
                .collect(Collectors.toList()).isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean checkIfCanInsertValueInColumn(int column, int value) {
        if (sudokuBoard.getSudokuRows().stream()
                .filter(i -> i.getSudokuElementsInRow().get(column - 1).getValue().equals(value))
                .collect(Collectors.toList()).isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean checkIfCanInsertValueInSquare(int row, int column, int value) {
        int firstElementInSquareByRow = Math.round((row - 1) / 3) * 3;
        int firstElementInSquareByColumn = Math.round((column - 1) / 3) * 3;
        for (int i = firstElementInSquareByRow; i < ROWELEMENTSINSQUARE + firstElementInSquareByRow; i++) {
            for (int j = firstElementInSquareByColumn; j < COLUMNELEMENTSINSQUARE + firstElementInSquareByColumn; j++) {
                if (sudokuBoard.getSudokuRows().get(i).getSudokuElementsInRow().get(j).getValue().equals(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIfRowIsCorrect(int row) {
        boolean noDuplicates = true;
        List<Integer> numbersToCheckDuplicates = sudokuBoard.getSudokuRows().get(row).getSudokuElementsInRow().stream()
                .filter(i -> !i.getValue().equals(-1))
                .map(i -> i.getValue())
                .collect(Collectors.toList());
        Set<Integer> numbersWithOutDuplicates = new HashSet<>(numbersToCheckDuplicates);
        if (numbersWithOutDuplicates.size() < numbersToCheckDuplicates.size()) {
            noDuplicates = false;
        }
        return noDuplicates;
    }

    private boolean checkIfColumnIsCorrect(int column) {
        boolean noDuplicates = true;
        List<Integer> numbersToCheckDuplicates = sudokuBoard.getSudokuRows().stream()
                .filter(i -> !i.getSudokuElementsInRow().get(column).getValue().equals(-1))
                .map(i -> i.getSudokuElementsInRow().get(column).getValue())
                .collect(Collectors.toList());
        Set<Integer> numbersWithOutDuplicates = new HashSet<>(numbersToCheckDuplicates);
        if (numbersWithOutDuplicates.size() < numbersToCheckDuplicates.size()) {
            noDuplicates = false;
        }
        return noDuplicates;
    }

    private boolean checkIfSquareIsCorrect(int startRow, int startColumn) {
        boolean noDuplicates = true;
        List<Integer> numbersToCheckDuplicates = new ArrayList<>();
        for (int i = startRow; i < startRow + ROWELEMENTSINSQUARE; i++) {
            for (int j = startColumn; j < startColumn + COLUMNELEMENTSINSQUARE; j++) {
                int valueToCheck = sudokuBoard.getSudokuRows().get(i).getSudokuElementsInRow().get(j).getValue();
                if (valueToCheck != -1) {
                    numbersToCheckDuplicates.add(valueToCheck);
                }
            }
        }
        Set<Integer> numbersWithOutDuplicates = new HashSet<>(numbersToCheckDuplicates);
        if (numbersWithOutDuplicates.size() < numbersToCheckDuplicates.size()) {
            noDuplicates = false;
        }
        return noDuplicates;
    }
}
