package main;

import javax.swing.*;
import java.awt.*;
import game1.Game1;
import game2.BrickBreakerGame;
import game3.HangmanGame;
import game4.TennisGame;

public class MainApp {
    private JFrame frame;
    private JPanel mainMenu;
    private JPanel gamePanelContainer;

    public MainApp() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Mini Games App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        gamePanelContainer = new JPanel(new CardLayout());

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
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton game1Button = createButton("Game 1");
        JButton brickBreakerButton = createButton("Brick_Breaker"); // Add Brick Breaker button
        JButton hangmanButton = createButton("Hangman");
        JButton tennisButton = createButton("Tennis");
       

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
        gbc.gridy = 2; // Position for the Brick Breaker button
       

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.addActionListener(e -> {
            switch (text) {
                case "Game 1":
                    startGame(new Game1());
                    break;
                case "Brick_Breaker":
                    startGame(new BrickBreakerGame()); // Start Brick Breaker
                    break;
                case "Hangman":
                    startGame(new HangmanGame());
                    break;
                case "Tennis":
                    startGame(new TennisGame());
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
