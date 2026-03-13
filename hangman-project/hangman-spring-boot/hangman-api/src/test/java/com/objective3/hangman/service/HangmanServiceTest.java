package com.objective3.hangman.service;

import com.objective3.hangman.document.WordDocument;
import com.objective3.hangman.model.Difficulty;
import com.objective3.hangman.repository.HangmanRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class HangmanServiceTest {

    @Test
    void createGame_and_getState_flow() {
        // var repo = new HangmanRepository();
        // var service = new HangmanService(repo);
        

        HangmanRepository repo = Mockito.mock(HangmanRepository.class);
        WordService wordService = Mockito.mock(WordService.class);
        HighScoreService highScoreService = Mockito.mock(HighScoreService.class);

        // mock the words returned from Mongo
        Mockito.when(wordService.getAllWords()).thenReturn(List.of(new WordDocument("galaxy")));

        HangmanService service = new HangmanService(repo, wordService, highScoreService);
        
        String id = service.createGame(Difficulty.EASY);
        assertNotNull(id);

        var state = service.getState(id);
        assertNotNull(state);
        assertFalse(state.isGameOver());
    }
}