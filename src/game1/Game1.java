package game1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Game;

public class Game1 implements Game {
    private JPanel gamePanel;
    private JButton[][] buttons;
    private char currentPlayer;
    private int moveCount;

    public Game1() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3)); // Set layout for Tic-Tac-Toe grid
        buttons = new JButton[3][3];
        currentPlayer = 'X'; // X always goes first
        moveCount = 0;
        initializeUI();
    }

    private void initializeUI() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                gamePanel.add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("-")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                moveCount++;

                if (checkForWin()) {
                    JOptionPane.showMessageDialog(gamePanel, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (moveCount == 9) {
                    JOptionPane.showMessageDialog(gamePanel, "The game is a tie!");
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
                }
            }
        }
    }

    private boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().charAt(0) == currentPlayer &&
                buttons[i][1].getText().charAt(0) == currentPlayer &&
                buttons[i][2].getText().charAt(0) == currentPlayer) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().charAt(0) == currentPlayer &&
                buttons[1][i].getText().charAt(0) == currentPlayer &&
                buttons[2][i].getText().charAt(0) == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().charAt(0) == currentPlayer &&
            buttons[1][1].getText().charAt(0) == currentPlayer &&
            buttons[2][2].getText().charAt(0) == currentPlayer) {
            return true;
        }
        if (buttons[0][2].getText().charAt(0) == currentPlayer &&
            buttons[1][1].getText().charAt(0) == currentPlayer &&
            buttons[2][0].getText().charAt(0) == currentPlayer) {
            return true;
        }
        return false;
    }

    private void resetGame() {
        moveCount = 0;
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("-");
            }
        }
    }

    @Override
    public JPanel getGamePanel() {
        return gamePanel;
    }
}
