package application;

import ui.UserInput;
import ui.UserOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    UserOutput userOutput;
    UserInput userInput;
    FileUtilities fileUtilities;

    public Hangman() {
        userInput = new UserInput();
        userOutput = new UserOutput();
        fileUtilities = new FileUtilities();
    }

    public void run() {

        userOutput.displayWelcome();

        // 1. From the FileUtilities class, get a random word to use as the
        // secret word to guess.

        String secretWord = fileUtilities.readRandomLineFromFile();


        // 2. Create a char array (or String) containing underscores for each
        // letter in the secret word. For example,
        // Secret word ...: giving (6 letters)
        // current guess .: ______ (6 underscores)

        char[] underscoreArr = new char[secretWord.length()];

        System.out.print("Secret word: ");

        for (int i = 0; i < underscoreArr.length; i++) {
            underscoreArr[i] = '_';
            System.out.print(underscoreArr[i]);
        }
        // 3. Create a variable (maybe a final variable?) to hold the
        // maximum number of guesses before losing
        final int MAX_GUESSES = 10;

        // 4. Create a variable to keep track of the number of failed guesses
        int failedGuesses = 0;
        Scanner userInput = new Scanner(System.in);
        List<String> guessedCharacters = new ArrayList<>();

        // 5. While the user hasn't reached the max number of failed guesses,
        //   * Print out the current letters the user has guessed. For example,
        //       gi_i__  (secret word: giving)
        //   * Ask the user for a letter. (Optional: check for a valid letter)
        //   * Check if the letter is in the secret word
        //   * If the letter is in the secret word, replace the '_' with the letter
        //   * If the letter is NOT in the secret word, increase the variable that
        //     keeps track of the failed guesses by one.
        //   * If the users guess matches the secret word, print a congratulations
        //     message.
        //   * If the user has reached the maximum number of guesses without solving
        //     the secret word, print a message saying: try again.

        while (failedGuesses < MAX_GUESSES) {
            if(Arrays.toString(underscoreArr)
                    .replace(", ", "")
                    .replace("[", "")
                    .replace("]", "")
                    .equals(secretWord)) {
                System.out.println("Congratulations! you guessed the secret word!");
                break;
            }

            System.out.println("\n Please guess a letter, or the secret word: ");
            String userGuess = userInput.nextLine().toLowerCase();

            if (secretWord.contentEquals(userGuess)) {
                System.out.println("Congratulations! you guessed the secret word!");
                break;
            } else if (secretWord.contains(userGuess)) {
                for (int i = 0; i < underscoreArr.length; i++) {
                    if (secretWord.charAt(i) == userGuess.charAt(0)) {
                        underscoreArr[i] = userGuess.charAt(0);
                    }
                }
                System.out.println("\nGood job! " + userGuess + " was in the secret word.\n");


            } else {
                failedGuesses++;
                System.out.println("Sorry, that wasn't in the secret word.");

            }
            guessedCharacters.add(userGuess);
            System.out.println("Remaining guesses: " + (MAX_GUESSES - failedGuesses));
            System.out.println("Letters guessed so far: " + guessedCharacters);
            System.out.println("Secret word: " + Arrays.toString(underscoreArr)
                    .replace(", ", "")
                    .replace("[", "")
                    .replace("]", ""));


            // Challenge: add a theme mode for hangman words
            // Challenge: add a hint option
            // Challenge: create Wordle

        }
        if(failedGuesses >= 10) {
            System.out.println("Sorry, you lose. Try again.");
            System.out.println("The secret word was: " + secretWord);
        }
    }
}
