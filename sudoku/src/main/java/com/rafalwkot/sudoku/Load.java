package com.rafalwkot.sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class Load {

    private static final String EMPTY = "";
    private static Scanner SCANNER;
    public static final Scanner INPUT_CONSOLE = new Scanner(System.in);

    public static String getText(String fileName, String text) {
        try {
            SCANNER = new Scanner(getFile(fileName));
            String textFromFile;
            while (true) {
                if (SCANNER.nextLine().equals(text)) {
                    textFromFile = SCANNER.nextLine();
                    String line = SCANNER.nextLine();
                    while (!line.equals(EMPTY)) {
                        textFromFile = textFromFile + "\n" + line;
                        line = SCANNER.nextLine();
                    }
                    return textFromFile;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Plik z tekstami aplikacji nie został znaleziony " +
                    "lub niepoprawna jest jego  konfiguracja!");
        }
        return "";
    }

    private static File getFile(String fileName) throws FileNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        return new File(((Optional.ofNullable(classLoader.getResource(fileName))).orElseThrow(FileNotFoundException::new)).getFile());
    }
}
