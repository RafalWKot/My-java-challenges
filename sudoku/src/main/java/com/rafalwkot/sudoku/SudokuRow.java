package com.rafalwkot.sudoku;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@EqualsAndHashCode
@Getter
public class SudokuRow {

    public static int ELEMENTSQUANTITYINROW = 9;

    private List<SudokuElement> sudokuElementsInRow;

    SudokuRow() {
        sudokuElementsInRow = new ArrayList<>();
        IntStream.range(0, ELEMENTSQUANTITYINROW)
                .forEach(i -> sudokuElementsInRow.add(new SudokuElement()));
    }

    @Override
    public String toString() {
        return "| " + sudokuElementsInRow.get(0).printElement() +
                " | " + sudokuElementsInRow.get(1).printElement() +
                " | " + sudokuElementsInRow.get(2).printElement() +
                " || " + sudokuElementsInRow.get(3).printElement() +
                " | " + sudokuElementsInRow.get(4).printElement() +
                " | " + sudokuElementsInRow.get(5).printElement() +
                " || " + sudokuElementsInRow.get(6).printElement() +
                " | " + sudokuElementsInRow.get(7).printElement() +
                " | " + sudokuElementsInRow.get(8).printElement() + " |";
    }
}
