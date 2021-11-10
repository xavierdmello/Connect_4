package connect_4;

import java.util.*;

public class AI
{
    // Ask for choice and place disc in board
    // Returns updated board
    public static String[][] makeAiMove(String[][] board, String turn, int discsNeededToWin)
    {
        Random rn = new Random();
        boolean betterMoveFound = false;

        ArrayList<Board> boards = new ArrayList<Board>();
        for (int columnToCheck = 0; columnToCheck < board[0].length; ++columnToCheck) {
            try {
                boards.add(new Board(placeDisc(board,turn,columnToCheck), calculateCookies(placeDisc(board,turn,columnToCheck),turn,discsNeededToWin)));
            } catch (ArrayIndexOutOfBoundsException e) {
                // If the disc would overflow the board, move on to the next column.
                // I know this is cursed, but hey, it works, is predictable, and is easy to understand.
            }
        }

//        // TODO: Remove following debug code:
//        for (int i = 0; i < boards.size(); i++) {
//            System.out.println("Board #" + i + "; Score: " + boards.get(i).score);
//            Connect_4.printBoard(boards.get(i).board, discsNeededToWin);
//            System.out.println("-----------");
//        }

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

        if (betterMoveFound == true) {
            return boards.get(indicesOfHighestScoresSoFar.get(rn.nextInt(indicesOfHighestScoresSoFar.size()))).board;
        } else {
            System.out.println("No better move found, picking random spot");
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

        if (placeDiscAtRow > 5) {
            throw new IndexOutOfBoundsException();
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
        int biggestEnemyRun = 0;
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

                // Repeat loop twice; Check AI's runs first, then check player's runs
                for (int k = 0; k < 2; k++) {

                    // Check if colour can win immediately with a horizontal run
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (testBoard[row][column + j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                            // If the streak of discs in a row is broken, break loop
                            else {
                                break;
                            }
                        }
                        if (discsInARowSoFar > biggestRun && playerToCheck == turn) {
                            biggestRun = discsInARowSoFar;
                        } else {
                            biggestEnemyRun = discsInARowSoFar;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // Check if colour can win immediately with a vertical run
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (testBoard[row + j][column].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                            // If the streak of discs in a row is broken, break loop
                            else {
                                break;
                            }
                        }
                        if (discsInARowSoFar > biggestRun && playerToCheck == turn) {
                            biggestRun = discsInARowSoFar;
                        } else {
                            biggestEnemyRun = discsInARowSoFar;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // Check if colour can win immediately with a left diagonal run
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (testBoard[row + j][column + j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                            // If the streak of discs in a row is broken, break loop
                            else {
                                break;
                            }
                        }
                        if (discsInARowSoFar > biggestRun && playerToCheck == turn) {
                            biggestRun = discsInARowSoFar;
                        } else {
                            biggestEnemyRun = discsInARowSoFar;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // Check if colour can win immediately with a right diagonal run
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (testBoard[row - j][column - j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                            // If the streak of discs in a row is broken, break loop
                            else {
                                break;
                            }
                        }
                        if (discsInARowSoFar > biggestRun && playerToCheck == turn) {
                            biggestRun = discsInARowSoFar;
                        } else {
                            biggestEnemyRun = discsInARowSoFar;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // Swap playerToCheck to check for the player's runs on the second iteration of this for loop
                    if (playerToCheck == "X") {
                        playerToCheck = "O";
                    } else {
                        playerToCheck = "X";
                    }
                }


                // Give +1 iceCream for each chip every chip is adjacent to
                if (testBoard[row][column] == playerToCheck) {
                    // If disc above
                    try {
                        if (testBoard[row + 1][column] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to top right
                    try {
                        if (testBoard[row  + 1][column + 1] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to right
                    try {
                        if (testBoard[row][column + 1] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to bottom right
                    try {
                        if (testBoard[row - 1][column + 1] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc below
                    try {
                        if (testBoard[row - 1][column] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to bottom right
                    try {
                        if (testBoard[row - 1][column - 1] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to left
                    try {
                        if (testBoard[row][column - 1] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // If disc to top left
                    try {
                        if (testBoard[row + 1][column - 1] == playerToCheck) {
                            iceCream = iceCream + 1;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                } // end adjacent disc iceCream reward if statement

                // If AI can win immediately, give lots of iceCream
                if (biggestRun == discsNeededToWin) {
                    iceCream = iceCream + 999;
                }
                // If player can win immediately, give slightly less iceCream
                if (biggestEnemyRun == discsNeededToWin) {
                    iceCream = iceCream + 750;
                }
            } // end checks on respective columns in rows of board
        } // end checks on rows of board

        return iceCream;
    }
} // end class AI
