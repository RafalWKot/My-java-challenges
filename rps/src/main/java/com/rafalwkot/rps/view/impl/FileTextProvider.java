package com.rafalwkot.rps.view.impl;

import com.rafalwkot.rps.view.TextProvider;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class FileTextProvider implements TextProvider {

    private Map<String, String> texts = new HashMap<>();

    public FileTextProvider(String fileName) throws UnsupportedEncodingException{
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Set<String> tags = rb.keySet();
        for (String key : tags) {
                texts.put(key, new String(rb.getString(key).getBytes("ISO-8859-1"), "UTF-8"));
        }
    }

    public String getText(String tag) {
        return texts.get(tag);
    }

}
