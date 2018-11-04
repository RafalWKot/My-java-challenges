package com.rafalwkot.rps.view.impl;

import com.rafalwkot.rps.model.Move;
import com.rafalwkot.rps.model.Result;
import com.rafalwkot.rps.view.TextProvider;
import com.rafalwkot.rps.view.View;

import java.util.EnumSet;
import java.util.Scanner;

public class ConsoleView implements View {

    private static String PLAYERNAME;
    private Scanner input = new Scanner(System.in);
    private TextProvider textProvider;

    public ConsoleView(TextProvider textProvider) {
        this.textProvider = textProvider;
    }

    @Override
    public void printGameInformation() {
        System.out.println(textProvider.getText("GAME_NAME"));
        System.out.println(textProvider.getText("AUTHOR_NAME"));
    }

    @Override
    public void setPlayerName() {
        System.out.print(textProvider.getText("ASK_PLAYER_NAME"));
        PLAYERNAME = input.nextLine();

    }

    @Override
    public void printPlayerWelcome() {
        System.out.println("\n" + textProvider.getText("INTRODUCE") + " " + PLAYERNAME + "!");
        System.out.println(textProvider.getText("BEGINNING"));
    }

    @Override
    public void printGameVariantsToChoose() {
        System.out.println(textProvider.getText("GAME_VARIANT"));
        System.out.println(textProvider.getText("RPS_GAME_NAME"));
        System.out.println(textProvider.getText("RPSSL_GAME_NAME"));
        System.out.print(textProvider.getText("PLAYER_CHOICE"));
    }

    @Override
    public int getGameVariant() {
        return input.nextInt();
    }

    @Override
    public void printWrongVariant() {
        System.out.println(textProvider.getText("WRONG_VARIANT"));
    }

    @Override
    public void printInformationAboutVariant(EnumSet<Move> movesSet) {
        if (movesSet.size() == 3) {
            System.out.println("\n" + textProvider.getText("RPS_ABOUT_BATTLE"));
        } else {
            System.out.println("\n" + textProvider.getText("RPSSL_ABOUT_BATTLE"));
        }
    }

    @Override
    public int getQuantityRounds() {
        System.out.print(textProvider.getText("QUANTITY_ROUNDS"));
        return input.nextInt();
    }

    @Override
    public void printRoundNumber(int i) {
        System.out.println("\n" + textProvider.getText("NO_ROUND") + (i + 1));
    }

    @Override
    public int getPlayerSymbol(EnumSet<Move> movesSet) {
        System.out.println(textProvider.getText("SYMBOL"));
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Move move: movesSet) {
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(move.getText());
            stringBuilder.append("\n");
            i++;
        }
        System.out.println(stringBuilder.toString());
        System.out.print(textProvider.getText("PLAYER_CHOICE"));
        return input.nextInt();
    }

    @Override
    public void printWrongInput() {
        System.out.println(textProvider.getText("WRONG_INPUT"));
    }

    @Override
    public void printActualResult(Result result, Move humanMove, Move computerMove, int humanPoints, int computerPoints) {
        System.out.print(textProvider.getText("PLAYER_CHOICE"));
        System.out.print(humanMove.getText());
        System.out.print("   " + textProvider.getText("COMPUTER_SYMBOL"));
        System.out.println(computerMove.getText());
        System.out.print(result.getText() + " ");
        System.out.print(textProvider.getText("CURRENT_RESULT"));
        System.out.println(PLAYERNAME + " " + humanPoints + " " +
                "Computer " + computerPoints);
    }

    @Override
    public void printCongratulations(boolean isHumanWin) {
        if (isHumanWin) {
            System.out.println(textProvider.getText("CONGRATULATIONS") + "\n");
        } else {
            System.out.println(textProvider.getText("DEFEAT") + "\n");
        }
    }

    @Override
    public boolean getGamaReplay() {
        System.out.println(textProvider.getText("ASK_FOR_REPLAY"));
        return (input.next().equals("1"));
    }
    @Override
    public void printGoodbye() {
        System.out.println(textProvider.getText("GOODBYE"));
    }
}
