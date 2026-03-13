package com.objective3.hangman.repository;

import com.objective3.hangman.document.HighScoreDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HighScoreRepository extends MongoRepository<HighScoreDocument, String> {

    // Filter by difficulty sorted by best performance:
    // win => true first, then least mistakes, then shortest time
    List<HighScoreDocument> findByDifficultyOrderByWonDescMistakesAscTimeTakenAsc(String difficulty);

    // Optional: get by player
    List<HighScoreDocument> findByPlayerIdOrderByPlayedAtDesc(String playerId);
}