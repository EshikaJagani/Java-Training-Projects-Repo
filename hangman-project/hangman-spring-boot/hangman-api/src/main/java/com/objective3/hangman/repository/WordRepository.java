package com.objective3.hangman.repository;

import com.objective3.hangman.document.WordDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<WordDocument, String> {
}