package com.rafalwkot.sudoku.provider;

import com.rafalwkot.sudoku.SudokuBoard;

public interface BoardProvider {

    SudokuBoard getBoard() throws Exception;
}
