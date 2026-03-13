package com.objective3.hangman.service;

import com.objective3.hangman.document.WordDocument;
import com.objective3.hangman.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final Random random = new Random();

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /** Get ALL words from MongoDB */
    public List<WordDocument> getAllWords() {
        return wordRepository.findAll();
    }

    /** Insert a new word into MongoDB (used for seeding / admin) */
    public WordDocument addWord(String text) {
        WordDocument doc = new WordDocument(text);
        return wordRepository.save(doc);
    }

    /** Get ONE random word from MongoDB */
    public WordDocument getRandomWord() {
        List<WordDocument> words = wordRepository.findAll();

        if (words.isEmpty()) {
            throw new RuntimeException("No words found in MongoDB. Please insert some words.");
        }

        return words.get(random.nextInt(words.size()));
    }
}