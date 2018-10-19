package com.rafalwkot.rps;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LoadSchemeConfigurationTest {

    @Test
    public void testGetTextFromRPS() {

        //Given

        //When
        String newLine = LoadText.getText("game_texts.txt", "#GAME_NAME");
        //Then
        Assert.assertEquals("Game: RPSSL 1.0", newLine);
    }

    @Test
    public void testGetSchemaFromRPS() {

        //Given

        //When
        List<Scheme> schemes = LoadScheme.getScheme("rps_scheme");

        //Then
        Assert.assertEquals(3, schemes.size());
        Assert.assertTrue(schemes.get(2).getWinWith().contains(Move.PAPER));
        Assert.assertTrue(schemes.get(2).getLoseWith().contains(Move.ROCK));
    }

    @Test
    public void testGetSchemaFromRPSSL() {

        //Given

        //When
        List<Scheme> schemes = LoadScheme.getScheme("rpssl_scheme");

        //Then
        Assert.assertEquals(5, schemes.size());
        Assert.assertTrue(schemes.get(4).getWinWith().contains(Move.PAPER));
        Assert.assertTrue(schemes.get(4).getWinWith().contains(Move.SPOCK));
        Assert.assertTrue(schemes.get(4).getLoseWith().contains(Move.ROCK));
        Assert.assertTrue(schemes.get(4).getLoseWith().contains(Move.SCISSORS));
    }
}