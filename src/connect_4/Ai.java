package connect_4;

import java.util.Arrays;

public class Ai
{
    public static String[][] makeMove(String[][] board, String turn, int discsNeededToWin)
    {
        int bestmove[] = checkForBestMove(board, turn, discsNeededToWin);


        return board;
    }
    public static int[] checkForBestMove(String[][] board, String turn, int discsNeededToWin)
    {
        int bestMove[] = {0, 0}; // Column with best move, score of that move
        int highestScoreOfIteration[] = {0,0};
        String[][] testBoard = board.clone();
        int placeDiscAtRow = 999;

        // Check each column's score
        for (int placeDiscInColumn = 0; placeDiscInColumn < board[0].length; placeDiscInColumn++) {

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

                // See what the score is if you would put the disc in this column
                testBoard[placeDiscAtRow][placeDiscInColumn] = turn;
                highestScoreOfIteration = calculateHighestScore(testBoard, turn, discsNeededToWin);

                if (highestScoreOfIteration[1] > bestMove[1]) {
                    bestMove[0] = highestScoreOfIteration[0];
                    bestMove[1] = highestScoreOfIteration[0];
                }
            }
        }

        return bestMove;
    } // end makeAiMove()

    public static int[] calculateHighestScore(String[][] board, String turn, int discsNeededToWin)
    {
        // Go through each cell on the Connect 4 board
        int bestMove[] = {0, 0}; // Column, score of that move
        String playerToCheck = turn;

        for (int row = 0; row < board.length; row++ ) {
            for (int column = 0; column < board[row].length; column++) {

                // Check for horizontal, vertical, left diagonal, and right diagonal 4-in-a-rows (respectively)
                // This algo is probably super slow, but hey, it works and it's mine :)

                try {
                    // Check horizontals for wins
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (board[row][column + j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestMove[1]) {
                        bestMove[0] = column;
                        bestMove[1] = discsInARowSoFar;
                    }

                    // Check verticals for wins
                    discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (board[row + j][column].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestMove[1]) {
                        bestMove[0] = column;
                        bestMove[1] = discsInARowSoFar;
                    }


                    // Check left diagonals for wins
                    discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (board[row + j][column + j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestMove[1]) {
                        bestMove[0] = column;
                        bestMove[1] = discsInARowSoFar;
                    }


                    // Check right diagonals for wins
                    discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (board[row - j][column - j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar > bestMove[1]) {
                        bestMove[0] = column;
                        bestMove[1] = discsInARowSoFar;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue; // LOLOL
                } // end try/catch
            } // end checks on respective columns in rows of board
        } // end checks on rows of board

        return bestMove;
    }
} // end class Ai
