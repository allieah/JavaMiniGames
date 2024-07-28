import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TennisGame extends JPanel implements ActionListener, KeyListener {
    private int ballX, ballY, ballDirectionX, ballDirectionY;
    private int paddle1Y, paddle2Y;
    private int score1, score2;
    private boolean gameRunning;

    public TennisGame() {
        // Set up the game window
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        addKeyListener(this);

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
        Timer timer = new Timer(-10, this); // Adjusted timer delay for faster speed
        timer.start();
    }

    public void paintComponent(Graphics g) {
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
            Font emojiFont = new Font("Segoe UI Emoji", Font.BOLD, 50);
            g.setFont(emojiFont);
            if (score1 > score2)
                g.drawString("Player 1 wins! 🏆", 300, 300);
            else if (score1 == score2)
                g.drawString("It's a Tie! 🤝", 300, 300);
            else
                g.drawString("Player 2 wins! 🏆", 300, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
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

            // Move the paddles
            if (paddle1Y < 0)
                paddle1Y = 0;
            if (paddle1Y > 500)
                paddle1Y = 500;
            if (paddle2Y < 0)
                paddle2Y = 0;
            if (paddle2Y > 500)
                paddle2Y = 500;

            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            paddle1Y -= 20;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            paddle1Y += 20;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            paddle2Y -= 20;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2Y += 20;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tennis Game");
        TennisGame game = new TennisGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
