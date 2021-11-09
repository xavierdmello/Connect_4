package connect_4;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AI
{
    // Ask for choice and place disc in board
    // Returns updated board
    public static String[][] placeDisc(String[][] board, String turn, int discsNeededToWin)
    {
        String[][] testBoard = board.clone();
        int bestMove[] = {0, 0, 0}; // Row, Column with best move, score of that move
        int placeDiscAtRow = 0;
        int placeDiscInColumn = 0;

        for (int columnToCheck = 1; columnToCheck < board[0].length + 1; columnToCheck++) {
            // Extra loop to repeat code if user tries to place disc on full column
            while (true) {

                // Get user column choice
                while (true) {
                    try {
                        System.out.println(turn + ": Choose column to place disc in: ");
                        placeDiscInColumn =  columnToCheck - 1;

                        if (placeDiscInColumn > board[0].length - 1 || placeDiscInColumn < 0) {
                            System.out.println("Invalid Input.");
                        } else {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input.");
                    }
                }

                // Find what row in column to place chip at (making disc "fall to bottom")
                for (int row = 0; row < board.length; row++) {
                    if (!board[row][placeDiscInColumn].equals(" ")) {
                        placeDiscAtRow = row + 1;
                    }
                }

                // Check if placing disk here would overflow the column and cause the piece to fall on the floor
                if (placeDiscAtRow > 5) {
                    System.out.println("Invalid Input.");
                } else {
                    board[placeDiscAtRow][placeDiscInColumn] = turn;
                    break;
                }
            }

            // See what the score is if you would put the disc in this column
            testBoard[placeDiscAtRow][placeDiscInColumn] = turn;
            int highestScoreInBoard = calculateHighestScoreInBoard(testBoard, turn, discsNeededToWin);

            if (highestScoreInBoard > bestMove[2]) {
                bestMove[0] = placeDiscAtRow;
                bestMove[1] = placeDiscInColumn;
                bestMove[2] = highestScoreInBoard;
            }

        }

        board[bestMove[0]][bestMove[1]] = turn;
        return board;
    } // end placeDisc()

    public static String[][] makeAiMove(String[][] board, String turn, int discsNeededToWin)
    {
        String[][] testBoard = board.clone();
        int bestMove[] = {0, 0, 0}; // Row, Column with best move, score of that move

        // Check each column's score
        for (int placeDiscInColumn = 0; placeDiscInColumn < board[0].length; placeDiscInColumn++) {
            int placeDiscAtRow = -1;

            // Find what row in column to place chip at (making disc "fall to bottom")
            for (int row = 0; row < board.length; row++) {
                if (!board[row][placeDiscInColumn].equals(" ")) {
                    placeDiscAtRow = row + 1;
                }
            }

            // If disc would overflow column, just move to the next one
            if (placeDiscAtRow > 5) {
                continue;
            } else {


            }
        }

        board[bestMove[0]][bestMove[1]] = turn;
        return board;
    }

    // Finds the score of X or O's discs on the board.
    // "Score" is the highest amount of discs in a row on the board.
    // The highest score is used by the AI to find the best move.
    // Returns int bestScore.
    public static int calculateHighestScoreInBoard(String[][] testBoard, String turn, int discsNeededToWin)
    {
        // Init variables
        int bestScore = 0;
        String playerToCheck = turn;

        // Go through each and every cell in the board
        for (int row = 0; row < testBoard.length; row++ ) {
            for (int column = 0; column < testBoard[row].length; column++) {

                // Modification of winner checking algorithm from Connect_4 class
                // TODO: Update this comment with correct class if I refactor it later

                // Check the highest score of each horizontal run
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row][column + j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestScore) {
                        bestScore = discsInARowSoFar;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

                // Check the highest score of each vertical run
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row + j][column].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestScore) {
                        bestScore = discsInARowSoFar;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

                // Check the highest score of each left diagonal run
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row + j][column + j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestScore) {
                        bestScore = discsInARowSoFar;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

                // Check the highest score of each right diagonal run
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row - j][column - j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestScore) {
                        bestScore = discsInARowSoFar;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            } // end checks on respective columns in rows of board
        } // end checks on rows of board

        return bestScore;
    }
} // end class AI
