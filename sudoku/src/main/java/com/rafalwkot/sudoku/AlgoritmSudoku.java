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
    private List<List<Branch>> tree;

    public AlgoritmSudoku(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        tree = new ArrayList<>();
    }

    public void resolve() {

        findPossibilitiesForAllBoard();
        boolean nextMoveCorrect = true;
        int steps = 0;
        while (nextMoveCorrect) {
            int numberOfPotentialMovesInMinElements = findMinOfSizePossibleValues(findPotentialMoves());
            List<Integer> sudokuElementsWithMinPossibles = getListOfElementWithMinPossibles(numberOfPotentialMovesInMinElements, findPotentialMoves());
            buildBranches(sudokuElementsWithMinPossibles);
            nextMoveCorrect = setValueInEmptyPlace(sudokuElementsWithMinPossibles);
            System.out.println(nextMoveCorrect);
            steps++;
            if (checkIfSudokuIsSolved()) {
                System.out.println("Sudoku solved");
                nextMoveCorrect = false;
            }
        }
        System.out.println("Steps = " + steps);
        System.out.println(sudokuBoard.toString());
    }

    public void resolveWithBranches() {
        findPossibilitiesForAllBoard();
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

    private int getValueFromIndexList(int index) {
        return sudokuBoard.getSudokuRows()
                .get(getRowNumberFromIndexList(index))
                .getSudokuElementsInRow()
                .get(getColumnNumberFromIndexList(index))
                .getValue();
    }

    private List<Integer> getPossibleValuesFromIndexList(int index) {
        return sudokuBoard.getSudokuRows()
                .get(getRowNumberFromIndexList(index))
                .getSudokuElementsInRow()
                .get(getColumnNumberFromIndexList(index))
                .getPossibleValues();
    }

    private boolean setValueInEmptyPlace(List<Integer> listOfIndex) {

        boolean isValueToSet = true;
        if (listOfIndex.isEmpty()) {
            isValueToSet = false;
        } else {
            Random random = new Random();
            int chosenMove = random.nextInt(listOfIndex.size());
            int chosenValue = random.nextInt(sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndex.get(chosenMove)))
                    .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndex.get(chosenMove)))
                    .getPossibleValues().size());

            sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndex.get(chosenMove)))
                    .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndex.get(chosenMove)))
                    .setValue(sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndex.get(chosenMove)))
                            .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndex.get(chosenMove)))
                            .getPossibleValues().get(chosenValue));

            tree.get(tree.size() - 1).stream()
                    .filter(i -> (i.getIndex() == listOfIndex.get(chosenMove) &&
                            i.getValue() == sudokuBoard.getSudokuRows()
                                    .get(getRowNumberFromIndexList(listOfIndex.get(chosenMove)))
                                    .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndex.get(chosenMove)))
                                    .getPossibleValues().get(chosenValue)))
                    .collect(Collectors.toList()).get(0).setTried(true);

            findPossibilitiesForRowColumnSquareByIndex(getRowNumberFromIndexList(listOfIndex.get(chosenMove)), getColumnNumberFromIndexList(listOfIndex.get(chosenMove)));
        }
        return isValueToSet;
    }

    private Branch randomBranchIndex(int step) {
        Branch randomBranch = null;
        List<Branch> freeIndex = tree.get(tree.size() - 1 - step).stream()
                .filter(index -> !index.isTried())
                .collect(Collectors.toList());
        if (!freeIndex.isEmpty()) {
            Random random = new Random();
            int chosenMove = random.nextInt(freeIndex.size());
            randomBranch = freeIndex.get(chosenMove);
        }
        return randomBranch;
    }

    private void buildBranches(List<Integer> indexList) {
        List<Branch> indexListInStep = new ArrayList<>();
        for (int i = 0; i < indexList.size(); i++) {
            for (int j = 0; j < getPossibleValuesFromIndexList(indexList.get(i)).size(); j++) {
                indexListInStep.add(new Branch(indexList.get(i), getPossibleValuesFromIndexList(indexList.get(i)).get(j)));
            }
        }
        tree.add(indexListInStep);
    }

    private boolean checkIfSudokuIsSolved() {
        boolean isSolved = true;
        if (!sudokuBoard.getSudokuRows().stream()
                .flatMap(i -> i.getSudokuElementsInRow().stream())
                .filter(t -> t.getValue() == -1)
                .collect(Collectors.toList()).isEmpty()) {
            isSolved = false;
        }
        return isSolved;
    }

    private boolean setValueInEmptyPlaceFromBranches() {
        boolean setValueCorrect = false;
        int step = tree.size();
        while (step > 0) {
            Branch insertBranch = randomBranchIndex(step);
            if (insertBranch != null) {
                sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(insertBranch.getIndex()))
                        .getSudokuElementsInRow().get(getColumnNumberFromIndexList(insertBranch.getIndex()))
                        .setValue(sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(insertBranch.getIndex()))
                                .getSudokuElementsInRow().get(getColumnNumberFromIndexList(insertBranch.getIndex()))
                                .getPossibleValues().get(insertBranch.getValue()));
                setValueCorrect = true;
            } else {
                step--;
            }
        }
        return setValueCorrect;
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

    private void printTree() {
        for (int i = 0; i < tree.size(); i++) {
            for (int j = 0; j < tree.get(i).size(); j++) {
                System.out.print("[L:");
                System.out.print(tree.get(i).get(j).getIndex());
                System.out.print(",V:");
                System.out.print(tree.get(i).get(j).getValue());
                System.out.print(",T:");
                System.out.print(tree.get(i).get(j).isTried());
                System.out.print("]");
            }
            System.out.println();
        }
    }
}
