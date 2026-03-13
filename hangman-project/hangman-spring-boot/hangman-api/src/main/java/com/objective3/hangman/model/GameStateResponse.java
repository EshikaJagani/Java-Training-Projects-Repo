package com.objective3.hangman.model;

public class GameStateResponse {
    private String progress;    // e.g. "_ a _ g m a n"
    private int mistakes;       // current wrong guesses
    private int maxMistakes;    // limit based on difficulty
    private boolean win;
    private boolean gameOver;

    public GameStateResponse() {}

    public GameStateResponse(String progress, int mistakes, int maxMistakes, boolean win, boolean gameOver) {
        this.progress = progress;
        this.mistakes = mistakes;
        this.maxMistakes = maxMistakes;
        this.win = win;
        this.gameOver = gameOver;
    }

    // public boolean isGameOver() { return gameOver;}

    // public boolean isWin() { return win;}

    public String getProgress() { return progress; }
    public int getMistakes() { return mistakes; }
    public int getMaxMistakes() { return maxMistakes; }
    public boolean isWin() { return win; }
    public boolean isGameOver() { return gameOver; }

    public void setProgress(String progress) { this.progress = progress; }
    public void setMistakes(int mistakes) { this.mistakes = mistakes; }
    public void setMaxMistakes(int maxMistakes) { this.maxMistakes = maxMistakes; }
    public void setWin(boolean win) { this.win = win; }
    public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }

    
    // public boolean isGameOver() { return gameOver;}

}

/*
public class GameStateResponse {
    public String progress;
    public int mistakes;
    public int maxMistakes;
    public boolean win;
    public boolean gameOver;

    public GameStateResponse(String progress, int mistakes, int max, boolean win, boolean gameOver) {
        this.progress = progress;
        this.mistakes = mistakes;
        this.maxMistakes = max;
        this.win = win;
        this.gameOver = gameOver;
    }
}
    */