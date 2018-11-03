package com.rafalwkot.sudoku;

import com.rafalwkot.sudoku.provider.Load_old;
import org.junit.Assert;
import org.junit.Test;

public class ValidationTest {

    @Test
    public void testInsertValueToBoardByColumnIncorrect() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        Validation validation = new Validation(sudokuBoard);

        //When
        boolean correctInserting = validation.setValueToBoard("1", "1", "5");
        boolean inCorrectInserting = validation.setValueToBoard("4", "1", "5");

        //Then
        Assert.assertTrue(correctInserting);
        Assert.assertFalse(inCorrectInserting);
    }

    @Test
    public void testInsertValueToBoardByRowIncorrect() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        Validation validation = new Validation(sudokuBoard);
        //When
        boolean correctInserting = validation.setValueToBoard("1", "1", "5");
        boolean inCorrectInserting = validation.setValueToBoard("1", "7", "5");

        //Then
        Assert.assertTrue(correctInserting);
        Assert.assertFalse(inCorrectInserting);
    }

    @Test
    public void testInsertValueToBoardBySquareIncorrect() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        Validation validation = new Validation(sudokuBoard);

        //When
        boolean correctInserting = validation.setValueToBoard("1", "1", "5");
        boolean inCorrectInserting = validation.setValueToBoard("2", "2", "5");

        //Then
        Assert.assertTrue(correctInserting);
        Assert.assertFalse(inCorrectInserting);
    }

    @Test
    public void testValidWholeBoardFromFileCorrect() {
        //Given
        SudokuBoard sudokuBoard = Load_old.getInitialBoardFromFile("sudoku_example.txt");
        Validation validation = new Validation(sudokuBoard);

        //When
        boolean isBoardCorrect = validation.validWholeBoard();

        //Then
        Assert.assertTrue(isBoardCorrect);
    }

    @Test
    public void testValidWholeBoardFromFileIncorrectInColumn() {
        //Given
        SudokuBoard sudokuBoard = Load_old.getInitialBoardFromFile("sudoku_example_incorrect_in_column.txt");
        Validation validation = new Validation(sudokuBoard);

        //When
        boolean isBoardCorrect = validation.validWholeBoard();

        //Then
        Assert.assertFalse(isBoardCorrect);
    }

    @Test
    public void testValidWholeBoardFromFileIncorrectInRow() {
        //Given
        SudokuBoard sudokuBoard = Load_old.getInitialBoardFromFile("sudoku_example_incorrect_in_row.txt");
        Validation validation = new Validation(sudokuBoard);

        //When
        boolean isBoardCorrect = validation.validWholeBoard();

        //Then
        Assert.assertFalse(isBoardCorrect);
    }

    @Test
    public void testValidWholeBoardFromFileIncorrectInSquare() {
        //Given
        SudokuBoard sudokuBoard = Load_old.getInitialBoardFromFile("sudoku_example_incorrect_in_square.txt");
        Validation validation = new Validation(sudokuBoard);

        //When
        boolean isBoardCorrect = validation.validWholeBoard();

        //Then
        Assert.assertFalse(isBoardCorrect);
    }
}