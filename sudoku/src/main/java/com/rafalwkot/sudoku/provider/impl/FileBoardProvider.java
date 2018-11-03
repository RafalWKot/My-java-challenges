package com.rafalwkot.sudoku.provider.impl;

import com.rafalwkot.sudoku.Application;
import com.rafalwkot.sudoku.SudokuBoard;
import com.rafalwkot.sudoku.provider.BoardProvider;
import com.rafalwkot.sudoku.provider.TextProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FileBoardProvider implements BoardProvider {

    String fileName;
    TextProvider textProvider;

    public FileBoardProvider(String fileName, TextProvider textProvider) {
        this.fileName = fileName;
        this.textProvider = textProvider;
    }

    @Override
    public SudokuBoard getBoard(){
        final Scanner scanner;
        SudokuBoard sudokuBoard = new SudokuBoard();
        try {
            scanner = new Scanner(getFile(fileName));
            IntStream.range(0, SudokuBoard.ROWQUANTITY)
                    .forEach(i -> sudokuBoard.getSudokuRows().get(i).getSudokuElementsInRow().stream()
                            .forEach(t -> t.setValue(scanner.nextInt())));
        } catch (FileNotFoundException e) {
            System.out.println(textProvider.getText( "FIlE_SUDOKU_NOT_FOUND"));
        }
        return sudokuBoard;
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return  new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }
}
