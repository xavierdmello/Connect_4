/*  Name: Xavier D'Mello
 *  Course: ICS 3U
 *  Teacher: Mrs. McCaffery
 *  Date: November 8th, 2021
 *  Description: A Game of Connect 4.
 */

package connect_4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect_4
{
    public static void main(String[] args)
    {
        // Init variables and scanner
        int numRows = 6;
        int numColumns = 7;
        int gameMode; // 0=PvP, 1=PvCPU
        int p1WinStreak = 0;
        int p2WinStreak = 0;
        int cpuWinStreak = 0;
        boolean playAgain = true;
        boolean gameOver = false;
        String[][] board = new String[numRows][numColumns];
        Scanner myInput = new Scanner(System.in);

        System.out.println("|| Connect 4 ||");

        while (playAgain = true) {

            // Print win streaks
            printWinStreaks(p1WinStreak, p2WinStreak, cpuWinStreak);

            // Get game mode choice
            gameMode = getGameMode();

            // Empty game board
            board = emptyBoard(board);

            // Main gameplay loop
            while (gameOver == false) {
                printBoard(board);
                // Place disc for each player
                board = placeDisc(board, gameMode, "X");
                printBoard(board);
                board = placeDisc(board, gameMode, "O");
            }
        } // end program loop
    } // end main()
    
    // Prints out Connect 4 board
    public static void printBoard(String[][] board)
    {
        // Print title and column numbers
        // TODO: Figure out how to centre title
        System.out.println("|| Connect 4 ||");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(" " + (i+1) + " ");
        }
        System.out.println();

        // Print each row, starting from bottom left
        for (int row = 0; row < board.length; row++ ) {
            // Print each column in that row
            for (int column = 0; column < board[row].length; column++) {
                System.out.print("[" + board[row][column] + "]");
            }
            System.out.println();
        }
    } // end printBoard()
    
    // Fills Connect 4 Board with Blank Spaces
    public static String[][] emptyBoard(String[][] board)
    {
        // Change each row, starting from bottom left
        for (int row = 0; row < board.length; row++ ) {
            // Change each column in that row
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = " ";
            }
        }
        return board;
    } // end emptyBoard()

    // Asks user for game mode
    public static int getGameMode()
    {
        Scanner myInput = new Scanner(System.in);
        int gameMode = -1;

        while (true) {
            try {
                System.out.println("Choose your game mode:\n1: Player vs. Player\n2: Player vs. CPU");
                gameMode = myInput.nextInt();

                if (gameMode < 0 || gameMode > 1) {
                    System.out.println("Invalid Input.");
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
            }
        }
        return gameMode;
    } // end getGameMode()

    // Prints win streaks (if there are any)
    public static void printWinStreaks(int p1WinStreak, int p2WinStreak, int cpuWinStreak)
    {
        if (p1WinStreak != 0) {
            System.out.print("Player 1 Win Streak: " + p1WinStreak + " || ");

            if (p2WinStreak != 0 || cpuWinStreak != 0) {
                System.out.print(" | ");
            }
        }
        if (p2WinStreak != 0) {
            System.out.println("Player 2 Win Streak: " + p1WinStreak);

            if (cpuWinStreak != 0) {
                System.out.print(" | ");
            }
        }
        if (cpuWinStreak != 0) {
            System.out.print("CPU Win Streak: " + cpuWinStreak);
        }
    } // end printWinStreaks()

    // Ask for choice and place disc in board
    public static String[][] placeDisc(String[][] board, int gameMode, String turn)
    {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Player " + turn + ": Choose column to place disc in: ");
        int placeDiscInColumn = myInput.nextInt() - 1;
        int placeDiscAtRow = 0;

        // Find what row in column to place chip at (making disc "fall to bottom")
        for (int row = 0; row < board.length; row++) {
            if (!board[row][placeDiscInColumn].equals(" ")) {
                placeDiscAtRow = row + 1;
            }
        }

        board[placeDiscAtRow][placeDiscInColumn] = turn;
        return board;
    } // end placeDisc()
} // end class Connect_4
