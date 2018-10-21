package com.rafalwkot.sudoku;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.IntStream;

@EqualsAndHashCode
@Getter
public class SudokuElement {
    public static final int EMPTY = -1;
    public static final int VALUESQUANTITY = 9;

    @Setter
    private Integer value;
    private Set<Integer> possibleValues;


    SudokuElement() {
        this.value = EMPTY;
        possibleValues = new HashSet<>();
        IntStream.range(0, VALUESQUANTITY)
                .forEach(i -> possibleValues.add(i + 1));
    }

    public String printElement() {
        if (value == EMPTY) {
            return " ";
        }
        return value.toString();
    }
}
