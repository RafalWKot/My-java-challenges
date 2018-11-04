package com.rafalwkot.rps;

import com.rafalwkot.rps.presenter.Presentation;
import com.rafalwkot.rps.view.TextProvider;
import com.rafalwkot.rps.view.View;
import com.rafalwkot.rps.view.impl.ConsoleView;
import com.rafalwkot.rps.view.impl.FileTextProvider;

public class Application {

    private static final String GAMEFILETEXTS = "game_texts";

    public static void main(String[] args) {

        TextProvider textProvider = new FileTextProvider(GAMEFILETEXTS);   //obsługa wyjątka RUNTIMEEXCEPTION
        View view = new ConsoleView(textProvider);
        Presentation presentation = new Presentation(view);
        presentation.run();
    }
}

