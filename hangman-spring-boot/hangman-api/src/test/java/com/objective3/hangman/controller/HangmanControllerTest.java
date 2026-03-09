package com.objective3.hangman.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.objective3.hangman.model.Difficulty;
import com.objective3.hangman.model.GameStateResponse;
import com.objective3.hangman.model.GuessRequest;
import com.objective3.hangman.service.HangmanService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.annotation.Resource;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HangmanController.class)
class HangmanControllerTest {

    @Resource
    private MockMvc mvc;

    @MockBean
    private HangmanService service;

    private final ObjectMapper om = new ObjectMapper();

    @Test
    void startGame_returnsId() throws Exception {
        Mockito.when(service.createGame(Difficulty.EASY)).thenReturn("abc123");

        mvc.perform(post("/api/hangman/start")
                .param("difficulty", "EASY"))
           .andExpect(status().isOk())
           .andExpect(content().string("abc123"));
    }

    @Test
    void guess_returnsState() throws Exception {
        var state = new GameStateResponse("_ _ _", 0, 6, false, false);
        Mockito.when(service.guess(anyString(), anyChar())).thenReturn(state);

        GuessRequest req = new GuessRequest();
        req.setLetter("a");

        mvc.perform(post("/api/hangman/xyz/guess")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(req)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.progress").value("_ _ _"));
    }
}
