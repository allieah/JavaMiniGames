package game2;
import main.Game;

import javax.swing.JPanel;

import game2.GamePlay; // Import your GamePlay class

public class BrickBreakerGame implements Game {
    private GamePlay gamePlay;

    public BrickBreakerGame() {
        gamePlay = new GamePlay();
    }

    @Override
    public JPanel getGamePanel() {
        return gamePlay; // Return the game panel
    }
}
