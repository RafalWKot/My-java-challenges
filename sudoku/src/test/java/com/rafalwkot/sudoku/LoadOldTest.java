package com.rafalwkot.sudoku;

import com.rafalwkot.sudoku.provider.Load_old;
import org.junit.Assert;
import org.junit.Test;

public class LoadOldTest {

    @Test
    public void testGetText() {
        //Given

        //When
        String textName = Load_old.getText("game_texts.txt", "#GAME_NAME");

        //Then
        Assert.assertEquals("Sudoku 1.0", textName);
    }

    @Test
    public void testGetInitialBoardFromFile() {
        //Given

        //When
        SudokuBoard sudokuBoard = Load_old.getInitialBoardFromFile("sudoku_example.txt");

        //Then
        Assert.assertEquals(8, sudokuBoard.getSudokuRows().get(1).getSudokuElementsInRow().get(8).getValue().longValue());
        Assert.assertEquals(3,sudokuBoard.getSudokuRows().get(2).getSudokuElementsInRow().get(0).getValue().longValue());
        Assert.assertEquals(6, sudokuBoard.getSudokuRows().get(8).getSudokuElementsInRow().get(7).getValue().longValue());
    }
}