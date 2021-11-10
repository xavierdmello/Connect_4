/*  Name: Xavier D'Mello
 *  Course: ICS 3U
 *  Teacher: Mrs. McCaffery
 *  Date: November 10th, 2021
 *  Description: AI for the Connect 4 class.
 *               The AI has 4 decisions, in this order.:
 *               1. if (AI can win this round): Win.
 *               2. else if (player can win this round): Block player.
 *               3. else if (move places disc close to other discs): Place disc close to other discs.
 *               4. else: Place disc in random column.
 *
 *               The AI LOVES LOVES LOVES iceCream. iceCream is added to each good move.
 *               Better moves will reward the AI with more iceCream.
 *               Using this system, the AI will do the best move - whatever move gives it the most iceCream.
 *
 *               Code to copy 2D array borrowed from Stackoverflow:
 *               Arrays.stream(board).map(String[]::clone).toArray(String[][]::new)
 *               https://stackoverflow.com/questions/23079003/how-to-convert-a-java-8-stream-to-an-array
 *
 *               All other code in this program is my own. I had a lot of fun building this AI!
 */

package connect_4;

import java.util.*;

public class AI
{
    public static String[][] makeAiMove(String[][] board, String turn, int discsNeededToWin)
    {
        // Get what the AI thinks is its best move:
        String[][] bestAIMove = calculateMove(board, turn,discsNeededToWin);

        // Get what the AI thinks is the player's best move:
        String[][] bestPlayerMove;
        if (turn.equals("X")) {
            // If the AI is X, get the Player's move (O)
            bestPlayerMove = calculateMove(board, "O",discsNeededToWin);
        }
        else {
            // If the AI is O, get the Player's move (X)
            bestPlayerMove = calculateMove(board, "X",discsNeededToWin);
        }

        // Check if enemy's best move means that it wins:
        boolean doesEnemyWin = false;
        String willPlayerWin = Connect_4.checkIfWin(bestPlayerMove, discsNeededToWin);
        if (turn.equals("X") && willPlayerWin.equals("O") || turn.equals("O") && willPlayerWin.equals("X")) {
            doesEnemyWin = true;
        }

        // If enemy can win next round, block them:
        // Find where enemy can place their disc to win by iterating through each cell of the "winning" board.
        if (doesEnemyWin == true) {
            // Iterate through each row:
            for (int i = 0; i < board.length; i++) {
                // Iterate through each column:
                for (int j = 0; j < board[0].length; j++) {
                    // If "winning" enemy move is found, then place disc there to block them.
                    if (!board[i][j].equals(bestPlayerMove[i][j])) {
                        bestAIMove = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
                        bestAIMove[i][j] = turn;
                    }
                } // end column iterator
            } // end row iterator
        } // end "Find where enemy can place their disc to win" loop

        // If player move was blocked, return that blocked move. Otherwise, return the calculated move.
        return bestAIMove;
    }

    // Ask for choice and place disc in board
    // Returns updated board
    public static String[][] calculateMove(String[][] board, String turn, int discsNeededToWin)
    {
        String[][] newBoard = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
        Random rn = new Random();
        boolean betterMoveFound = false;

        ArrayList<Board> boards = new ArrayList<Board>();
        for (int columnToCheck = 0; columnToCheck < newBoard[0].length; ++columnToCheck) {
                boards.add(new Board(placeDisc(newBoard,turn,columnToCheck), calculateCookies(placeDisc(newBoard,turn,columnToCheck),turn,discsNeededToWin)));
        }

        ArrayList<Integer> indicesOfHighestScoresSoFar = new ArrayList<Integer>();
        int indexHighestScoreSoFar = 0;
        int highestScoreSoFar = calculateCookies(board, turn, discsNeededToWin);

        for (int i = 0; i < boards.size(); i++) {
            if (boards.get(i).score > highestScoreSoFar) {
                indicesOfHighestScoresSoFar.clear();
                indexHighestScoreSoFar = i;
                indicesOfHighestScoresSoFar.add(i);
                highestScoreSoFar = boards.get(indexHighestScoreSoFar).score;
                betterMoveFound = true;
            } else if (boards.get(i).score == highestScoreSoFar) {
                indicesOfHighestScoresSoFar.add(i);
            }
        }

        // Choose a good move from the list of good moves.
        if (betterMoveFound == true) {
            return boards.get(indicesOfHighestScoresSoFar.get(rn.nextInt(indicesOfHighestScoresSoFar.size()))).board;
        }
        // If there are no good moves, pick a random column to place disc.
        else {
            return placeDisc(board, turn, rn.nextInt(6));
        }
    } // end placeDisc()


    public static String[][] placeDisc(String[][] board, String turn, int placeDiscInColumn)
    {
        String[][] newBoard = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
        int placeDiscAtRow = 0;

        if (placeDiscInColumn > newBoard[0].length - 1 || placeDiscInColumn < 0) {
            throw new IndexOutOfBoundsException();
        }

        for (int row = 0; row < newBoard.length; row++) {
            if (!newBoard[row][placeDiscInColumn].equals(" ")) {
                placeDiscAtRow = row + 1;
            }
        }

        if (placeDiscAtRow > newBoard.length - 1) {
            String[][] tempBoard = Connect_4.emptyBoard(newBoard);
            return tempBoard;
        } else {
            newBoard[placeDiscAtRow][placeDiscInColumn] = turn;
            return newBoard;
        }
    }

    // Calculate how much iceCream to offer AI.
    // The AI will do whatever move gives it the most iceCream.
    // Rules:
    //      - Move will cause AI to immediately win: +999 iceCream
    //      - Move will block human from immediately winning: +750 iceCream
    //      - Disc is adjacent to same colour disc: +1 iceCream for each disc
    public static int calculateCookies(String[][] testBoard, String turn, int discsNeededToWin)
    {
        // Init variables
        int biggestRun = 0;
        int iceCream = 0;
        String playerToCheck = turn;

        // Go through each and every cell in the board
        for (int row = 0; row < testBoard.length; row++ ) {
            for (int column = 0; column < testBoard[row].length; column++) {

                // WARNING: You are about to see shameless abuse of ArrayIndexOutOfBoundsException.
                //          If you are easily frightened, please collapse the next section of code and move on

                //                          Hey, it works...


                // Modification of winner checking algorithm from Connect_4 class
                // TODO: Update this comment with correct class if I refactor it later

                // Check horizontals for wins
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row][column + j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar == discsNeededToWin) {
                        // If somebody wins, print the final state of the board, then return the winner
                        iceCream = iceCream + 999;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}

                // Check verticals for wins
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row + j][column].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar == discsNeededToWin) {
                        // If somebody wins, print the final state of the board, then return the winner
                        iceCream = iceCream + 999;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}

                // Check left diagonals for wins
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row + j][column + j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar == discsNeededToWin) {
                        // If somebody wins, print the final state of the board, then return the winner
                        iceCream = iceCream + 999;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}

                // Check right diagonals for wins
                try {
                    int discsInARowSoFar = 0;
                    for (int j = 0; j < discsNeededToWin; j++) {
                        if (testBoard[row + j][column - j].equals(playerToCheck)) {
                            discsInARowSoFar++;
                        }
                    }
                    if (discsInARowSoFar == discsNeededToWin) {
                        // TODO: update commenting in this area
                        // If somebody wins, print the final state of the board, then return the winner
                        iceCream = iceCream + 999;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}

                // Give +1 iceCream for each chip every chip is adjacent to
                if (testBoard[row][column].equals(playerToCheck)) {
                    // If disc above
                    try {
                        if (testBoard[row + 1][column].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to top right
                    try {
                        if (testBoard[row  + 1][column + 1].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to right
                    try {
                        if (testBoard[row][column + 1].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to bottom right
                    try {
                        if (testBoard[row - 1][column + 1].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc below
                    try {
                        if (testBoard[row - 1][column].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to bottom right
                    try {
                        if (testBoard[row - 1][column - 1].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to left
                    try {
                        if (testBoard[row][column - 1].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to top left
                    try {
                        if (testBoard[row + 1][column - 1].equals(playerToCheck)) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                } // end adjacent disc iceCream reward if statement
            } // end checks on respective columns in rows of board
        } // end checks on rows of board

        return iceCream;
    }
} // end class AI
