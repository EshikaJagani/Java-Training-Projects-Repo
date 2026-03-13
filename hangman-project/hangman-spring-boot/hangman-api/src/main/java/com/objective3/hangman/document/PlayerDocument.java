package com.objective3.hangman.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "players")
public class PlayerDocument {

    @Id
    private String id;

    private String name;

    private LocalDateTime createdAt;

    public PlayerDocument() {}

    public PlayerDocument(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}