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
            // Exit
            else if (menuChoice == 3) {
                return;
            }
            // Change Game Mode
            else if (menuChoice == 4) {
                gameMode = changeGameMode();
            }
            // Change Board Width
            else if (menuChoice == 5) {
                numColumns = changeBoardWidth(numColumns);
            }
            // Change Board Height
            else if (menuChoice == 6) {
                numRows = changeBoardHeight(numRows);
            }
            // Change number of discs-in-a-row needed to win
            else if (menuChoice == 7) {
                discsNeededToWin = changeDiscsNeededToWin(numRows, numColumns, discsNeededToWin);
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
                System.out.println("\n|| Change Number of Discs-In-a-Row Needed to Win ||\nCurrent: " + discsNeededToWin + " Discs.\nEnter a new value: ");
                int tempDiscsNeededToWin = myInput.nextInt();

                if (tempDiscsNeededToWin > numRows) {
                    System.out.println("Your board is only " + numRows+ " rows tall. Please enter a smaller number of discs.");
                } else if (tempDiscsNeededToWin > numColumns) {
                    System.out.println("Your board is only " + numColumns + " columns wide. Please enter a smaller number of discs.");
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
                System.out.println("\n|| Connect " + discsNeededToWin + " ||");
                System.out.println("1: Start Game\n2: Change Settings\n3: Exit");
                System.out.println("Enter your choice: ");
                menuChoice = myInput.nextInt();

                if (menuChoice == 2) {

                    while (true) {
                        try {
                            System.out.println("\n|| Settings ||");
                            System.out.println("1: Change Game Mode\n2: Change Board Width\n3: Change Board Height\n4: Change number of discs-in-a-row needed to win\n5: Go back");
                            System.out.println("Enter your choice: ");

                            menuChoice = myInput.nextInt() + 3;

                            if (menuChoice < 4 || menuChoice > 8) {
                                System.out.println("Invalid Input.");
                            }
                            else if (menuChoice == 8) {
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
                else if (menuChoice > 3 || menuChoice < 1) {
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
                System.out.println("\n|| Choose a Game Mode ||\n1: Player vs. Player\n2: Player vs. Computer\nEnter your choice:");
                gameMode = myInput.nextInt();

                if (gameMode > 2 || gameMode < 1) {
                    System.out.println("Invalid Input.");
                } else {
                    if (gameMode == 1) {
                        System.out.println("Gamemode set to Player vs. Player.");
                    } else {
                        System.out.println("Gamemode set to Player vs. Computer.");
                    }
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
                System.out.println("\n|| Choose Board Width ||\nCurrent Board Width: " + numColumns + " Columns\nEnter a New Board Width: ");
                int tempNumColumns = myInput.nextInt();

                if (tempNumColumns > 9) {
                    System.out.println("The maximum board size is 9x9");
                } else if (numColumns < 1) {
                    System.out.println("You can't make a board this small!");
                } else {
                    numColumns = tempNumColumns;
                    System.out.println("New Board Width: " + numColumns + " Columns");
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
                System.out.println("\n|| Choose Board Height ||\nCurrent Board Height: " + numRows + " Rows\nEnter a New Board Height: ");
                int tempNumRows = myInput.nextInt();

                if (tempNumRows > 9) {
                    System.out.println("The maximum board size is 9x9");
                } else if (numRows < 1) {
                    System.out.println("You can't make a board this small!");
                } else {
                    numRows = tempNumRows;
                    System.out.println("New Board Height: " + numRows + " Rows");
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
        String cpuCharacter = "";
        String turn = "X";
        String winner = "none";
        String[][] board = new String[numRows][numColumns];

        // If gameMode hasn't been set already, then make sure to set it!
        if (gameMode == 0) {
            gameMode = changeGameMode();
        }

        // Player can choose who they want to be (X or O) if they are playing a PvCPU game.
        // Get Player choice and set CPU choice opposite to that. Ex: if Player = O --> CPU = X.
        if (gameMode == 2) {
            cpuCharacter = selectPlayer();
        }

        // Empty game board
        board = emptyBoard(board);

        // Set game timer
        long secondsTimer = System.nanoTime();

        // Main gameplay loop
        while (!winner.equals("X") && !winner.equals("O")) {
            printBoard(board, discsNeededToWin);
            if (turn.equals("X")) {
                if (cpuCharacter.equals("X")) {
                    board = AI.makeAiMove(board, turn, discsNeededToWin);
                    System.out.println("CPU is Calculating Move...");
                    Thread.sleep(2000);
                }
                else {
                    board = placeDisc(board, gameMode, turn);
                }
                turn = "O";
            } else {
                if (cpuCharacter.equals("O")) {
                    board = AI.makeAiMove(board, turn, discsNeededToWin);
                    System.out.println("CPU is Calculating Move...");
                    Thread.sleep(2000);
                }
                else {
                    board = placeDisc(board, gameMode, turn);
                }
                turn = "X";
            }
            // Check if there is a winner after each round
            winner = checkIfWin(board, discsNeededToWin);
        } // end main gameplay loop

        // Print final winning board
        printBoard(board, discsNeededToWin);

        // Print winner
        System.out.println(winner + " Wins!");

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
        Thread.sleep(2000);
    } // end startGame()

    // Prints out Connect 4 board
    public static void printBoard(String[][] board, int discsNeededToWin)
    {
        // Print title
        System.out.println("\n|---------Connect " + discsNeededToWin + "---------|");

        // Print each row, starting from the top of the board
        for (int row = board.length - 1; row >= 0; row--) {
            // Print each column in that row
            System.out.print("| ");
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(board[row][column] + " | ");
            }
            System.out.println();
        }

        // Print column numbers
        System.out.print("  ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print((i+1) + "   ");
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

    // Only for PvCPU games.
    // Asks user for who they want to play as ('X' or 'O')
    // Returns cpuCharacter (returning player character as well is redundant)
    public static String selectPlayer()
    {
        // Init variables
        Scanner myInput = new Scanner(System.in);
        String playerCharacter = "";
        String cpuCharacter = "";

        // Get who user wants to play as ('X' or 'O)
        while (true) {
            System.out.println("Do you want to play as X or O? (X/O)");
            playerCharacter = myInput.next().toUpperCase();

            if (playerCharacter.equals("X")) {
                System.out.println("Player is X and CPU is O.");
                cpuCharacter = "O";
                break;
            } else if (playerCharacter.equals("O")) {
                System.out.println("Player is O and CPU is X.");
                cpuCharacter = "X";
                break;
            } else {
                System.out.println("Invalid Input.");
            }
        } // end "get who user wants to play as" loop

        return cpuCharacter;
    } // end getGameMode()

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
                            // If somebody wins return the winner
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // Check verticals for wins
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (board[row + j][column].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                        }
                        if (discsInARowSoFar == discsNeededToWin) {
                            // If somebody wins return the winner
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // Check left diagonals for wins
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (board[row + j][column + j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                        }
                        if (discsInARowSoFar == discsNeededToWin) {
                            // If somebody wins return the winner
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}

                    // Check right diagonals for wins
                    try {
                        int discsInARowSoFar = 0;
                        for (int j = 0; j < discsNeededToWin; j++) {
                            if (board[row + j][column - j].equals(playerToCheck)) {
                                discsInARowSoFar++;
                            }
                        }
                        if (discsInARowSoFar == discsNeededToWin) {
                            // If somebody wins return the winner
                            return playerToCheck;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                } // end checks on respective columns in rows of board
            } // end checks on rows of board

            // Make this loop check 'O' instead of 'X' next time
            playerToCheck = "O";
        }

        // If there is no winner, return empty string
        return "";
    }
} // end class Connect_4
