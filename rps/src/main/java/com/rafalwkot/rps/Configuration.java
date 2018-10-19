package com.rafalwkot.rps;

import java.util.List;

public class Configuration {

    private final String fileTexts;
    private final String fileScheme;

    public Configuration(String fileTexts, String fileScheme) {
        this.fileTexts = fileTexts;
        this.fileScheme = fileScheme;
    }

    public String getGameName() {
        return LoadText.getText(fileTexts, "#BATTLE_NAME");
    }

    public String getDescription() {
        return LoadText.getText(fileTexts, "#ABOUT_BATTLE");
    }

    public List<Scheme> getPossibleMoves() {
        return LoadScheme.getScheme(fileScheme);
    }
}
