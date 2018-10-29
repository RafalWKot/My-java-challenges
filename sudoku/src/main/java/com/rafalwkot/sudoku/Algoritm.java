package com.rafalwkot.sudoku;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class Algoritm {
    protected static final int ROWSINSQUARE = 3;
    protected static final int COLUMNSINSQUARE = 3;
    protected SudokuBoard sudokuBoard;

    public Algoritm(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public abstract boolean resolve();

    protected void findPossibilitiesForRowColumnSquareByIndex(int rowNumber, int columnNumber) {
        findPossibilitiesInRow(rowNumber);
        findPossibilitiesInColumn(columnNumber);
        findPossibilitiesInSquare(rowNumber, columnNumber);
    }

    protected void findPossibilitiesForAllBoard() {
        for (int i = 0; i < SudokuBoard.ROWQUANTITY; i++) {
            findPossibilitiesInRow(i);
        }
        for (int i = 0; i < SudokuRow.ELEMENTSQUANTITYINROW; i++) {
            findPossibilitiesInColumn(i);
        }
        for (int i = 0; i < ROWSINSQUARE; i++) {
            for (int j = 0; j < COLUMNSINSQUARE; j++) {
                findPossibilitiesInSquare(i * ROWSINSQUARE, j * COLUMNSINSQUARE);
            }
        }
    }

    private void findPossibilitiesInRow(int row) {
        for (int i = 0; i < SudokuRow.ELEMENTSQUANTITYINROW; i++) {
            if (sudokuBoard.getSudokuRows().get(row).getSudokuElementsInRow().get(i).getValue() == SudokuElement.EMPTY) {
                for (SudokuElement sudokuElement : sudokuBoard.getSudokuRows().get(row).getSudokuElementsInRow()) {
                    sudokuBoard.getSudokuRows().get(row)
                            .getSudokuElementsInRow().get(i)
                            .getPossibleValues()
                            .remove(sudokuElement.getValue());
                }
            } else {
                sudokuBoard.getSudokuRows().get(row).getSudokuElementsInRow().get(i).getPossibleValues().clear();
            }
        }
    }

    private void findPossibilitiesInColumn(int column) {
        for (int i = 0; i < SudokuRow.ELEMENTSQUANTITYINROW; i++) {
            if (sudokuBoard.getSudokuRows().get(i).getSudokuElementsInRow().get(column).getValue() == SudokuElement.EMPTY) {
                for (int j = 0; j < SudokuRow.ELEMENTSQUANTITYINROW; j++) {
                    sudokuBoard.getSudokuRows().get(i)
                            .getSudokuElementsInRow().get(column)
                            .getPossibleValues()
                            .remove(sudokuBoard.getSudokuRows().get(j).getSudokuElementsInRow().get(column).getValue());
                }
            } else {
                sudokuBoard.getSudokuRows().get(i).getSudokuElementsInRow().get(column).getPossibleValues().clear();
            }
        }
    }

    private void findPossibilitiesInSquare(int row, int column) {
        int firstElementInSquareByRow = Math.round(row / 3) * 3;
        int firstElementInSquareByColumn = Math.round(column / 3) * 3;
        for (int k = 0; k < ROWSINSQUARE; k++) {
            for (int l = 0; l < COLUMNSINSQUARE; l++) {
                for (int i = 0; i < ROWSINSQUARE; i++) {
                    for (int j = 0; j < COLUMNSINSQUARE; j++) {
                        if (sudokuBoard.getSudokuRows().get(firstElementInSquareByRow + k).getSudokuElementsInRow().get(firstElementInSquareByColumn + l).getValue() == SudokuElement.EMPTY) {
                            sudokuBoard.getSudokuRows().get(firstElementInSquareByRow + k)
                                    .getSudokuElementsInRow().get(firstElementInSquareByColumn + l)
                                    .getPossibleValues()
                                    .remove(sudokuBoard.getSudokuRows().get(firstElementInSquareByRow + i).getSudokuElementsInRow().get(firstElementInSquareByColumn + j).getValue());
                        }
                    }
                }
            }
        }
    }

    protected int getRowNumberFromIndexList(int indexMinPossibleValues) {
        return Math.round(indexMinPossibleValues / SudokuRow.ELEMENTSQUANTITYINROW);
    }

    protected int getColumnNumberFromIndexList(int indexMinPossibleValues) {
        return Math.round(indexMinPossibleValues % SudokuRow.ELEMENTSQUANTITYINROW);
    }

    protected int getValueFromIndexList(int index) {
        return sudokuBoard.getSudokuRows()
                .get(getRowNumberFromIndexList(index))
                .getSudokuElementsInRow()
                .get(getColumnNumberFromIndexList(index))
                .getValue();
    }

    protected List<Integer> getPossibleValuesFromIndexList(int index) {
        return sudokuBoard.getSudokuRows()
                .get(getRowNumberFromIndexList(index))
                .getSudokuElementsInRow()
                .get(getColumnNumberFromIndexList(index))
                .getPossibleValues();
    }

    protected boolean checkIfSudokuIsSolved() {
        boolean isSolved = true;
        if (!sudokuBoard.getSudokuRows().stream()
                .flatMap(i -> i.getSudokuElementsInRow().stream())
                .filter(t -> t.getValue() == -1)
                .collect(Collectors.toList()).isEmpty()) {
            isSolved = false;
        }
        return isSolved;
    }

    protected List<SudokuElement> getPossibleMoves() {
        return sudokuBoard.getSudokuRows().stream()
                .flatMap(i -> i.getSudokuElementsInRow().stream())
                .collect(Collectors.toList());
    }

    protected int findMinValueOfPossibleMoves(List<SudokuElement> potentialMovesList) {
        List<Integer> listOfSizePossibleValues = potentialMovesList.stream()
                .map(t -> t.getPossibleValues().size())
                .filter(t -> !t.equals(0))
                .collect(Collectors.toList());
        if (listOfSizePossibleValues.isEmpty()) {
            return 0;
        }
        return Collections.min(listOfSizePossibleValues);
    }

    protected List<Integer> getElementsWithMinPossibles(int numberOfPotentialMovesInMinElements, List<SudokuElement> potentialMovesList) {
        List<Integer> listOfIndexes = new ArrayList<>();
        if (numberOfPotentialMovesInMinElements != 0) {
            for (int i = 0; i < potentialMovesList.size(); i++) {
                if (potentialMovesList.get(i).getPossibleValues().size() == numberOfPotentialMovesInMinElements) {
                    listOfIndexes.add(i);
                }
            }
        }
        return listOfIndexes;
    }

    //----------------------------------------------------------------------------------------------------------------
    //Funkcje pomocnicze wyświetlające
    //----------------------------------------------------------------------------------------------------------------
    private void printPossibilitiesValues() {
        System.out.println("Print all possible values");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(sudokuBoard.getSudokuRows().get(i).getSudokuElementsInRow().get(j).getPossibleValues().toString());
            }
        }
    }

    //pomocnicza faunkcja
    private void printPossibleValuesOfMinWalues(List<Integer> listOfElementWithPossibles) {
        System.out.println("Print elements where is min of possible moves");
        for (int i = 0; i < listOfElementWithPossibles.size(); i++) {
            System.out.println(listOfElementWithPossibles.get(i).toString());
        }
    }
}
