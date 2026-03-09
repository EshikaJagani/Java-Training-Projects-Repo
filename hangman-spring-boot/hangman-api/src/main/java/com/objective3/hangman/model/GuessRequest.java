package com.objective3.hangman.model;

import jakarta.validation.constraints.Pattern;

public class GuessRequest {

    @Pattern(regexp = "^[a-zA-Z]$", message = "Letter must be a single A-Z character")
    private String letter;

    public char getLetter() {
        return Character.toLowerCase(letter.charAt(0));
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}

/* 
public class GuessRequest {
    private char letter;
    public char getLetter() { return letter; }
}
    */
