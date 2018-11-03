package com.rafalwkot.rps.presenter;

import com.rafalwkot.rps.view.TextProvider;

public class Presentation {

    private TextProvider textProvider;

    public Presentation(TextProvider textProvider) {
        this.textProvider = textProvider;
    }

    public void printGameInformation() {
        System.out.println(textProvider.getText("GAME_NAME"));
        System.out.println(textProvider.getText("AUTHOR_NAME"));
    }
}
