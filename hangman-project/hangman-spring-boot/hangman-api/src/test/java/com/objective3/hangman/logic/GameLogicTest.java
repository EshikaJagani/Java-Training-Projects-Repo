package com.objective3.hangman.logic;

import com.objective3.hangman.model.Difficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


class GameLogicTest {

    @Test
    void startGame_initializesState() {
        // GameLogic game = new GameLogic();
        List<String> testWords = List.of("galaxy", "nebula", "cosmos");
        GameLogic game = new GameLogic(testWords);
        game.startGame(Difficulty.EASY);

        var state = game.getState();
        assertNotNull(state);
        assertEquals(0, state.getMistakes());
        assertFalse(state.isGameOver());
    }

    @Test
    void guess_rejectsNonLetter() {
        // GameLogic game = new GameLogic();
        List<String> testWords = List.of("galaxy", "nebula", "cosmos");
        GameLogic game = new GameLogic(testWords);
        game.startGame(Difficulty.MEDIUM);

        assertThrows(InvalidGuessException.class, () -> game.guess('1'));
    }
}