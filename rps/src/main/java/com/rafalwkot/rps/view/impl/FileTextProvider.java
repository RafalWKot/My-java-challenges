package com.rafalwkot.rps.view.impl;

import com.rafalwkot.rps.model.LoadTextException;
import com.rafalwkot.rps.view.TextProvider;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class FileTextProvider implements TextProvider {

    private Map<String, String> texts = new HashMap<>();

    public FileTextProvider(String fileName) throws LoadTextException {   //LoadTextExeption
        try {
            ResourceBundle rb = ResourceBundle.getBundle(fileName);
            Set<String> tags = rb.keySet();
            for (String key : tags) {
                texts.put(key, new String(rb.getString(key).getBytes("ISO-8859-1"), "UTF-8"));
            }
        } catch (MissingResourceException e) {
            throw new LoadTextException("File not found",e);
        } catch (UnsupportedEncodingException e) {
            throw new LoadTextException("Wrong encoding",e);
        }
    }

    public String getText(String tag) {
        return texts.get(tag);
    }

}
