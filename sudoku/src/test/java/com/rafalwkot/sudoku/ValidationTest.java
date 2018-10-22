package com.rafalwkot.sudoku;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidationTest {

    private SudokuBoard sudokuBoard;
    private Validation validation;

    @Before
    public void setUp() throws Exception {
        //Given
        sudokuBoard = new SudokuBoard();
        validation = new Validation(sudokuBoard);
    }

    @Test
    public void testInsertValueToBoardByColumn() {

        //When
        boolean correctInserting = validation.setValueToBoard("1", "1", "5");
        boolean inCorrectInserting = validation.setValueToBoard("4", "1", "5");

        //Then
        Assert.assertTrue(correctInserting);
        Assert.assertFalse(inCorrectInserting);
    }

    @Test
    public void testInsertValueToBoardByRow() {

        //When
        boolean correctInserting = validation.setValueToBoard("1", "1", "5");
        boolean inCorrectInserting = validation.setValueToBoard("1", "7", "5");

        //Then
        Assert.assertTrue(correctInserting);
        Assert.assertFalse(inCorrectInserting);
    }

    @Test
    public void testInsertValueToBoardBySquare() {

        //When
        boolean correctInserting = validation.setValueToBoard("1", "1", "5");
        boolean inCorrectInserting = validation.setValueToBoard("2", "2", "5");

        //Then
        Assert.assertTrue(correctInserting);
        Assert.assertFalse(inCorrectInserting);
    }
}