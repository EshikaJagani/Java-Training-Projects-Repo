package com.objective3.hangman.repository;

import com.objective3.hangman.logic.GameLogic;
import com.objective3.hangman.model.HangmanGame;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class HangmanRepository {

    private final Map<String, GameLogic> games = new ConcurrentHashMap<>();

    public void save(String id, GameLogic game) {
        games.put(id, game);
    }

    public Optional<GameLogic> findById(String id) {
        return Optional.ofNullable(games.get(id));
    }

    public void delete(String id) {
        games.remove(id);
    }

    public List<HangmanGame> findAll() {
        List<HangmanGame> list = new ArrayList<>();
        for (var entry : games.entrySet()) {
            list.add(new HangmanGame(entry.getKey(), entry.getValue().getState()));
        }
        return list;
    }
}

/*
@Repository
public class HangmanRepository {

    private Map<String, GameLogic> games = new HashMap<>();

    public String save(GameLogic game) {
        String id = UUID.randomUUID().toString();
        games.put(id, game);
        return id;
    }

    public GameLogic get(String id) {
        return games.get(id);
    }

    public Collection<GameLogic> getAll() {
        return games.values();
    }

    public void delete(String id) {
        games.remove(id);
    }
}
    */