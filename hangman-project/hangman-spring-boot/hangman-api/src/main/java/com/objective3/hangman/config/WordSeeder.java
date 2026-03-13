package com.objective3.hangman.config;

import com.objective3.hangman.service.WordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WordSeeder implements CommandLineRunner {

    private final WordService wordService;

    public WordSeeder(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (wordService.getAllWords().isEmpty()) {
            wordService.addWord("GALAXY");
            wordService.addWord("NEBULA");
            wordService.addWord("ORBIT");
            wordService.addWord("ASTEROID");
            wordService.addWord("SUPERNOVA");
            wordService.addWord("COSMOS");

            System.out.println("🌟 Words seeded into MongoDB!");
        } else {
            System.out.println("Words collection already has data. Skipping seeding.");
        }
    }
}