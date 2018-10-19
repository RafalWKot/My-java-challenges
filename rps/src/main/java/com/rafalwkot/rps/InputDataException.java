package com.rafalwkot.rps;

public class InputDataException extends RuntimeException {

    public InputDataException() {
        super(LoadText.getText(Application.GAMEFILE, "EXCEPTION_MESSAGE"));
    }
}
