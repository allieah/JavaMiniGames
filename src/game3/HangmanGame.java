import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        String[] words = { "apple", "banana", "orange", "grape", "strawberry" };
        String wordToGuess = words[(int) (Math.random() * words.length)];

        int maxAttempts = 6;
        int attemptsLeft = maxAttempts;
        StringBuilder guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));

        Scanner scanner = new Scanner(System.in);

        while (attemptsLeft > 0 && guessedWord.indexOf("_") != -1) {
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.println("Word: " + guessedWord);
            System.out.print("Guess a letter: ");
            char guess = scanner.next().charAt(0);

            boolean found = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guess) {
                    guessedWord.setCharAt(i, guess);
                    found = true;
                }
            }

            if (!found) {
                attemptsLeft--;
                System.out.println("Incorrect guess!");
                displayHangman(maxAttempts - attemptsLeft);
            }
        }

        if (guessedWord.indexOf("_") == -1) {
            System.out.println("Congratulations! You guessed the word: " + guessedWord);
        } else {
            System.out.println("Sorry, you're out of attempts. The word was: " + wordToGuess);
        }

        scanner.close();
    }

    private static void displayHangman(int incorrectGuesses) {
        String[] hangmanArt = {
                "  +---+\n  |   |\n      |\n      |\n      |\n      |",
                "  +---+\n  |   |\n  O   |\n      |\n      |\n      |",
                "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |",
                "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |",
                "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |",
                "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |",
                "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |"
        };

        System.out.println(hangmanArt[incorrectGuesses]);
    }
}
