
import java.util.Scanner;

public class TicTac {
    public static void main(String[] args) {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        int currentPlayer = 1; // 1 for Player 1 (X), 2 for Player 2 (O)
        boolean gameWon = false;

        Scanner scanner = new Scanner(System.in);

        while (!gameWon) {
            // Display the current state of the board
            printBoard(board);

            // Get the current player's move
            System.out.println("Player " + currentPlayer + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Check if the chosen cell is valid
            if (isValidMove(board, row, col)) {
                // Make the move
                board[row][col] = (currentPlayer == 1) ? 'X' : 'O';

                // Check if the current player wins
                if (checkWin(board)) {
                    gameWon = true;
                    printBoard(board);
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (isBoardFull(board)) {
                    // Check if the board is full (a tie)
                    gameWon = true;
                    printBoard(board);
                    System.out.println("It's a tie!");
                } else {
                    // Switch to the other player
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Function to print the current state of the board
    private static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Function to check if a move is valid
    private static boolean isValidMove(char[][] board, int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ');
    }

    // Function to check if the current player wins
    private static boolean checkWin(char[][] board) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) ||
                (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i])) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
            (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true;
        }

        return false;
    }

    // Function to check if the board is full (a tie)
    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // There is an empty cell, so the board is not full
                }
            }
        }
        return true; // All cells are filled, indicating a tie
    }
}
