package connect_4;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Ai
{
//    public static int[] checkForBestMove(String[][] board, String turn, int discsNeededToWin)
//    {
//        int bestMove[] = {0, 0}; // Column with best move, score of that move
//        int highestScoreOfIteration[] = {0,0};
//        String[][] testBoard = board.clone();
//        int placeDiscAtRow = 999;
//
//        // Check each column's score
//        for (int placeDiscInColumn = 0; placeDiscInColumn < board[0].length; placeDiscInColumn++) {
//
//            // Find what row in column to place chip at (making disc "fall to bottom")
//            for (int row = 0; row < board.length; row++) {
//                if (!board[row][placeDiscInColumn].equals(" ")) {
//                    placeDiscAtRow = row + 1;
//                }
//            }
//
//            // If disc would overflow column, just move to the next one
//            if (placeDiscAtRow > 5) {
//                continue;
//            } else {
//
//                // See what the score is if you would put the disc in this column
//                testBoard[placeDiscAtRow][placeDiscInColumn] = turn;
//                highestScoreOfIteration = calculateHighestScore(testBoard, turn, discsNeededToWin);
//
//                if (highestScoreOfIteration[1] > bestMove[1]) {
//                    bestMove[0] = highestScoreOfIteration[0];
//                    bestMove[1] = highestScoreOfIteration[0];
//                }
//            }
//        }
//
//        return bestMove;
//    } // end makeAiMove()

    public static int calculateHighestScoreInBoard(String[][] testBoard, String turn, int discsNeededToWin)
    {
        int bestScore = 0; // Highest score of current move
        String playerToCheck = turn;

        for (int row = 0; row < testBoard.length; row++ ) {
            for (int column = 0; column < testBoard[row].length; column++) {

                // Check for the highest amount of discs beside each other in horizontals, verticals, and both diagonals.
                // Modification of winner checking algorithm from Connect_4 class
                // TODO: Update this comment with correct class if I refactor it later
                try {
                    // Check horizontals for wins
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

                try {
                    // Check verticals for wins
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

                try {
                    // Check left diagonals for wins
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

                try {
                    // Check right diagonals for wins
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
} // end class Ai
