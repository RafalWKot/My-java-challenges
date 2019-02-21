package com.rafalwkot.rps;

import com.rafalwkot.rps.view.impl.FileTextProvider;
import com.rafalwkot.rps.view.TextProvider;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class FileTextProviderTest {

    @Test
    public void testGetText() {
        TextProvider textProvider = null;
        try {
            textProvider = new FileTextProvider("game_texts");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //When
        String text = textProvider.getText("AUTHOR_NAME");
        //Then
        Assert.assertEquals("Author: Rafał Kot", text);
    }

    @Test
    public void testLoadTexts() {
        TextProvider textProvider = null;
        try {
            textProvider = new FileTextProvider("game_texts");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //When
        String text = textProvider.getText("GAME_NAME");
        //Then
        Assert.assertEquals("Game: RPSSL 1.0", text);
    }
}