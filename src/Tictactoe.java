import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class Tictactoe {
    int boardWidth = 600;
    int boardHeight = 700;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton playAgain = new JButton("Play Again");

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    Tictactoe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);

        playAgain.setBackground(Color.DARK_GRAY);
        playAgain.setForeground(Color.WHITE);
        playAgain.setFont(new Font("Arial", Font.BOLD, 50));
        playAgain.setFocusable(false);
        frame.add(playAgain, BorderLayout.SOUTH);

        playAgain.addActionListener(_ -> {
            gameOver = false;
            turns = 0;
            textLabel.setText("Tic Tac Toe");
            currentPlayer = playerX;

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    board[r][c].setText("");
                }
            }
        });

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(e -> {
                    if (gameOver) return;
                    JButton tile1 = (JButton) e.getSource();

                    if (Objects.equals(tile1.getText(), "")) {
                        tile1.setText(currentPlayer);
                        turns++;
                        checkWinner();

                        if (!gameOver) {
                            currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                            textLabel.setText(currentPlayer + "'s turn");
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (Objects.equals(board[r][0].getText(), "")) continue;

            if (Objects.equals(board[r][0].getText(), board[r][1].getText()) &&
                    Objects.equals(board[r][1].getText(), board[r][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (Objects.equals(board[0][c].getText(), "")) continue;

            if (Objects.equals(board[0][c].getText(), board[1][c].getText()) &&
                    Objects.equals(board[1][c].getText(), board[2][c].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        if (Objects.equals(board[0][0].getText(), board[1][1].getText()) &&
                Objects.equals(board[1][1].getText(), board[2][2].getText()) &&
                !Objects.equals(board[0][0].getText(), "")) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
        }

        if (Objects.equals(board[0][2].getText(), board[1][1].getText()) &&
                Objects.equals(board[1][1].getText(), board[2][0].getText()) &&
                !Objects.equals(board[0][2].getText(), "")) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.GRAY);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.YELLOW);
        tile.setBackground(Color.GRAY);
        textLabel.setText("It's a tie!");
    }
}
