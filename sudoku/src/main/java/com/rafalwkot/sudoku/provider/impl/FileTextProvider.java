package com.rafalwkot.sudoku.provider.impl;

import com.rafalwkot.sudoku.provider.TextProvider;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class FileTextProvider implements TextProvider {

    private Map<String, String> texts = new HashMap<>();

    public String getText(String tag) {
        return texts.get(tag);
    }

    public void loadTexts(String fileName) throws UnsupportedEncodingException {
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Set<String> tags = rb.keySet();
        for (String key : tags) {
            texts.put(key, new String(rb.getString(key).getBytes("ISO-8859-1"), "UTF-8"));
        }
    }
}
