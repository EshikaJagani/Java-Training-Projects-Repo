package com.objective3.hangman.controller;

import com.objective3.hangman.document.WordDocument;
import com.objective3.hangman.service.WordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
@CrossOrigin(origins = "http://localhost:5173")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    // GET all words
    @GetMapping
    public List<WordDocument> getAllWords() {
        return wordService.getAllWords();
    }

    // Add a new word (admin)
    @PostMapping
    public WordDocument addWord(@RequestBody WordDocument doc) {
        return wordService.addWord(doc.getText());
    }

    // Get a random word
    @GetMapping("/random")
    public WordDocument getRandomWord() {
        return wordService.getRandomWord();
    }
}