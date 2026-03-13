package com.objective3.hangman.service;

import com.objective3.hangman.logic.GameLogic;
import com.objective3.hangman.model.Difficulty;
import com.objective3.hangman.model.GameStateResponse;
import com.objective3.hangman.model.HangmanGame;
import com.objective3.hangman.repository.HangmanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HangmanService {

    private final WordService wordService;

    private final HangmanRepository repository;

    private final HighScoreService highScoreService;

    // public HangmanService(HangmanRepository repository) {
    //     this.repository = repository;
    // }
    public HangmanService(HangmanRepository repository, WordService wordService, HighScoreService highScoreService) {
    this.repository = repository;
    this.wordService = wordService;
    this.highScoreService = highScoreService;
}

    public String createGame(Difficulty difficulty) {
        // GameLogic game = new GameLogic();
        // game.startGame(difficulty); // loads words and initializes arrays
        // 1. Get ALL words from MongoDB
        List<String> words = wordService.getAllWords()
                                .stream()
                                .map(w -> w.getText())
                                .toList();
        
        if (words.isEmpty()) {
            throw new RuntimeException("No words found in MongoDB. Please insert words.");
        }


        // 2. Create GameLogic with DB words
        GameLogic game = new GameLogic(words);

        // 3. Start the game (difficulty sets max mistakes)
        game.startGame(difficulty);
            String id = UUID.randomUUID().toString();
            repository.save(id, game);
            return id;
    }

    public GameStateResponse guess(String gameId, char letter, String playerId, String playerName, long startTime) {

        GameLogic game = repository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException("Game not found: " + gameId));

        // Let the game process the guess
        game.guess(letter);

        GameStateResponse state = game.getState();

        // If gameOver → save high score
        if (state.isGameOver()) {
            int timeTaken = (int)((System.currentTimeMillis() - startTime) / 1000);

            highScoreService.saveScore(
                playerId,
                playerName,
                game.getDifficulty().name(),
                state.getMistakes(),
                timeTaken,
                state.isWin()
            );
        }

        return state;
    }
    /*public GameStateResponse guess(String gameId, char letter) {
        GameLogic game = repository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game not found: " + gameId));

        game.guess(letter); // may throw InvalidGuessException (handled by advice)
        return game.getState();
    }*/

    public GameStateResponse getState(String gameId) {
        GameLogic game = repository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game not found: " + gameId));
        return game.getState();
    }

    public List<HangmanGame> listGames() {
        return repository.findAll();
    }

    public void deleteGame(String gameId) {
        repository.delete(gameId);
    }
}

/* 
@Service
public class HangmanService {

    @Autowired
    private HangmanRepository repo;

    public String createGame(Difficulty difficulty) {
        GameLogic game = new GameLogic();
        game.startGame(difficulty);
        return repo.save(game);
    }

    public GameStateResponse guess(String gameId, char letter) throws Exception {
        GameLogic game = repo.get(gameId);
        if (game == null) throw new Exception("Game not found");

        game.guess(letter);
        return game.getState();
    }

    public GameStateResponse getState(String gameId) throws Exception {
        GameLogic game = repo.get(gameId);
        if (game == null) throw new Exception("Game not found");

        return game.getState();
    }

    public void deleteGame(String gameId) {
        repo.delete(gameId);
    }
}
    */