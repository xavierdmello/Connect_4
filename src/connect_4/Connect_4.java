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
        String[][] board = new String[numRows][numColumns];
        Scanner myInput = new Scanner(System.in);

        while (playAgain = true) {

            // Get game mode choice
            gameMode = getGameMode();

            // Empty game board
            board = emptyBoard(board);

            // Print game board
            printBoard(board);


        } // end game loop
    } // end main()
    
    // Prints out Connect 4 board
    public static void printBoard(String[][] board)
    {
        System.out.println("   || Connect 4 ||");

        // Print each row, starting from bottom left
        for(int row = 0; row < board.length; row++ )
        {
            // Print each column in that row
            for(int column = 0; column < board[row].length; column++)
            {
                System.out.print("[" + board[row][column] + "]");
            }
            System.out.println();
        }
    } // end printBoard()
    
    // Fills Connect 4 Board with Blank Spaces
    public static String[][] emptyBoard(String[][] board)
    {
        // Change each row, starting from bottom left
        for(int row = 0; row < board.length; row++ )
        {
            // Change each column in that row
            for(int column = 0; column < board[row].length; column++)
            {
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
                System.out.println("   || Connect 4 ||");
                System.out.println("Choose your game mode:\n1.Player vs. Player\n2.Player vs. CPU");
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
}
