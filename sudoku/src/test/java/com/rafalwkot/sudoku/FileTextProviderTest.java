package com.rafalwkot.sudoku;

import com.rafalwkot.sudoku.provider.TextProvider;
import com.rafalwkot.sudoku.provider.impl.FileTextProvider;
import org.junit.Assert;
import org.junit.Test;

public class FileTextProviderTest {

    @Test
    public void testGetText() {
        //Given
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
}