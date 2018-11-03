package com.rafalwkot.sudoku.provider;

import com.rafalwkot.sudoku.Application;
import com.rafalwkot.sudoku.SudokuBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Load_old {

    public static final Scanner INPUT_CONSOLE = new Scanner(System.in);
    private static final String EMPTY = "";
    private static Scanner SCANNER;

    public static String getText(String fileName, String text) {
        try {
            SCANNER = new Scanner(getFile(fileName));
            StringBuilder textFromFile = new StringBuilder();
            while (true) {
                if (SCANNER.nextLine().equals(text)) {
                    textFromFile.append(SCANNER.nextLine());
                    String line = SCANNER.nextLine();
                    while (!line.equals(EMPTY)) {
                        textFromFile.append("\n" + line);
                        line = SCANNER.nextLine();
                    }
                    return textFromFile.toString();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Plik z tekstami aplikacji nie został znaleziony " +
                    "lub niepoprawna jest jego  konfiguracja!");
        }
        return "";
    }

    public static SudokuBoard getInitialBoardFromFile(String fileName) {
        SudokuBoard sudokuBoard = new SudokuBoard();
        try {
            SCANNER = new Scanner(getFile(fileName));
            IntStream.range(0, SudokuBoard.ROWQUANTITY)
                    .forEach(i -> sudokuBoard.getSudokuRows().get(i).getSudokuElementsInRow().stream()
                            .forEach(t -> t.setValue(SCANNER.nextInt())));
        } catch (FileNotFoundException e) {
            System.out.println(getText(Application.GAMETEXTS, "#FIlE_SUDOKU_NOT_FOUND"));
        }
        return sudokuBoard;
    }

    private static File getFile(String fileName) throws FileNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        return new File(((Optional.ofNullable(classLoader.getResource(fileName))).orElseThrow(FileNotFoundException::new)).getFile());
    }
}
