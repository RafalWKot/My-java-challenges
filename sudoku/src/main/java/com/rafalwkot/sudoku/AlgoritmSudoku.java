package com.rafalwkot.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class AlgoritmSudoku extends Algoritm {

    public AlgoritmSudoku(SudokuBoard sudokuBoard) {
        super(sudokuBoard);
    }

    @Override
    public boolean resolve() {
        findPossibilitiesForAllBoard();
        boolean nextMovePossible = true;
        int noOfMinPossibleMoves;
        List<Integer> listOfElementsWithMinPossiblesMoves;
        while (nextMovePossible) {
            noOfMinPossibleMoves = findMinValueOfPossibleMoves(getPossibleMoves());
            listOfElementsWithMinPossiblesMoves = getElementsWithMinPossibles(noOfMinPossibleMoves, getPossibleMoves());
            nextMovePossible = setValueInEmptyElement(listOfElementsWithMinPossiblesMoves);

            nextMovePossible = setValueInEmptyElement(
                    getElementsWithMinPossibles(
                            findMinValueOfPossibleMoves(getPossibleMoves()), getPossibleMoves()));

            if (checkIfSudokuIsSolved()) {
                nextMovePossible = false;
            }
        }
        if (!checkIfSudokuIsSolved()) {
            return false;
        }
        return true;
    }



    private boolean setValueInEmptyElement(List<Integer> listOfIndexes) {
        boolean isValueToSet = true;
        if (listOfIndexes.isEmpty()) {
            isValueToSet = false;
        } else {
            Random random = new Random();
            int chosenMove = random.nextInt(listOfIndexes.size());
            int chosenValue = random.nextInt(sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndexes.get(chosenMove)))
                    .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndexes.get(chosenMove)))
                    .getPossibleValues().size());

            sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndexes.get(chosenMove)))
                    .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndexes.get(chosenMove)))
                    .setValue(sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(listOfIndexes.get(chosenMove)))
                            .getSudokuElementsInRow().get(getColumnNumberFromIndexList(listOfIndexes.get(chosenMove)))
                            .getPossibleValues().get(chosenValue));

            findPossibilitiesForRowColumnSquareByIndex(getRowNumberFromIndexList(listOfIndexes.get(chosenMove)), getColumnNumberFromIndexList(listOfIndexes.get(chosenMove)));
        }
        return isValueToSet;
    }


}
