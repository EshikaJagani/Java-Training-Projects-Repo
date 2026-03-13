package com.objective3.hangman.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

/**
 * Request body for guessing a single letter.
 * Accepts exactly one alphabetic character (A–Z / a–z).
 *
 * Provides both:
 *  - getLetter()       -> char (lowercased)  — use this if your controller expects a char
 *  - getLetterString() -> String (lowercased) — use this if your controller normalizes from String
 */

public class GuessRequest {

    @NotBlank(message = "Letter is required")
    @Pattern(regexp = "^[a-zA-Z]$", message = "Letter must be a single A-Z character")
    private String letter;

    public GuessRequest() {}

    /** Returns the letter as lowercased char (convenient for service/controller). */
    /*public char getLetter() {
        return Character.toLowerCase(letter.charAt(0));
    }*/
   
    /** Controller-use: return the letter as a lowercase STRING */
    public String getLetter() {
        return (letter == null) ? null : letter.trim().toLowerCase();
    }


    /** Returns the letter as a lowercased String (useful if you normalize elsewhere). */
    public String getLetterString() {
        return letter == null ? null : letter.trim().toLowerCase();
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
