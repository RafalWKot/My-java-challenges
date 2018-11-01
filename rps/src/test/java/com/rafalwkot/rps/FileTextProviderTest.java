package com.rafalwkot.rps;

import org.junit.Assert;
import org.junit.Test;


public class FileTextProviderTest {

    @Test
    public void testGetText() {
        TextProvider textProvider = new FileTextProvider();
        try {
            textProvider.loadTexts("game_texts");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //When
        String text = textProvider.getText("AUTHOR_NAME");
        //Then
        Assert.assertEquals("Author: Rafał Kot", text);
    }

    @Test
    public void testLoadTexts() {
        //Given
        TextProvider textProvider = new FileTextProvider();
        try {
            textProvider.loadTexts("game_texts");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //When
        String text = textProvider.getText("GAME_NAME");
        //Then
        Assert.assertEquals("Game: RPSSL 1.0", text);
    }
}