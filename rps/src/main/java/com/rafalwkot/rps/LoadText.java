package com.rafalwkot.rps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LoadText {

    private static final String EMPTY = "";

    public static String getText(String fileName, String text) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(getFile(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String textFromFile;
        while (true) {
            if (scanner.nextLine().equals(text)) {
                textFromFile = scanner.nextLine();
                String line = scanner.nextLine();
                while (!line.equals(EMPTY)) {
                    textFromFile = textFromFile + "\n" + line;
                    line = scanner.nextLine();
                }
                return textFromFile;
            }
        }
    }

    private static File getFile(String fileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }
}
