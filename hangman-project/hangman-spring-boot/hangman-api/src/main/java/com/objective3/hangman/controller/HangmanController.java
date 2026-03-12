package com.objective3.hangman.controller;

import com.objective3.hangman.model.Difficulty;
import com.objective3.hangman.model.GameStateResponse;
import com.objective3.hangman.model.GuessRequest;
import com.objective3.hangman.model.HangmanGame;
import com.objective3.hangman.service.HangmanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/hangman")
// @CrossOrigin(origins = "http://localhost:3000") // handy later if when I add React
public class HangmanController {

    private final HangmanService service;

    public HangmanController(HangmanService service) {
        this.service = service;
    }

    /** Create a new game and return the generated gameId */
    @PostMapping("/start")
//    public ResponseEntity<String> start(@RequestParam Difficulty difficulty) {
    public ResponseEntity<String> start(@RequestParam("difficulty") Difficulty difficulty) {
        String gameId = service.createGame(difficulty);
        return ResponseEntity.ok(gameId);
    }

    /** Submit a guess for a specific gameId and return updated state */
    @PostMapping("/{id}/guess")
    public ResponseEntity<GameStateResponse> guess(
            @PathVariable("id") @NotBlank String id,
            @Valid @RequestBody GuessRequest request) {
        return ResponseEntity.ok(service.guess(id, request.getLetter()));
    }

    /** Get the latest state of a game */
    @GetMapping("/{id}/state")
    public ResponseEntity<GameStateResponse> getState(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getState(id));
    }

    /** List all current games with their IDs and current progress (for debugging) */
    @GetMapping
    public ResponseEntity<List<HangmanGame>> list() {
        return ResponseEntity.ok(service.listGames());
    }

    /** Delete a game */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.deleteGame(id);
        return ResponseEntity.ok("Game deleted");
    }
}

/*
@RestController
@RequestMapping("/api/hangman")
public class HangmanController {

    @Autowired
    private HangmanService service;

    @PostMapping("/start")
    public ResponseEntity<String> start(
        @RequestParam Difficulty difficulty
    ) {
        String gameId = service.createGame(difficulty);
        return ResponseEntity.ok(gameId);
    }

    @PostMapping("/{id}/guess")
    public ResponseEntity<GameStateResponse> guess(
        @PathVariable String id,
        @RequestBody GuessRequest request
    ) throws Exception {
        return ResponseEntity.ok(service.guess(id, request.getLetter()));
    }

    @GetMapping("/{id}/state")
    public ResponseEntity<GameStateResponse> getState(
        @PathVariable String id
    ) throws Exception {
        return ResponseEntity.ok(service.getState(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.deleteGame(id);
        return ResponseEntity.ok("Game deleted");
    }
}
    */