package game4;

import main.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TennisGame extends JPanel implements Game {
    private int ballX, ballY, ballDirectionX, ballDirectionY;
    private int paddle1Y, paddle2Y;
    private int score1, score2;
    private boolean gameRunning;
    private JButton backButton; // Add Back button

    public TennisGame() {
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        setLayout(new BorderLayout()); // Use BorderLayout to position the Back button

        // Initialize game variables
        ballX = 400;
        ballY = 300;
        ballDirectionX = -1;
        ballDirectionY = 1;
        paddle1Y = 250;
        paddle2Y = 250;
        score1 = 0;
        score2 = 0;
        gameRunning = true;

        // Create a timer to update the game
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameRunning) {
                    updateGame();
                }
            }
        });
        timer.start();

        // Key Bindings
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "movePaddle1Up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "movePaddle1Down");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "movePaddle2Up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "movePaddle2Down");

        actionMap.put("movePaddle1Up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1Y -= 20;
                if (paddle1Y < 0) paddle1Y = 0;
                repaint();
            }
        });
        actionMap.put("movePaddle1Down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1Y += 20;
                if (paddle1Y > getHeight() - 100) paddle1Y = getHeight() - 100;
                repaint();
            }
        });
        actionMap.put("movePaddle2Up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle2Y -= 20;
                if (paddle2Y < 0) paddle2Y = 0;
                repaint();
            }
        });
        actionMap.put("movePaddle2Down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle2Y += 20;
                if (paddle2Y > getHeight() - 100) paddle2Y = getHeight() - 100;
                repaint();
            }
        });

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateGame() {
        // Update the ball's position
        ballX += ballDirectionX;
        ballY += ballDirectionY;

        // Check for collisions with paddles and walls
        if (ballX <= 60 && ballY >= paddle1Y && ballY <= paddle1Y + 100) {
            ballDirectionX = 1;
            score1++;
        } else if (ballX >= 720 && ballY >= paddle2Y && ballY <= paddle2Y + 100) {
            ballDirectionX = -1;
            score2++;
        } else if (ballX <= 0 || ballX >= 780) {
            ballDirectionX *= -1;
        }

        if (ballY <= 0 || ballY >= 580) {
            ballDirectionY *= -1;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the tennis court lines
        g.setColor(Color.decode("#15542f"));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        for (int i = 0; i < getHeight(); i += 50) {
            g.fillRect(getWidth() / 2 - 5, i, 10, 40);
        }

        // Draw paddles, ball, and scores
        g.setColor(Color.WHITE);
        g.fillRect(50, paddle1Y, 10, 100);
        g.fillRect(740, paddle2Y, 10, 100);
        g.fillOval(ballX, ballY, 20, 20);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Player 1: " + score1, 100, 50);
        g.drawString("Player 2: " + score2, 550, 50);

        // Check for a winner
        if (score1 >= 5 || score2 >= 5) {
            gameRunning = false;
            g.setFont(new Font("Arial", Font.BOLD, 50));
            if (score1 > score2)
                g.drawString("Player 1 wins!", 300, 300);
            else if (score1 == score2)
                g.drawString("It's a Tie!", 300, 300);
            else
                g.drawString("Player 2 wins!", 300, 300);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Switch to MainMenu panel
            ((CardLayout) getParent().getLayout()).show(getParent(), "MainMenu");
        }
    }

    @Override
    public JPanel getGamePanel() {
        return this;
    }
}
