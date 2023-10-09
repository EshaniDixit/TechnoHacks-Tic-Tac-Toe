/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoegui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel resultLabel;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        JPanel panel = new JPanel(new GridLayout(3, 3));
        resultLabel = new JLabel("Current Player: " + currentPlayer);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                panel.add(buttons[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][0].getText().equals(buttons[i][2].getText()) && !buttons[i][0].getText().isEmpty()) {
                announceWinner(buttons[i][0].getText());
                return;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[0][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].getText().isEmpty()) {
                announceWinner(buttons[0][i].getText());
                return;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[0][0].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().isEmpty()) {
            announceWinner(buttons[0][0].getText());
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[0][2].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().isEmpty()) {
            announceWinner(buttons[0][2].getText());
            return;
        }

        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            announceDraw();
        }
    }

    private void announceWinner(String winner) {
        resultLabel.setText("Player " + winner + " wins!");
        disableButtons();
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
    }

    private void announceDraw() {
        resultLabel.setText("It's a draw!");
        JOptionPane.showMessageDialog(this, "It's a draw!");
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().isEmpty()) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                if (currentPlayer == 'X') {
                    buttons[row][col].setBackground(Color.GREEN); // Change background color to green for X
                    buttons[row][col].setForeground(Color.BLUE);
                } else {
                    buttons[row][col].setBackground(Color.RED);   // Change background color to red for O
                    buttons[row][col].setForeground(Color.WHITE);
                }
                checkWin();
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                resultLabel.setText("Current Player: " + currentPlayer);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
