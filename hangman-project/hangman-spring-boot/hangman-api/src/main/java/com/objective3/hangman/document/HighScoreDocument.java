package com.objective3.hangman.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "highscores")
public class HighScoreDocument {

    @Id
    private String id;

    private String playerId;
    private String playerName; // redundant but useful for leaderboard display

    private String difficulty; // EASY, MEDIUM, HARD
    private int mistakes;
    private int timeTaken;     // seconds

    private boolean won;
    private LocalDateTime playedAt;

    public HighScoreDocument() {}

    public HighScoreDocument(
            String playerId,
            String playerName,
            String difficulty,
            int mistakes,
            int timeTaken,
            boolean won
    ) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.difficulty = difficulty;
        this.mistakes = mistakes;
        this.timeTaken = timeTaken;
        this.won = won;
        this.playedAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getPlayerId() { return playerId; }
    public String getPlayerName() { return playerName; }
    public String getDifficulty() { return difficulty; }
    public int getMistakes() { return mistakes; }
    public int getTimeTaken() { return timeTaken; }
    public boolean isWon() { return won; }
    public LocalDateTime getPlayedAt() { return playedAt; }

    public void setPlayerId(String playerId) { this.playerId = playerId; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setMistakes(int mistakes) { this.mistakes = mistakes; }
    public void setTimeTaken(int timeTaken) { this.timeTaken = timeTaken; }
    public void setWon(boolean won) { this.won = won; }
}