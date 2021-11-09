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
    public static void main(String[] args) throws InterruptedException {
        int menuChoice = 0;
        int gameMode = 1; // 1=PvP, 2=PvCPU
        int numRows = 6;
        int numColumns = 7;
        String playAgain = "Y";
        Scanner myInput = new Scanner(System.in);

        menuChoice = getMenuChoice();
        System.out.println(menuChoice);
        // Start Game (with specific board size and game mode)
        if (menuChoice == 1) {
            startGame(numRows, numColumns, gameMode);

            // TODO: Add game timer (ex: at the end of game, print "Game Length: 5:46")
        }
        // View Leaderboard
        else if (menuChoice == 2) {
            // TODO: Change "win streaks" to leaderboard instead
        }
        // Exit
        else if (menuChoice == 4) {

        }
        // Change Game Mode
        else if (menuChoice == 5) {

        }
        // Change Board Width
        else if (menuChoice == 6) {

        }
        // Change Board Height
        else if (menuChoice == 7) {

        }
        // Change number of discs-in-a-row needed to win
        else if (menuChoice == 8) {

        }
        else {
            System.out.println("Error");
        }
    } // end main()

    // Print game options
    // Returns user's selection
    public static int getMenuChoice()
    {
        Scanner myInput = new Scanner(System.in);
        int menuChoice = 0;

        while (true) {
            try {
                System.out.println("|| Connect 4 ||");
                System.out.println("1: Start Game\n2: View Leaderboard\n3: Change Settings\n4: Exit");
                System.out.println("Enter your choice: ");
                menuChoice = myInput.nextInt();

                if (menuChoice == 3) {

                    while (true) {
                        try {
                            System.out.println("|| Settings ||");
                            System.out.println("1: Change Game Mode\n2: Change Board Width\n3: Change Board Height\n4: Change number of discs-in-a-row needed to win\n5: Go back");
                            System.out.println("Enter your choice: ");

                            menuChoice = myInput.nextInt() + 4;

                            if (menuChoice < 5 || menuChoice > 9) {
                                System.out.println("Invalid Input.");
                            }
                            else if (menuChoice == 9) {
                                break;
                            }
                            else {
                                return menuChoice;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input.");
                            myInput.nextLine();
                        }
                    }
                }
                else if (menuChoice > 4 || menuChoice < 1) {
                    System.out.println("Invalid Input.");
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                myInput.nextLine();
            }
        }

        return menuChoice;
    }

    public static void startGame(int numRows, int numColumns, int gameMode) throws InterruptedException {
        // Init variables
        int p1WinStreak = 0;
        int p2WinStreak = 0;
        int cpuWinStreak = 0;
        String p1Character = "";
        String p2Character = "";
        String cpuCharacter = "";

        String turn = "X";
        String winner = "none";
        String[][] board = new String[numRows][numColumns];

        // Get game mode choice
        Object[] settings = setupGame();
        gameMode = (int)(settings[0]);
        p1Character = (String)(settings[1]);
        p2Character = (String)(settings[2]);
        cpuCharacter = (String)(settings[3]);

        // Empty game board
        board = emptyBoard(board);

        // Main gameplay loop
        while (winner != "X" && winner != "O") {
            printBoard(board);

            if (turn.equals("X")) {
                board = placeDisc(board, gameMode, turn);
                turn = "O";
            } else {
                board = placeDisc(board, gameMode, turn);
                turn = "X";
            }

            winner = checkIfWin(board);
        }

        // Determine winner and display any win streaks
        if (p1Character.equals(winner)) {
            System.out.println("Congratulations! Player 1 Wins!");
            p1WinStreak++;
            p2WinStreak = 0;
            cpuWinStreak = 0;
        } else if (p2Character.equals(winner)) {
            System.out.println("Congratulations! Player 2 Wins!");
            p2WinStreak++;
            p1WinStreak = 0;
            cpuWinStreak = 0;
        } else {
            System.out.println("CPU Wins. Womp, womp.");
            cpuWinStreak++;
            p1WinStreak = 0;
            p2WinStreak = 0;
        }

        Thread.sleep(2000);
        printWinStreaks(p1WinStreak, p2WinStreak, cpuWinStreak);
        Thread.sleep(2000);
    }
    // Prints out Connect 4 board
    public static void printBoard(String[][] board)
    {
        // Print title and column numbers
        System.out.print("\n|-----Connect 4-----|");
        System.out.println();
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(" " + (i+1) + " ");
        }
        System.out.println();

        // Print each row, starting from the top of the board
        for (int row = board.length - 1; row >= 0; row--) {
            // Print each column in that row
            for (int column = 0; column < board[row].length; column++) {
                System.out.print("[" + board[row][column] + "]");
            }
            System.out.println();
        }

        // Print column numbers again for readability
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(" " + (i+1) + " ");
        }
        System.out.println("\n");
    } // end printBoard()
    
    // Fills Connect 4 Board with Blank Spaces
    // Returns blanked board
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

    // Asks user for game mode and who they want to play as ('X' or 'O')
    // Returns their choices as an object
    public static Object[] setupGame()
    {
        Scanner myInput = new Scanner(System.in);
        int gameMode = -1;
        String p1Character = "";
        String p2Character = "";
        String cpuCharacter = "";

        // Get game mode
        while (true) {
            try {
                System.out.println("Choose your game mode:\n1: Player vs. Player\n2: Player vs. CPU");
                gameMode = myInput.nextInt();

                if (gameMode < 1 || gameMode > 2) {
                    System.out.println("Invalid Input.");
                }
                else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                myInput.nextLine();
            }
        } // end "get game mode" loop

        // Get who user wants to play as ('X' or 'O)
        while (true) {
            System.out.println("Player 1,\nDo you want to be 'X' (go first) or 'O' (go second)?");
            p1Character = myInput.next().toUpperCase();

            if (p1Character.equals("X")) {
                System.out.println("Player 1 is 'X' and the other player is 'O'.");
                p2Character = "O";
                cpuCharacter = "O";
                break;
            } else if (p1Character.equals("O")) {
                System.out.println("Player 1 is 'O' and the other player is 'X'.");
                p2Character = "X";
                cpuCharacter = "X";
                break;
            } else {
                System.out.println("Invalid Input.");
            }
        } // end "get who user wants to play as" loop

        Object[] settings = {gameMode, p1Character, p2Character, cpuCharacter};
        return settings;
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
    // Returns updated board
    public static String[][] placeDisc(String[][] board, int gameMode, String turn)
    {
        Scanner myInput = new Scanner(System.in);

        // Extra loop to repeat code if user tries to place disc on full column
        while (true) {

            int placeDiscAtRow = 0;
            int placeDiscInColumn = 0;

            // Get user column choice
            while (true) {
                try {
                    System.out.println("Player " + turn + ": Choose column to place disc in: ");
                    placeDiscInColumn = myInput.nextInt() - 1;

                    if (placeDiscInColumn > board[0].length - 1 || placeDiscInColumn < 0) {
                        System.out.println("Invalid Input.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input.");
                    myInput.nextLine();
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

        return board;
    } // end placeDisc()

    // Check if anybody has won
    // Returns winner (or an empty string if there is no winner)
    public static String checkIfWin(String[][] board)
    {
        // First, check if 'X' won
        String playerToCheck = "X";

        // Repeat twice (Check if 'X' won, then check if 'O' won)
        for (int i = 0; i < 2; i++) {

            // Go through each cell on the Connect 4 board
            for (int row = 0; row < board.length; row++ ) {
                for (int column = 0; column < board[row].length; column++) {

                    // Check for horizontal, vertical, left diagonal, and right diagonal 4-in-a-rows (respectively)
                    // This algo is probably super slow, but hey, it works and it's mine :)
                    try {
                        if ((board[row][column].equals(playerToCheck) && board[row][column + 1].equals(playerToCheck) && board[row][column + 2].equals(playerToCheck) && board[row][column + 3].equals(playerToCheck))
                                || (board[row][column].equals(playerToCheck) && board[row + 1][column].equals(playerToCheck) && board[row + 2][column].equals(playerToCheck) && board[row + 3][column].equals(playerToCheck))
                                || (board[row][column].equals(playerToCheck) && board[row + 1][column + 1].equals(playerToCheck) && board[row + 2][column + 2].equals(playerToCheck) && board[row + 3][column + 3].equals(playerToCheck))
                                || (board[row][column].equals(playerToCheck) && board[row + 1][column - 1].equals(playerToCheck) && board[row + 2][column - 2].equals(playerToCheck) && board[row + 3][column - 3].equals(playerToCheck))) {

                            // If somebody wins, print the final state of the board, then return the winner
                            printBoard(board);
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue; // LOL
                    } // end try/catch

                } // end checks on respective columns in rows of board
            } // end checks on rows of board

            // Make this loop check 'O' instead of 'X' next time
            playerToCheck = "O";
        }

        // If there is no winner, return empty string
        return "";
    }
} // end class Connect_4
