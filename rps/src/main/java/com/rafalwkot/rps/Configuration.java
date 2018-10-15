package com.rafalwkot.rps;

import java.util.List;

public class Configuration {

    private final String fileName;
    private LoadConfiguration loadConfiguration;

    public Configuration(String fileName) {
        this.loadConfiguration = new LoadConfiguration();
        this.fileName = fileName;
    }

    String getGameName() {
        return loadConfiguration.getText(fileName, "#BATTLE_NAME");
    }

    String getDescription() {
        return loadConfiguration.getText(fileName, "#ABOUT");
    }

    public List<Scheme> getPossibleMoves() {
        return loadConfiguration.getSchema(fileName);
    }
}
