package com.objective3.hangman.service;

import com.objective3.hangman.document.HighScoreDocument;
import com.objective3.hangman.repository.HighScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighScoreService {

    private final HighScoreRepository repo;

    public HighScoreService(HighScoreRepository repo) {
        this.repo = repo;
    }

    // Save a new high score entry
    public HighScoreDocument saveScore(String playerId,
                                       String playerName,
                                       String difficulty,
                                       int mistakes,
                                       int timeTaken,
                                       boolean won) {

        HighScoreDocument score = new HighScoreDocument(
                playerId,
                playerName,
                difficulty,
                mistakes,
                timeTaken,
                won
        );

        return repo.save(score);
    }

    // Get leaderboard by difficulty
    public List<HighScoreDocument> getTopScores(String difficulty) {
        return repo.findByDifficultyOrderByWonDescMistakesAscTimeTakenAsc(difficulty);
    }

    // Optional: get all scores by player
    public List<HighScoreDocument> getByPlayer(String playerId) {
        return repo.findByPlayerIdOrderByPlayedAtDesc(playerId);
    }
}