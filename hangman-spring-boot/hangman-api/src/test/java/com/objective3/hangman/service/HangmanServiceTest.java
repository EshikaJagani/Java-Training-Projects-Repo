package com.objective3.hangman.service;

import com.objective3.hangman.model.Difficulty;
import com.objective3.hangman.repository.HangmanRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanServiceTest {

    @Test
    void createGame_and_getState_flow() {
        var repo = new HangmanRepository();
        var service = new HangmanService(repo);

        String id = service.createGame(Difficulty.EASY);
        assertNotNull(id);

        var state = service.getState(id);
        assertNotNull(state);
        assertFalse(state.isGameOver());
    }
}