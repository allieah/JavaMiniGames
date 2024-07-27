// src/game2/java/game2/Game2.java
package game2;

import javax.swing.*;
import main.Game;

public class Game2 implements Game {
    private JPanel gamePanel;

    public Game2() {
        gamePanel = new JPanel();
        gamePanel.add(new JLabel("Game 2"));
        // Add game logic and components here
    }

    @Override
    public JPanel getGamePanel() {
        return gamePanel;
    }
}
