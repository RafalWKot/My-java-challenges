package com.rafalwkot.rps.presenter;


import com.rafalwkot.rps.model.*;
import com.rafalwkot.rps.view.View;

import java.util.InputMismatchException;

public class Presentation {

    private View view;

    public Presentation(View view) {
        this.view = view;
    }

    public void run() {
        view.printGameInformation();
        view.setPlayerName();
        view.printPlayerWelcome();
        boolean endGameCondition = true;
        while (endGameCondition) {
            view.printGameVariantsToChoose();
            SchemeProvider scheme = new SchemeProvider(
                    new SchemeCreator().getScheme(
                            Variant.valueOf(view.getGameVariant()).orElseGet(() -> {
                                view.printWrongVariant();
                                return Variant.RPS;
                            })));
            view.printInformationAboutVariant(scheme.getMoves());
            Game game = new Game();
            int quantityRounds = view.getQuantityRounds();
            for (int i = 0; i < quantityRounds; i++) {
                view.printRoundNumber(i);
                boolean correctSymbol = false;
                while (!correctSymbol) {
                    try {
                        Round round = new Round(scheme, Move.valueOf(
                                view.getPlayerSymbol(scheme.getMoves())).orElseThrow(InputMismatchException::new));
                        correctSymbol = true;
                        Result result = game.play(round);
                        view.printActualResult(result, game.getHumanMove(i), game.getComputerMove(i),  game.getHumanPoints(), game.getComputerPoints());
                    } catch (InputMismatchException e) {
                        view.printWrongInput();
                    }
                }

            }
            view.printCongratulations(game.isHumanWin());
            endGameCondition = view.getGamaReplay();
        }
        view.printGoodbye();
    }
}
