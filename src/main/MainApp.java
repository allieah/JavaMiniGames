package main;

import javax.swing.*;
import java.awt.*;
import game1.Game1;
import game2.Game2;
import game3.HangmanGame;
import game4.TennisGame;
import game5.SudokuGame;

public class MainApp {
    private JFrame frame;
    private JPanel mainMenu;
    private JPanel gamePanelContainer;

    public MainApp() {
        try {
            // Set the FlatLaf look and feel
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Mini Games App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Increased size to accommodate larger game panels

        gamePanelContainer = new JPanel(new CardLayout()); // Use CardLayout for switching

        mainMenu = createMainMenu();
        gamePanelContainer.add(mainMenu, "MainMenu");

        frame.add(gamePanelContainer);
        frame.setVisible(true);
    }

    private JPanel createMainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        JButton game1Button = createButton("Game 1");
        JButton game2Button = createButton("Game 2");
        JButton hangmanButton = createButton("Hangman");
        JButton tennisButton = createButton("Tennis");
        JButton sudokuButton = createButton("Sudoku"); 

        // Configure GridBagConstraints for buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5; // 50% of the width
        panel.add(game1Button, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(game2Button, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(hangmanButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(tennisButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(sudokuButton, gbc); 

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);git
        button.setPreferredSize(new Dimension(200, 50)); // Set button size
        button.setFont(new Font("Arial", Font.PLAIN, 16)); // Set button font
        button.addActionListener(e -> {
            switch (text) {
                case "Game 1":
                    startGame(new Game1());
                    break;
                case "Game 2":
                    startGame(new Game2());
                    break;
                case "Hangman":
                    startGame(new HangmanGame());
                    break;
                case "Tennis":
                    startGame(new TennisGame());
                    break;
                case "Sudoku":
                    startGame(new SudokuGame()); // Start Sudoku game
                    break;
            }
        });
        return button;
    }

    private void startGame(Game game) {
        JPanel gamePanel = game.getGamePanel();
        gamePanelContainer.add(gamePanel, "GamePanel");
        ((CardLayout) gamePanelContainer.getLayout()).show(gamePanelContainer, "GamePanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
