package com.rafalwkot.rps;

import com.rafalwkot.rps.presenter.Presentation;
import com.rafalwkot.rps.view.TextProvider;
import com.rafalwkot.rps.view.View;
import com.rafalwkot.rps.view.impl.ConsoleView;
import com.rafalwkot.rps.view.impl.FileTextProvider;

import java.io.UnsupportedEncodingException;
import java.util.MissingResourceException;

public class Application {

    private static final String GAMEFILETEXTS = "game_texts";

    public static void main(String[] args) {
        try {
            TextProvider textProvider = new FileTextProvider(GAMEFILETEXTS);
            View view = new ConsoleView(textProvider);
            Presentation presentation = new Presentation(view);
            presentation.run();
        } catch (MissingResourceException e) {
            System.out.print("Dane aplikacji nie zostały wczytane poprawnie. Brakuje pliku:");
            System.out.println(GAMEFILETEXTS);
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Złe kodowanie pliku z danymi. Zakoduj w UTF-8.");
            System.exit(-1);
        }
    }
}

