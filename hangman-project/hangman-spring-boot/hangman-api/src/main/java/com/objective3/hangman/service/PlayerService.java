package com.objective3.hangman.service;

import com.objective3.hangman.document.PlayerDocument;
import com.objective3.hangman.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository repo;

    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    /** Register a new player (or reuse existing by name) */
    public PlayerDocument registerPlayer(String name) {
        Optional<PlayerDocument> existing = repo.findByNameIgnoreCase(name);

        if (existing.isPresent()) {
            return existing.get();
        }

        PlayerDocument newPlayer = new PlayerDocument(name);
        return repo.save(newPlayer);
    }

    public Optional<PlayerDocument> getPlayerById(String id) {
        return repo.findById(id);
    }

    public Optional<PlayerDocument> getPlayerByName(String name) {
        return repo.findByNameIgnoreCase(name);
    }
}