package com.rafalwkot.rps.view;

import com.rafalwkot.rps.model.Move;
import com.rafalwkot.rps.model.Result;

import java.util.EnumSet;

public interface View {

    void printGameInformation();

    void setPlayerName();

    void printPlayerWelcome();

    void printGameVariantsToChoose();

    int getGameVariant();

    void printWrongVariant();

    void printInformationAboutVariant(EnumSet<Move> movesSet);

    int getQuantityRounds();

    void printRoundNumber(int i);

    int getPlayerSymbol(EnumSet<Move> movesSet);

    void printWrongInput();

    void printActualResult(Result result, Move humanMove, Move computerMove, int humanPoints, int computerPoints);

    void printCongratulations(boolean isHumanWin);

    boolean getGamaReplay();

    void printGoodbye();
}
