package com.rafalwkot.rps;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LoadConfigurationTest {

    @Test
    public void testGetTextFromRPS() {

        //Given
        LoadConfiguration loadConfiguration = new LoadConfiguration();

        //When
        String newLine = loadConfiguration.getText(Application.INTRODUCEFILE, "#GAME_NAME");

        //Then
        Assert.assertEquals("Game: RPSSL 1.0", newLine);
    }

    @Test
    public void testGetSchemaFromRPS() {

        //Given
        LoadConfiguration loadConfiguration = new LoadConfiguration();

        //When
        List<Scheme> schemes = loadConfiguration.getSchema(Application.RPSTEXTS);

        //Then
        Assert.assertEquals(3, schemes.size());
        Assert.assertTrue(schemes.get(2).getWinWith().contains(Move.PAPER));
        Assert.assertTrue(schemes.get(2).getLoseWith().contains(Move.ROCK));
    }

    @Test
    public void testGetSchemaFromRPSSL() {

        //Given
        LoadConfiguration loadConfiguration = new LoadConfiguration();

        //When
        List<Scheme> schemes = loadConfiguration.getSchema(Application.RPSSLTEXTS);

        //Then
        Assert.assertEquals(5, schemes.size());
        Assert.assertTrue(schemes.get(4).getWinWith().contains(Move.PAPER));
        Assert.assertTrue(schemes.get(4).getWinWith().contains(Move.SPOCK));
        Assert.assertTrue(schemes.get(4).getLoseWith().contains(Move.ROCK));
        Assert.assertTrue(schemes.get(4).getLoseWith().contains(Move.SCISSORS));
    }
}