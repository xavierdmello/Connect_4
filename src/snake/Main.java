/*  Name: Xavier D'Mello
 *  Course: ICS 3U
 *  Teacher: Mrs. McCaffery
 *  Date: November 8th, 2021
 *  Description: A Game of Connect 4.
 */

package snake;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int numRows = 6;
        int numColumns = 7;
        String[][] board = new String[numRows][numColumns];
        Scanner myInput = new Scanner(System.in);
        
	    System.out.println("   || Connect 4 ||");


        board = emptyBoard(board);
        printBoard(board);
    }
    
    // Prints out Connect 4 board
    public static void printBoard(String[][] board)
    {
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
    }
    
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
    }
}
