package game2;

import main.Game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatLightLaf;

import game2.GamePlay; // Import your GamePlay class

public class BrickBreakerGame implements Game {
    private GamePlay gamePlay;
    private JButton backButton; // Add Back button
    private JPanel gamePanel;

    static {
        FlatLightLaf.setup(); // Initialize FlatLaf look and feel
    }

    public BrickBreakerGame() {
        gamePlay = new GamePlay();
        initializeGamePanel();
    }

    private void initializeGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout()); // Use BorderLayout to add Back button

        // Add the GamePlay panel to the gamePanel
        gamePanel.add(gamePlay, BorderLayout.CENTER);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        gamePanel.add(backButton, BorderLayout.SOUTH); // Add Back button to the bottom of the game panel
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Assuming getParent() returns a container with CardLayout
            CardLayout cardLayout = (CardLayout) gamePanel.getParent().getLayout();
            cardLayout.show(gamePanel.getParent(), "MainMenu");
        }
    }

    @Override
    public JPanel getGamePanel() {
        return gamePanel; // Return the game panel
    }
}
