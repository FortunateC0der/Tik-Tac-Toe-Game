package com.mycompany.tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[][] buttons; // 2D array to hold the buttons in the Tic-Tac-Toe grid
    private char currentPlayer; // Variable to track the current player (either 'X' or 'O')

    public TicTacToe() {
        setTitle("Tic Tac Toe Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3]; // Initialize the 2D array for buttons
        currentPlayer = 'X'; // Start with player 'X'

        initializeButtons(); // Set up the GUI by initializing buttons
    }

    private void initializeButtons() {
        // Loop through rows and columns to create buttons and add them to the JFrame
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        // Constructor to store the row and column of the button being clicked
        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if the button is empty and process the move
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setEnabled(false);

                // Check for a win, draw, or switch to the next player
                if (checkWin()) {
                    JOptionPane.showMessageDialog(TicTacToe.this, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(TicTacToe.this, "It's a draw!");
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Row win
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Column win
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false; // No win
    }

    private boolean isBoardFull() {
        // Check if the board is full (draw)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // There is an empty cell, the board is not full
                }
            }
        }
        return true; // Board is full
    }

    private void resetGame() {
        // Reset the game by clearing the buttons and enabling them
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayer = 'X'; // Reset to player 'X'
    }

    public static void main(String[] args) {
        // Create and display the TicTacToeGUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            TicTacToe xoxo = new TicTacToe();
            xoxo.setVisible(true);
        });
    }
}
