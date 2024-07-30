package game3;

import main.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatLightLaf;

public class HangmanGame extends JPanel implements Game {
    private static final String[] WORDS = { "apple", "banana", "orange", "grape", "strawberry" };
    private String wordToGuess;
    private int maxAttempts = 6;
    private int attemptsLeft;
    private StringBuilder guessedWord;
    private JTextArea displayArea;
    private JTextField guessInput;
    private JButton guessButton;
    private JButton backButton; // Add Back button
    private JLabel hangmanLabel;

    public HangmanGame() {
        FlatLightLaf.setup(); // Initialize FlatLaf look and feel

        setLayout(new BorderLayout(10, 10));
        initGame();

        // Display Area
        displayArea = new JTextArea(5, 20);
        displayArea.setEditable(false);
        displayArea.setText("Attempts left: " + attemptsLeft + "\nWord: " + guessedWord);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        displayArea.setBackground(new Color(240, 240, 240));
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Game Status"));
        centerPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        guessInput = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());

        inputPanel.add(new JLabel("Guess a letter: "));
        inputPanel.add(guessInput);
        inputPanel.add(guessButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        inputPanel.add(backButton); // Add Back button to input panel

        add(inputPanel, BorderLayout.SOUTH);

        // Hangman Label
        hangmanLabel = new JLabel("", SwingConstants.CENTER);
        hangmanLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        hangmanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hangmanLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(hangmanLabel, BorderLayout.NORTH);

        updateHangman();
    }

    private void initGame() {
        wordToGuess = WORDS[(int) (Math.random() * WORDS.length)];
        attemptsLeft = maxAttempts;
        guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));
    }

    private void updateHangman() {
        String[] hangmanArt = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |"
        };

        int index = Math.max(0, Math.min(maxAttempts - attemptsLeft, hangmanArt.length - 1));
        hangmanLabel.setText("<html><pre>" + hangmanArt[index] + "</pre></html>");
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guessText = guessInput.getText().toLowerCase();
            if (guessText.isEmpty()) {
                return; // No input, do nothing
            }
            char guess = guessText.charAt(0);

            boolean found = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guess) {
                    guessedWord.setCharAt(i, guess);
                    found = true;
                }
            }

            if (!found) {
                attemptsLeft--;
                updateHangman();
                displayArea.append("\nIncorrect guess!");
            }

            displayArea.setText("Attempts left: " + attemptsLeft + "\nWord: " + guessedWord);

            if (guessedWord.indexOf("_") == -1) {
                displayArea.append("\nCongratulations! You guessed the word: " + guessedWord);
                guessButton.setEnabled(false);
            } else if (attemptsLeft <= 0) {
                displayArea.append("\nSorry, you're out of attempts. The word was: " + wordToGuess);
                guessButton.setEnabled(false);
            }

            guessInput.setText("");
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((CardLayout) getParent().getLayout()).show(getParent(), "MainMenu");
        }
    }

    @Override
    public JPanel getGamePanel() {
        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hangman Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new HangmanGame());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
