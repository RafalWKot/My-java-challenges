package com.rafalwkot.sudoku;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
public class Branch {

    private final int index;
    private final int value;

    @Setter
    private boolean isTried;

    public Branch(int index, int value) {
        this.index = index;
        this.value = value;
        isTried = false;
    }
}
