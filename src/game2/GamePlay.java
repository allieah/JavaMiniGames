package game2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = true;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int magentaXdir = -1;
    private int magentaRectX = 300;
    private int currentLevel = 1;
    private static final int numLevels = 4;
    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        requestFocusInWindow();
    }

    void nextLevel() {
        if (currentLevel < numLevels) {
            play = false;
            currentLevel++;
            loadLevel(currentLevel);
            repaint();
        }
    }
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();  // Add this line to ensure focus is requested when the panel is added to the container
    }
    
    public void paint(Graphics g) {
        // background color
        g.setColor(Color.CYAN);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        g.setColor(Color.blue);
        g.fillRect(playerX, 550, 100, 12);
        g.setColor(Color.RED); // ball color
        g.fillOval(ballposX, ballposY, 20, 20);
        g.setColor(Color.MAGENTA);
        g.fillRect(magentaRectX, 0, 80, 12);
        g.setColor(Color.black);
        g.setFont(new Font("MV Boli", Font.BOLD, 25));
        g.drawString("Score: " + score, 50, 70);

        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(new Color(0XFF6464));
            g.setFont(new Font("MV Boli", Font.BOLD, 30));
            g.drawString("You Won, Score: " + score, 190, 300);
            g.setFont(new Font("MV Boli", Font.BOLD, 20));
            g.drawString("Press Enter to Restart.", 230, 350);
        }

        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.BLACK);
            g.setFont(new Font("MV Boli", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);
            g.setFont(new Font("MV Boli", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        g.dispose();
        map.draw((Graphics2D) g);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        timer.start();
        if (play) {
            // Ball - Pedal interaction
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            // ball- special brick interaction
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(magentaRectX, 0, 80, 12))) {
                score += 50;
                ballYdir = -ballYdir;
            }
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)) {
                            if (i == MapGenerator.row1 && j == MapGenerator.col1) {
                                score -= 2;
                            } else {
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score += 5;
                            }
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)
                                ballXdir = -ballXdir;
                            else {
                                ballYdir = -ballYdir;
                            }
                        }
                    }
                }
            }

            int stoneX = map.stoneWidth + 100;
            int stoneY = map.stoneHeight + 300;
            int stoneA = map.stoneWidth + 400;
            int stoneWidth = map.stoneWidth;
            int stoneHeight = map.stoneHeight;
            Rectangle r = new Rectangle(stoneX, stoneY, stoneWidth, stoneHeight);
            Rectangle s = new Rectangle(stoneA, stoneY, stoneWidth, stoneHeight);
            Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
            Rectangle stoneRect = r;
            Rectangle stone1Rect = s;
            if (ballRect.intersects(stoneRect)) {
                score -= 5;
                if (ballposX + 19 <= stoneRect.x || ballposX + 1 >= stoneRect.x + stoneRect.width)
                    ballXdir = -ballXdir;
                else {
                    ballYdir = -ballYdir;
                }
            }
            if (ballRect.intersects(stone1Rect)) {
                score -= 5;
                if (ballposX + 19 <= stone1Rect.x || ballposX + 1 >= stone1Rect.x + stone1Rect.width)
                    ballXdir = -ballXdir;
                else {
                    ballYdir = -ballYdir;
                }
            }

            magentaRectX += magentaXdir;
            if (magentaRectX < 0) {
                magentaXdir = -magentaXdir;
            }

            if (magentaRectX > 610) {
                magentaXdir = -magentaXdir;
            }

            ballposX += ballXdir;
            ballposY += ballYdir;

            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }

        repaint();

        if (totalBricks <= 1) {
            if (currentLevel < numLevels) {
                play = false;
                currentLevel++;
                loadLevel(currentLevel);
                repaint();
            } else {
                play = false;
                ballXdir = 0;
                ballYdir = 0;
                repaint();
            }
        }

        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            repaint();
        }
    }

    private void loadLevel(int level) {
        if (level == 1) {
            map = new MapGenerator(3, 7);
            delay = 8;
            ballXdir = -1;
            ballYdir = -2;
        } else if (level == 2) {
            map = new MapGenerator(4, 6);
            delay = 6;
            ballXdir = -2;
            ballYdir = -3;
        } else if (level == 3) {
            map = new MapGenerator(8, 8);
            delay = 4;
            ballXdir = -3;
            ballYdir = -4;
        } else if (level == 4) {
            map = new MapGenerator(3, 9);
            delay = 8;
            ballXdir = -1;
            ballYdir = -1;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: " + e.getKeyCode());  
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
                System.out.println("rightClick");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7);
                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }
}
