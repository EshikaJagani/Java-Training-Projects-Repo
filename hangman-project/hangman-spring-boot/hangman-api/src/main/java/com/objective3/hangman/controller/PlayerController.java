package com.objective3.hangman.controller;

import com.objective3.hangman.document.PlayerDocument;
import com.objective3.hangman.service.PlayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "http://localhost:5173")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    // Register a new player, or return existing if name already exists
    @PostMapping("/register")
    public PlayerDocument register(@RequestParam String name) {
        return service.registerPlayer(name);
    }

    @GetMapping("/{id}")
    public PlayerDocument getById(@PathVariable String id) {
        return service.getPlayerById(id)
                .orElseThrow(() -> new RuntimeException("Player not found: " + id));
    }

    @GetMapping("/by-name/{name}")
    public PlayerDocument getByName(@PathVariable String name) {
        return service.getPlayerByName(name)
                .orElseThrow(() -> new RuntimeException("Player not found: " + name));
    }
}