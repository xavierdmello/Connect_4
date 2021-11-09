/*  Name: Xavier D'Mello
 *  Course: ICS 3U
 *  Teacher: Mrs. McCaffery
 *  Date: November 8th, 2021
 *  Description: A Game of Connect 4.
 */

package connect_4;

import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect_4
{
    public static void main(String[] args) throws InterruptedException {

        // Init variables and scanner
        int gameMode = 0; // 1=PvP, 2=PvCPU
        int numRows = 6;
        int numColumns = 7;
        int discsNeededToWin = 4;
        Scanner myInput = new Scanner(System.in);

        // Repeat program until user selects 'exit' on the menu
        while (true) {

            int menuChoice = getMenuChoice(discsNeededToWin);

            // Start Game (with specific board size and game mode)
            if (menuChoice == 1) {
                startGame(numRows, numColumns, gameMode, discsNeededToWin);
            }
            // View Leaderboard
            else if (menuChoice == 2) {
                // TODO: Change "win streaks" to leaderboard instead
            }
            // Exit
            else if (menuChoice == 4) {
                return;
            }
            // Change Game Mode
            else if (menuChoice == 5) {
                gameMode = changeGameMode();
            }
            // Change Board Width
            else if (menuChoice == 6) {
                numColumns = changeBoardWidth(numColumns);
            }
            // Change Board Height
            else if (menuChoice == 7) {
                numRows = changeBoardHeight(numRows);
            }
            // Change number of discs-in-a-row needed to win
            else if (menuChoice == 8) {
                discsNeededToWin = changeDiscsNeededToWin(numRows, numColumns, discsNeededToWin);
            }
            else {
                System.out.println("Error");
            }
        } // end program loop
    } // end main()

    // Change number of discs-in-a-row needed to win.
    // Requires numRows and numColumns to be passed in for error checking.
    // Returns updated int discsNeededToWin
    public static int changeDiscsNeededToWin(int numRows, int numColumns, int discsNeededToWin)
    {
        Scanner myInput = new Scanner(System.in);

        // Get number discs-in-a-row needed to win (with error checking)
        while (true) {
            try {
                System.out.println("|| Change Number of Discs-In-a-Row Needed to Win ||\nCurrent: " + discsNeededToWin + " Discs.\nEnter a new value: ");
                int tempDiscsNeededToWin = myInput.nextInt();

                if (tempDiscsNeededToWin > numRows) {
                    System.out.println("Invalid Input. Please enter a value smaller than " + numRows+ ".");
                } else if (tempDiscsNeededToWin > numColumns) {
                    System.out.println("Invalid Input. Please enter a value smaller than " + numColumns + ".");
                } else if (tempDiscsNeededToWin < 1) {
                    System.out.println("Invalid Input.");
                } else {
                    discsNeededToWin = tempDiscsNeededToWin;
                    break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                myInput.nextLine();
            } // end try/catch
        } // end "get number discs-in-a-row needed to win" loop

        return discsNeededToWin;
    } // end changeDiscsNeededToWin()

    // Print game options
    // Returns user's selection
    public static int getMenuChoice(int discsNeededToWin)
    {
        Scanner myInput = new Scanner(System.in);
        int menuChoice = 0;

        while (true) {
            try {
                System.out.println("|| Connect " + discsNeededToWin + " ||");
                System.out.println("1: Start Game\n2: View Leaderboard\n3: Change Settings\n4: Exit");
                System.out.println("\nEnter your choice: ");
                menuChoice = myInput.nextInt();

                if (menuChoice == 3) {

                    while (true) {
                        try {
                            System.out.println("|| Settings ||");
                            System.out.println("1: Change Game Mode\n2: Change Board Width\n3: Change Board Height\n4: Change number of discs-in-a-row needed to win\n5: Go back");
                            System.out.println("\nEnter your choice: ");

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
    } // end getMenuChoice()

    // Changes game mode
    // Returns update int gameMode();
    public static int changeGameMode()
    {
        Scanner myInput = new Scanner(System.in);
        int gameMode = 0;

        // Get game mode from user (with error checking)
        while (true) {
            try {
                System.out.println("|| Choose a Game Mode ||\n1: Player vs. Player\n2: Player vs. Computer\n\nEnter your choice:");
                gameMode = myInput.nextInt();

                if (gameMode > 2 || gameMode < 1) {
                    System.out.println("Invalid Input.");
                } else {
                    break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                myInput.nextLine();
            }
        }
        return gameMode;
    } // end changeGameMode()

    // Changes board width (num of columns)
    // Returns updated board width (int numColumns)
    public static int changeBoardWidth(int numColumns)
    {
        Scanner myInput = new Scanner(System.in);

        // Get number of columns from user (with error checking)
        while (true) {
            try {
                System.out.println("|| Choose Board Width ||\nCurrent Board Width: " + numColumns + " Columns.\nEnter a New Board Width: ");
                numColumns = myInput.nextInt();

                if (numColumns > 9) {
                    System.out.println("The maximum board size is 9x9");
                } else if (numColumns < 1) {
                    System.out.println("You can't make a board this small!");
                } else {
                    break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                myInput.nextLine();
            }
        }
        return numColumns;
    } // end changeBoardWidth()

    // Changes board height (num of rows)
    // Returns updated board height (int numRows)
    public static int changeBoardHeight(int numRows)
    {
        Scanner myInput = new Scanner(System.in);

        // Get number of columns from user (with error checking)
        while (true) {
            try {
                System.out.println("|| Choose Board Width ||\nCurrent Board Width: " + numRows + " Rows.\nEnter a New Board Width: ");
                numRows = myInput.nextInt();

                if (numRows > 9) {
                    System.out.println("The maximum board size is 9x9");
                } else if (numRows < 1) {
                    System.out.println("You can't make a board this small!");
                } else {
                    break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                myInput.nextLine();
            }
        }
        return numRows;
    } // end changeBoardHeight()

    public static void startGame(int numRows, int numColumns, int gameMode, int discsNeededToWin) throws InterruptedException {

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

        // If gameMode hasn't been set already, then make sure to set it!
        if (gameMode == 0) {
            gameMode = changeGameMode();
        }

        // Get who each player is playing as
        Object[] settings = selectPlayers();
        p1Character = (String)(settings[0]);
        p2Character = (String)(settings[1]);
        cpuCharacter = (String)(settings[2]);

        // Empty game board
        board = emptyBoard(board);

        // Set game timer
        long secondsTimer = System.nanoTime();

        // Main gameplay loop
        while (winner != "X" && winner != "O") {
            printBoard(board, discsNeededToWin);

            if (turn.equals("X")) {
                board = placeDisc(board, gameMode, turn);
                turn = "O";
            } else {
                board = placeDisc(board, gameMode, turn);
                turn = "X";
            }

            winner = checkIfWin(board, discsNeededToWin);
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

        // Convert game length in nanoseconds to game length in seconds
        secondsTimer = (System.nanoTime() - secondsTimer) / 1_000_000_000;

        // Count minutes (will go unused if game is under 60 seconds)
        long minutesTimer =  secondsTimer / 60;

        Thread.sleep(2000);

        // Print time elapsed in game with proper grammar
        if (secondsTimer < 60) {
            System.out.println("Game length: " + secondsTimer + " seconds");
        } else if (secondsTimer < 120) {
            System.out.println("Game length: " + minutesTimer + " minute and " + (secondsTimer % 60) + " seconds.");
        } else {
            System.out.println("Game length: " + minutesTimer + " minutes and " + (secondsTimer % 60) + " seconds.");
        }
        System.out.println();
        Thread.sleep(2000);

        // Print win streaks
//        Thread.sleep(2000);
//        printWinStreaks(p1Win2Streak, p2WinStreak, cpuWinStreak);
//        Thread.sleep(2000);
    }
    // Prints out Connect 4 board
    public static void printBoard(String[][] board, int discsNeededToWin)
    {
        // Print title and column numbers
        System.out.println("\n|-----Connect " + discsNeededToWin + "-----|");

        // TODO: Remove following debug code
        int xHighestScoreInBoard = Ai.calculateHighestScoreInBoard(board,"X", discsNeededToWin);
        System.out.println("X's highest score in board: " + xHighestScoreInBoard);
        int oHighestScoreInBoard = Ai.calculateHighestScoreInBoard(board,"O", discsNeededToWin);
        System.out.println("O's highest score in board: " + oHighestScoreInBoard);

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

    // Asks user for who they want to play as ('X' or 'O')
    // Returns their choices as an object
    public static Object[] selectPlayers()
    {
        // Init variables
        Scanner myInput = new Scanner(System.in);
        String p1Character = "";
        String p2Character = "";
        String cpuCharacter = "";

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

        Object[] settings = {p1Character, p2Character, cpuCharacter};
        return settings;
    } // end getGameMode()

    // Print leaderboard (if there is one)
    public static void printLeaderboard(String[][] leaderboard)
    {

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
                    System.out.println(turn + ": Choose column to place disc in: ");
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
    public static String checkIfWin(String[][] board, int discsNeededToWin)
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

                    // Check horizontals for wins
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (board[row][column + j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                        }
                        if (discsInARowSoFar == discsNeededToWin) {
                            // If somebody wins, print the final state of the board, then return the winner
                            printBoard(board, discsNeededToWin);
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }

                    // Check verticals for wins
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (board[row + j][column].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                        }
                        if (discsInARowSoFar == discsNeededToWin) {
                            // If somebody wins, print the final state of the board, then return the winner
                            printBoard(board, discsNeededToWin);
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }

                    // Check left diagonals for wins
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (board[row + j][column + j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                        }
                        if (discsInARowSoFar == discsNeededToWin) {
                            // If somebody wins, print the final state of the board, then return the winner
                            printBoard(board, discsNeededToWin);
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }

                    // Check right diagonals for wins
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (board[row - j][column - j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                        }
                        if (discsInARowSoFar == discsNeededToWin) {
                            // If somebody wins, print the final state of the board, then return the winner
                            printBoard(board, discsNeededToWin);
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                } // end checks on respective columns in rows of board
            } // end checks on rows of board

            // Make this loop check 'O' instead of 'X' next time
            playerToCheck = "O";
        }

        // If there is no winner, return empty string
        return "";
    }
} // end class Connect_4
