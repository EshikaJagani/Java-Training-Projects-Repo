package com.advanced;
import java.io.*;
import java.nio.file.*;
// TIME TYPES
import java.time.LocalDateTime;          
import java.util.*;

/*
// GAME LOGIC CLASS:
// ------------------------------------------------------------------------------------
// Create a function to read the words from a file and return a list of words
// ------------------------------------------------------------------------------------
// read the words from a .txt file (BufferedReader, FileReader)
// throw exception when the file is not found (try-catch block)
  

// ------------------------------------------------------------------------------------
// Create a file to store the words for the game (e.g., words.txt) and populate it with some words
// ------------------------------------------------------------------------------------
// this file will be read by the function created above to get the list of words for the game
// let's have words like "java", "hangman", "advanced", "intermediate", "expert" in the file

// ------------------------------------------------------------------------------------
// Now creating 2 char arrays
// ------------------------------------------------------------------------------------
// randomly select a word from the list (Random)
// create 2 char arrays, one for the word and one for guessing (this one will be initialized with underscores)
// first array (wordArr): (.toCharArray() method)
// second array (guessArr): use for loop to initialize with underscores 

// ------------------------------------------------------------------------------------
// Number of chances and taking input from user
// ------------------------------------------------------------------------------------
// first display the word length, and number of chances the user has
// Take input from user (Scanner) (till chances = 0) (while loop, decreament chances)
// now check if the char is in the wordArr, then update guessArr loc with the char and rest stay "_"
// set a flag, to correct and incorrect guesses
// if correct: print something, and also check the word is fully guessed. (if yes: U won; if no: continue)
// if incorrect: print something, and decreament chances, and also check for chances ==0 then print lost and reveal the word
// this whole game logic will be in a separate class (GameLogic) and the main method will be in HangmanApp class, where we will call the methods from GameLogic to run the game
*/

/*
- ENUM: For difficulty levels
- INNER CLASS: HangmanVisual
- ARRAY FUNCTIONS: Arrays.fill(), Arrays.equals()
- MATH FUNCTIONS: Math.random()
- TIME TYPES: LocalDateTime to show when the game was started
- INPUT AND OUTPUT: System.in, System.out, System.err, Scanner class
- STRING or STRINGBUILDER: STRING for words and user input, SB for hangman visual
- OBJECT CLASS: toString(), equals() and clone()
- COLLECTIONS: ArrayList for wordArr and guessArr, and also for storing the list of words read from the file
- GENERICS: printItem(T item) (just debugging purpose and also for printing)
- EXCEPTIONS: try-catch block and custom exception (invalid guess exception for non-letter inputs)
- FILE HANDLING: file class, FileReader, BufferedReader, Scanner, Path class, copying files
 */

// using ENUM here (game difficulty) to set the number of chances based on difficulty level chosen by user at the start of the game (easy, medium, hard)
enum Difficulty {
    EASY, MEDIUM, HARD
}

// CUSTOM EXCEPTION
class InvalidGuessException extends Exception {
    public InvalidGuessException(String msg) {
        super(msg);
    }
}

// using OBJECT CLASS (cloneable) to allow cloning of the game state if needed (for example, to save the game state or to implement an undo feature in the future)
public class GameLogic implements Cloneable {   
    
    // INNER CLASS (manages hangman drawing using StringBuilder)
    class HangmanVisual {
        private StringBuilder sb = new StringBuilder();

        public String getVisual(int mistakes, Difficulty diff) {
        sb.setLength(0);  // clear

        sb.append("Mistakes: ").append(mistakes).append("\n");

        switch (diff) {

            // ------------------- EASY (8 stages) -------------------
            case EASY:
                switch (mistakes) {
                    case 0: sb.append(""); break;
                    case 1: sb.append("  O"); break;
                    case 2: sb.append("  O\n  | "); break;
                    case 3: sb.append("  O\n  | \n /  "); break;
                    case 4: sb.append("  O\n  | \n / \\"); break;
                    case 5: sb.append("  O\n /| \n / \\"); break;
                    case 6: sb.append("  O\n /|\\\n / \\"); break;
                    case 7: sb.append(" \\O\n /|\\\n / \\"); break;
                    case 8: sb.append("  O\n /|\\\n / \\"); break;
                }
                break;

            // ------------------- MEDIUM (6 stages) -------------------
            case MEDIUM:
                switch (mistakes) {
                    case 0: sb.append(""); break;
                    case 1: sb.append("  O"); break;
                    case 2: sb.append("  O\n  |"); break;
                    case 3: sb.append("  O\n /|"); break;
                    case 4: sb.append("  O\n /|\\"); break;
                    case 5: sb.append("  O\n /|\\\n / "); break;
                    case 6: sb.append("  O\n /|\\\n / \\"); break;
                }
                break;

            // ------------------- HARD (4 stages) -------------------
            case HARD:
                switch (mistakes) {
                    case 0: sb.append(""); break;
                    case 1: sb.append("  O"); break;
                    case 2: sb.append("  O\n /|"); break;
                    case 3: sb.append("  O\n /|\\"); break;
                    case 4: sb.append("  O\n /|\\\n / \\"); break;
                }
                break;
        }

        return sb.toString();
    }
        /*public String getVisual(int mistakes) {
            sb.setLength(0);  // clear
            sb.append("Mistakes: ").append(mistakes).append("\n");

            // using STRINGBUILDER here to build the hangman visual based on the number of mistakes (6 mistakes = full hangman, 0 mistakes = empty)
            switch (mistakes) {
                case 6: sb.append("  O\n  | \n /|\\\n / \\").append("\n"); break;
                case 5: sb.append("  O\n  | \n /|\\\n /  ").append("\n"); break;
                case 4: sb.append("  O\n  | \n /|\\").append("\n"); break;
                case 3: sb.append("  O\n  | \n /| ").append("\n"); break;
                case 2: sb.append("  O\n  | ").append("\n"); break;
                case 1: sb.append("  O").append("\n"); break;
                default: sb.append("").append("\n"); break;
            }
            return sb.toString();
        }*/
    }

    // COLLECTIONS (ArrayList)
    private ArrayList<String> words = new ArrayList<>();  
    private String randomWord;
    private char[] wordArr;
    private char[] guessArr;
    private int chances;
    private Difficulty difficulty;
    private HangmanVisual visual = new HangmanVisual();

    // GENERIC METHOD
    public <T> void printItem(T item) {
        System.out.println(item);
    }

    // OVERRIDING OBJECT METHODS
    @Override
    public String toString() {
        // TIME TYPES to show when the game was started
        return "Hangman Game @ " + LocalDateTime.now();  
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof GameLogic);
    }

    @Override
    public GameLogic clone() {
        try {
            return (GameLogic) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // ---------------- START GAME ----------------
    public void startGame(Difficulty diff) {
        this.difficulty = diff;

        // using toString() to print the game start message with the current date and time
        System.out.println("------------------------------------------------");
        System.out.println("Game Started: " + this.toString()); 

        // choose chances based on ENUM + MATH FUNCTIONS
        chances = (difficulty == Difficulty.EASY) ? 8 :
                  (difficulty == Difficulty.MEDIUM) ? 6 : 4;

        System.out.println("Difficulty: " + difficulty);
        System.out.println("Chances: " + chances);
        System.out.println("------------------------------------------------");

        if (!loadWordsFromFile()) return;

        // commenting this.. (this was to let users add more words to words.txt before beginning the game)
        // allowUserToAddWords();   

        selectRandomWord();
        setupGuessingArray();

        Scanner sc = new Scanner(System.in);

        int mistakes = 0;

        while (mistakes < chances) {

            printProgress();
            // using INNER CLASS here to show hangman visual based on mistakes
            System.out.println(visual.getVisual(mistakes, difficulty)); 

            System.out.print("Enter a letter: ");
            char guess = sc.next().toLowerCase().charAt(0);

            try {
                if (!Character.isLetter(guess)) {
                    throw new InvalidGuessException("Invalid input. Letter only!");
                }

                boolean correct = checkGuess(guess);

                if (correct) {
                    System.out.println("Correct guess!");
                    if (Arrays.equals(wordArr, guessArr)) {     
                        // ARRAY FUNCTIONS
                        System.out.println("------------------------------------------------");
                        System.out.println("YAAYYY!! YOU WON! Word was: " + randomWord.toUpperCase());
                        System.out.println("------------------------------------------------");
                        return;
                    }
                } else {
                    mistakes++;
                    // using System.err to print error message for wrong guess
                    System.err.println("Wrong guess!");        
                }

            } catch (InvalidGuessException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println(visual.getVisual(chances, difficulty));
        System.out.println("------------------------------------------------");
        System.out.println("Uhh Ohhhh!! You lost. The word was: " + randomWord.toUpperCase());
        System.out.println("------------------------------------------------");
    }

    // ---------------- FILE HANDLING + split() ----------------

    private boolean loadWordsFromFile() {
        try {
            // using PATH CLASS here to get the absolute path of the file, and also to check if the file exists before reading
            Path path = Paths.get("java-training/src/com/advanced/words.txt");
            System.out.println("Loading words from: " + path.toAbsolutePath());

            BufferedReader br = new BufferedReader(new FileReader("java-training/src/com/advanced/words.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
            br.close();

            if (words.isEmpty()) {
                System.err.println("words.txt is empty!");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error reading words file.");
            return false;
        }

        return true;
    }

    // USE split() to allow user to enter words as well, separated by commas, and add them to the words list
    // private void allowUserToAddWords() {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.print("Enter extra words separated by commas (or press enter): ");
    //     String input = sc.nextLine().trim();

    //     if (!input.isEmpty()) {
    //         // STRING split()
    //         String[] extra = input.split(",");   
    //         for (String w : extra) words.add(w.trim());
    //     }
    // }

    private void selectRandomWord() {
        Random rand = new Random();
        randomWord = words.get(rand.nextInt(words.size())).toLowerCase();
        wordArr = randomWord.toCharArray();
    }

    private void setupGuessingArray() {
        guessArr = new char[wordArr.length];
        // ARRAY FUNCTIONS
        Arrays.fill(guessArr, '_');     
    }

    private void printProgress() {
        System.out.print("Word: ");
        for (char c : guessArr) System.out.print(c + " ");
        System.out.println();
    }

    private boolean checkGuess(char guess) {
        boolean correct = false;

        for (int i = 0; i < wordArr.length; i++) {
            if (wordArr[i] == guess) {
                guessArr[i] = guess;
                correct = true;
            }
        }

        return correct;
    }
}

