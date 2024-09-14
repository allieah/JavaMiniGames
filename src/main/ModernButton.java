package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {
    public ModernButton(String text, String iconPath) {
        super(text);
        setIcon(new ImageIcon(iconPath));
        setPreferredSize(new Dimension(200, 50));
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setBackground(new Color(51, 153, 255));
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder());

        // Set a rounded border
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // Add custom rendering for rounded corners
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        // Add hover effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(0, 102, 204));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(51, 153, 255));
            }
        });

        // Add a smooth transition effect
        putClientProperty("JButton.buttonType", "round");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border painting
    }
}
