package com.rafalwkot.sudoku;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AlgorithmSudokuWithTreeSolutions extends Algoritm {

    private List<List<Branch>> tree;
    private Set<SudokuBoard> variousSudokuSolve;

    public AlgorithmSudokuWithTreeSolutions(SudokuBoard sudokuBoard) {
        super(sudokuBoard);
        tree = new ArrayList<>();
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
            buildBranches(listOfElementsWithMinPossiblesMoves);
            printTree();
            setValueInEmptyElementsFromBranches();
            System.out.println("SET VALuE: " + setValueInEmptyElementsFromBranches());
            if (checkIfSudokuIsSolved()) {
                nextMovePossible = false;
            }
        }

        return false;
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

    private boolean setValueInEmptyElementsFromBranches() {
        boolean isCorrectInserted = false;
        int step = tree.size() - 1;
        while (step > -1 && !isCorrectInserted) {
            Branch insertBranch = randomBranchIndex(step);
            System.out.println("Insert branch:" +insertBranch.getIndex());
            if (insertBranch != null) {
                sudokuBoard.getSudokuRows().get(getRowNumberFromIndexList(insertBranch.getIndex()))
                        .getSudokuElementsInRow().get(getColumnNumberFromIndexList(insertBranch.getIndex()))
                        .setValue(insertBranch.getValue());
                isCorrectInserted = true;
            } else {
                step--;
            }
        }
        return isCorrectInserted;
    }

    //------------------------------------------------------------------------------------------------------------------
    //Funkcje pomocnicze docelowo do usunięcia

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
