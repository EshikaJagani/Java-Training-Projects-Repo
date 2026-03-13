package com.objective3.hangman.repository;

import com.objective3.hangman.document.PlayerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<PlayerDocument, String> {

    Optional<PlayerDocument> findByNameIgnoreCase(String name);
}