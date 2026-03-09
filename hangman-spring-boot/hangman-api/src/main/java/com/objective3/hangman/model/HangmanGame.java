package com.objective3.hangman.model;

public class HangmanGame {
    private String id;
    private GameStateResponse state;

    public HangmanGame() {}

    public HangmanGame(String id, GameStateResponse state) {
        this.id = id;
        this.state = state;
    }

    public String getId() { return id; }
    public GameStateResponse getState() { return state; }
    public void setId(String id) { this.id = id; }
    public void setState(GameStateResponse state) { this.state = state; }
}