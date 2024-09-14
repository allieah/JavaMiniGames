package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public WelcomeScreen() {
        setTitle("Welcome to Mini Games App");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);

        JLabel welcomeLabel = new JLabel("Welcome to Mini Games!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        
        // Add a logo or image if you have one
        // ImageIcon logo = new ImageIcon("path/to/logo.png");
        // JLabel logoLabel = new JLabel(logo, JLabel.CENTER);
        // panel.add(logoLabel, BorderLayout.NORTH);

        panel.add(welcomeLabel, BorderLayout.CENTER);
        add(panel);

        // Transition to the MainApp after 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transitionToMainApp();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void transitionToMainApp() {
        dispose(); // Close the welcome screen
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true)); // Show the main app
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeScreen().setVisible(true));
    }
}
