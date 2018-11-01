package com.rafalwkot.rps;

public interface TextProvider {

    String getText(String tag);

    void loadTexts(String dataStorage) throws Exception;
}
