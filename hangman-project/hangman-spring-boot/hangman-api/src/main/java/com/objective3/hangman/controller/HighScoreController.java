package com.objective3.hangman.controller;

import com.objective3.hangman.document.HighScoreDocument;
import com.objective3.hangman.service.HighScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/highscores")
@CrossOrigin(origins = "http://localhost:5173")
public class HighScoreController {

    private final HighScoreService service;

    public HighScoreController(HighScoreService service) {
        this.service = service;
    }

    // Save a high score entry
    @PostMapping
    public HighScoreDocument saveScore(
            @RequestParam String playerId,
            @RequestParam String playerName,
            @RequestParam String difficulty,
            @RequestParam int mistakes,
            @RequestParam int timeTaken,
            @RequestParam boolean won
    ) {
        return service.saveScore(playerId, playerName, difficulty, mistakes, timeTaken, won);
    }

    // Get leaderboard for a difficulty
    @GetMapping("/top")
    public List<HighScoreDocument> getTopScores(@RequestParam String difficulty) {
        return service.getTopScores(difficulty);
    }

    // Get all scores for a specific player
    @GetMapping("/player/{playerId}")
    public List<HighScoreDocument> getPlayerScores(@PathVariable String playerId) {
        return service.getByPlayer(playerId);
    }
}