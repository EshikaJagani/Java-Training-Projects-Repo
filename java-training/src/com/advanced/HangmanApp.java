package com.advanced;
import java.util.Scanner;

/*
Final Practice: Hangman

read the words from a file - throw the exception when the file is not found
randomly select a word
create 2 char arrays, one for the word and one for guessing
loop
- read a character from user
- check whether the letter is contained by the random word
    -- yes: change the guessing char array + print out that the guessing was correct
            - also check whether the game is finished or not
    -- no: decrease the number of chances + print out that the guessing was incorrect
            - also check whether we have more chances
- end the game based on the scenarios listed above
*/


public class HangmanApp {
    public static void main(String[] args) {
        
        // Closing it here would close System.in and break those reads.
        Scanner sc = new Scanner(System.in);

        Difficulty diff = promptDifficulty(sc);  

        GameLogic game = new GameLogic();
        game.startGame(diff);                    
    }

    // using INPUT/OUTPUT + EXCEPTIONS + ENUM here to prompt user for difficulty level and handle invalid inputs
    private static Difficulty promptDifficulty(Scanner sc) {
        while (true) {
            System.out.println("Choose difficulty (type 1/2/3 or name):");
            System.out.println("  1) EASY");
            System.out.println("  2) MEDIUM");
            System.out.println("  3) HARD");
            System.out.print("Your choice: ");

            String raw = sc.nextLine().trim();

            // Accept 1/2/3
            switch (raw) {
                case "1": return Difficulty.EASY;
                case "2": return Difficulty.MEDIUM;
                case "3": return Difficulty.HARD;
            }

            // Accept EASY/MEDIUM/HARD (any case)
            try {
                return Difficulty.valueOf(raw.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid choice. Please enter 1, 2, 3, or EASY/MEDIUM/HARD.");
            }
        }
    }
}