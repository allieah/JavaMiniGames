// src/main/java/main/MainApp.java
package main;

import javax.swing.*;
import game1.Game1;
import game2.Game2;

public class MainApp {
    private JFrame frame;

    public MainApp() {
        frame = new JFrame("Mini Games App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainMenu = new JPanel();
        JButton game1Button = new JButton("Game 1");
        JButton game2Button = new JButton("Game 2");
//comment
        game1Button.addActionListener(e -> startGame(new Game1()));
        game2Button.addActionListener(e -> startGame(new Game2()));

        mainMenu.add(game1Button);
        mainMenu.add(game2Button);

        frame.add(mainMenu);
        frame.setVisible(true);
    }

    private void startGame(Game game) {
        frame.getContentPane().removeAll();
        frame.add(game.getGamePanel());
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
