package com.rafalwkot.sudoku.provider;

public interface TextProvider {

    String getText(String tag);

    void loadTexts(String dataStorage) throws Exception;
}
