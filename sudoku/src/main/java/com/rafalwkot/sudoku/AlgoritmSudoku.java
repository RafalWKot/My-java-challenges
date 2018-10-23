package com.rafalwkot.sudoku;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
public class AlgoritmSudoku {

    private static final int ROWSINSQUARE = 3;
    private static final int COLUMNSINSQUARE = 3;
    private SudokuBoard sudokuBoard;

    public AlgoritmSudoku(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public void resolve() {
        //1. Pierwsze ucruchomienie sprawdza Sudoku i znajduje wszystkie możliwości ruchu
        findPossibilitiesForAllBoard();

        printPossibilitiesValues();

        boolean nextMoveCorrect = true;
        int security = 0;
        while (nextMoveCorrect && (security < 80)) {
            printPossibilitiesValues();
            //2. Wybierz element/elementy gdzie jest najmniej możliwości rychu
            System.out.println("Min z możłiwych ruchów:");
            int numberOfPotetialMovesInMinElements = findMinOfSizePossibleValues(findPotentialMoves());
            System.out.println(numberOfPotetialMovesInMinElements);
            List<Integer> sudokuElementsWithMinPossibles = getListOfElementWithMinPossibles(numberOfPotetialMovesInMinElements, findPotentialMoves());
            printPossibleValuesOfMinWalues(sudokuElementsWithMinPossibles);
            nextMoveCorrect = setValueInEmptyPlace(sudokuElementsWithMinPossibles);
            System.out.println(sudokuBoard.toString());
            security++;
        }
        System.out.println("Security = " + security);
        //3. Jeżeli to konieczne wylosuj element jeden z wielu
        //4. Jeżeli to konieczne wylosuj wartość jedną z wielu
        //5. Krok i możliwości zapisz do Listy
        // }

    }

    private void findPossibilitiesForRowColumnSquareByIndex(int rowNumber, int columnNumber) {
        findPossibilitiesInRow(rowNumber);
        findPossibilitiesInColumn(columnNumber);
        findPossibilitiesInSquare(rowNumber, columnNumber);
    }

    private void findPossibilitiesForAllBoard() {
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

    private List<SudokuElement> findPotentialMoves() {
        return sudokuBoard.getSudokuRows().stream()
                .flatMap(i -> i.getSudokuElementsInRow().stream())
                .collect(Collectors.toList());
    }

    private int findMinOfSizePossibleValues(List<SudokuElement> potentialMovesList) {

        List<Integer> listOfSizePossibleValues = potentialMovesList.stream()
                .map(t -> t.getPossibleValues().size())
                .filter(t -> !t.equals(0))
                .collect(Collectors.toList());

        if (listOfSizePossibleValues.isEmpty()) {
            return 0;
        }
        return Collections.min(listOfSizePossibleValues);
    }

    private List<Integer> getListOfElementWithMinPossibles(int numberOfPotentialMovesInMinElements, List<SudokuElement> potentialMovesList) {
        List<Integer> listOfIndex = new ArrayList<>();
        if (numberOfPotentialMovesInMinElements != 0) {
            for (int i = 0; i < potentialMovesList.size(); i++) {
                if (potentialMovesList.get(i).getPossibleValues().size() == numberOfPotentialMovesInMinElements) {
                    listOfIndex.add(i);
                }
            }
        }
        return listOfIndex;
    }

    private int getRowNumberFromIndexList(int indexMinPossibleValues) {
        return Math.round(indexMinPossibleValues / SudokuRow.ELEMENTSQUANTITYINROW);
    }

    private int getColumnNumberFromIndexList(int indexMinPossibleValues) {
        return Math.round(indexMinPossibleValues % SudokuRow.ELEMENTSQUANTITYINROW);
    }

    private boolean setValueInEmptyPlace(List<Integer> listOfIndex) {
        boolean isValueToSet = true;
        if (listOfIndex.isEmpty()) {
            isValueToSet = false;
        } else {
            Random random = new Random();
            int choosenMove = random.nextInt(listOfIndex.size());
            int choosenValue = random.nextInt(sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndex.get(choosenMove)))
                    .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndex.get(choosenMove)))
                    .getPossibleValues().size());

            sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndex.get(choosenMove)))
                    .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndex.get(choosenMove)))
                    .setValue(sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndex.get(choosenMove)))
                            .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndex.get(choosenMove)))
                            .getPossibleValues().get(choosenValue));

            findPossibilitiesForRowColumnSquareByIndex(getRowNumberFromIndexList(listOfIndex.get(choosenMove)), getColumnNumberFromIndexList(listOfIndex.get(choosenMove)));
        }
        return isValueToSet;
    }


    //pomocnicza faunkcja
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
