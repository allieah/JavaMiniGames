package game2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Game;

public class Game2 implements Game {
    private JPanel gamePanel;
    private JButton backButton; // Add Back button

    public Game2() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout()); // Use BorderLayout to add Back button

        // Add placeholder label for Game 2
        gamePanel.add(new JLabel("Game 2", SwingConstants.CENTER), BorderLayout.CENTER);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((CardLayout) gamePanel.getParent().getLayout()).show(gamePanel.getParent(), "MainMenu");
        }
    }

    @Override
    public JPanel getGamePanel() {
        return gamePanel;
    }
}
