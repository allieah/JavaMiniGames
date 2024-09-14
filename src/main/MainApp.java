package main;

import javax.swing.*;
import java.awt.*;
import game1.Game1;
import game2.BrickBreakerGame;
import game3.HangmanGame;
import game4.TennisGame;
import game5.SudokuGame;

public class MainApp extends JFrame {
    private JPanel mainMenu;
    private JPanel gamePanelContainer;

    public MainApp() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");  // FlatLaf is a modern look and feel
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Mini Games App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use background color for the entire app
        getContentPane().setBackground(Color.DARK_GRAY);

        gamePanelContainer = new JPanel(new CardLayout());

        mainMenu = createMainMenu();
        gamePanelContainer.add(mainMenu, "MainMenu");

        add(gamePanelContainer);
    }

    private JPanel createMainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);  // Background color for main menu

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);  // Spacing around buttons

        // Adding game buttons with icons and hover effects
        JButton game1Button = createButton("Tic-Tac-Toe", "icons/tic-tac-toe.png");
        JButton brickBreakerButton = createButton("Brick Breaker", "icons/brick-breaker.png"); 
        JButton hangmanButton = createButton("Hangman", "icons/hangman.png");
        JButton tennisButton = createButton("Tennis", "icons/tennis.png");
        JButton sudokuButton = createButton("Sudoku", "icons/sudoku.png");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        panel.add(game1Button, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(brickBreakerButton, gbc);

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

    private JButton createButton(String text, String iconPath) {
        return new ModernButton(text, iconPath);
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
