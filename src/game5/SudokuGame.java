package game5;

import main.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import com.formdev.flatlaf.FlatLightLaf;

class SudokuPuzzle {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int EMPTY_CELL = 0;

    public boolean isSafe(int[][] board, int row, int col, int num) {
        for (int d = 0; d < SIZE; d++) {
            if (board[row][d] == num) return false;
        }
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == num) return false;
        }
        int boxRowStart = row - row % SUBGRID_SIZE;
        int boxColStart = col - col % SUBGRID_SIZE;
        for (int r = boxRowStart; r < boxRowStart + SUBGRID_SIZE; r++) {
            for (int d = boxColStart; d < boxColStart + SUBGRID_SIZE; d++) {
                if (board[r][d] == num) return false;
            }
        }
        return true;
    }

    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = EMPTY_CELL;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void generateSudoku(int[][] board) {
        Random rand = new Random();
        solveSudoku(board);
        int cellsToRemove = 40;
        while (cellsToRemove > 0) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            if (board[row][col] != EMPTY_CELL) {
                board[row][col] = EMPTY_CELL;
                cellsToRemove--;
            }
        }
    }
}

public class SudokuGame extends JPanel implements Game {
    private static final int SIZE = 9;
    private static final int EMPTY_CELL = 0;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private int[][] board = new int[SIZE][SIZE];

    public SudokuGame() {
        FlatLightLaf.setup(); // Initialize FlatLaf look and feel

        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SIZE, SIZE));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                boardPanel.add(cells[row][col]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate New");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateNewBoard();
            }
        });

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveBoard();
                JOptionPane.showMessageDialog(null, "Puzzle Solved! Congratulations!");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming the parent container uses CardLayout
                Container parent = getParent();
                if (parent != null && parent.getLayout() instanceof CardLayout) {
                    ((CardLayout) parent.getLayout()).show(parent, "MainMenu");
                }
            }
        });

        buttonPanel.add(generateButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void generateNewBoard() {
        SudokuPuzzle puzzle = new SudokuPuzzle();
        puzzle.generateSudoku(board);
        displayBoard();
    }

    private void solveBoard() {
        SudokuPuzzle puzzle = new SudokuPuzzle();
        puzzle.solveSudoku(board);
        displayBoard();
    }

    private void displayBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    cells[row][col].setText("");
                    cells[row][col].setEditable(true);
                } else {
                    cells[row][col].setText(String.valueOf(board[row][col]));
                    cells[row][col].setEditable(false);
                }
            }
        }
    }

    @Override
    public JPanel getGamePanel() {
        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sudoku Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new CardLayout()); // Assuming CardLayout is used for multiple views
            frame.add(new SudokuGame(), "SudokuGame");
            // Add other views here if needed, e.g., "MainMenu"
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
