package com.objective3.hangman.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "words")
public class WordDocument {

    @Id
    private String id;

    private String text;

    private String difficulty; // Optional: EASY, MEDIUM, HARD

    public WordDocument() {}

    public WordDocument(String text) {
        this.text = text;
    }

    public WordDocument(String text, String difficulty) {
        this.text = text;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}