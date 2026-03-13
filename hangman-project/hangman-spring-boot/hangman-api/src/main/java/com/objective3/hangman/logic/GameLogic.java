package com.objective3.hangman.logic;

import com.objective3.hangman.model.Difficulty;
import com.objective3.hangman.model.GameStateResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

public class GameLogic implements Cloneable {

    private final List<String> words = new ArrayList<>();
    private String randomWord;
    private char[] wordArr;
    private char[] guessArr;
    private int mistakes;
    private int maxMistakes;
    private Difficulty difficulty;

    public Difficulty getDifficulty() { return difficulty; }

    // NEW: Allow HangmanService to inject MongoDB words
    public GameLogic(List<String> words) {
    this.words.clear();
    this.words.addAll(words);
    }

    public void startGame(Difficulty diff) {
        this.difficulty = diff;
        this.maxMistakes = switch (diff) {
            case EASY -> 8;
            case MEDIUM -> 6;
            case HARD -> 4;
        };
        this.mistakes = 0;

        // if (!loadWordsFromClasspath()) {
        //     throw new IllegalStateException("Could not load words.txt from resources");
        // }
        if (words.isEmpty()) {
            throw new IllegalStateException("Words list is empty — ensure MongoDB is seeded");
        }
        selectRandomWord();
        setupGuessingArray();
    }

    public void guess(char letter) {
        if (!Character.isLetter(letter)) {
            throw new InvalidGuessException("Invalid input. Letter only!");
        }
        letter = Character.toLowerCase(letter);

        boolean correct = false;
        for (int i = 0; i < wordArr.length; i++) {
            if (wordArr[i] == letter) {
                guessArr[i] = letter;
                correct = true;
            }
        }
        if (!correct) {
            mistakes++;
        }
    }

    public boolean isGameOver() {
        return mistakes >= maxMistakes || Arrays.equals(guessArr, wordArr);
    }

    public boolean isWin() {
        return Arrays.equals(guessArr, wordArr);
    }

    public GameStateResponse getState() {
        return new GameStateResponse(
                maskedProgress(),       // "_ a _ g m a n"
                mistakes,
                maxMistakes,
                isWin(),
                isGameOver()
        );
    }

    // -------- helpers --------

    private boolean loadWordsFromClasspath() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("words.txt");
            if (inputStream == null) return false;

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String w = line.trim();
                    if (!w.isEmpty()) words.add(w.toLowerCase());
                }
            }
            return !words.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    private void selectRandomWord() {
        Random rand = new Random();
        randomWord = words.get(rand.nextInt(words.size())).toLowerCase(Locale.ROOT);
        wordArr = randomWord.toCharArray();
    }

    private void setupGuessingArray() {
        guessArr = new char[wordArr.length];
        Arrays.fill(guessArr, '_');
    }

    private String maskedProgress() {
        // Return spaced characters: "_ a _ g m a n"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guessArr.length; i++) {
            sb.append(guessArr[i]);
            if (i < guessArr.length - 1) sb.append(' ');
        }
        return sb.toString();
    }

    // Optional overrides
    @Override
    public String toString() {
        return "Hangman Game @ " + LocalDateTime.now();
    }

    @Override
    public GameLogic clone() {
        try {
            return (GameLogic) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    
    
}

/*
public class GameLogic {

    private Difficulty difficulty;
    private ArrayList<String> words = new ArrayList<>();
    private String randomWord;
    private char[] wordArr;
    private char[] guessArr;
    private int mistakes = 0;
    private int maxMistakes;

    public void startGame(Difficulty diff) {
        this.difficulty = diff;
        this.maxMistakes = (diff == Difficulty.EASY) ? 8 :
                           (diff == Difficulty.MEDIUM) ? 6 : 4;

        loadWordsFromFile();
        selectRandomWord();
        setupGuessingArray();
    }

    public boolean guess(char letter) throws InvalidGuessException {
        if (!Character.isLetter(letter))
            throw new InvalidGuessException("Letter only!");

        letter = Character.toLowerCase(letter);

        boolean correct = false;
        for (int i = 0; i < wordArr.length; i++) {
            if (wordArr[i] == letter) {
                guessArr[i] = letter;
                correct = true;
            }
        }

        if (!correct) mistakes++;
        return correct;
    }

    public boolean isGameOver() {
        return mistakes >= maxMistakes || Arrays.equals(guessArr, wordArr);
    }

    public boolean isWin() {
        return Arrays.equals(guessArr, wordArr);
    }

    public GameStateResponse getState() {
        return new GameStateResponse(
            new String(guessArr),
            mistakes,
            maxMistakes,
            isWin(),
            isGameOver()
        );
    }
}

*/