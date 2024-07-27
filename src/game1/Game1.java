// src/game1/java/game1/Game1.java
package game1;

import javax.swing.*;
import main.Game;

public class Game1 implements Game {
    private JPanel gamePanel;

    public Game1() {
        gamePanel = new JPanel();
        gamePanel.add(new JLabel("Game 1"));
        // Add game logic and components here
    }

    @Override
    public JPanel getGamePanel() {
        return gamePanel;
    }
}
