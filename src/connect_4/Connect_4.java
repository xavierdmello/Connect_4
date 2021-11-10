/*  Name: Xavier D'Mello
 *  Course: ICS 3U
 *  Teacher: Mrs. McCaffery
 *  Date: November 8th, 2021
 *  Description: A Game of Connect 4.
 *               This class handles the majority of the game, but not the AI.
 */

package connect_4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect_4
{
    public static void main(String[] args) throws InterruptedException
    {
        // Init game-persistent variables and scanner
        // These vars will persist over each game (unless user changes them, of course)
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
        } // end main menu loop
    } // end main()

    /* Method Name: changeDiscsNeededToWin
     * Description: Change number of discs-in-a-row needed to win.
     * Parameters: int numRows, int numColumns, int discsNeededToWin
     * Returns: updated value of int discsNeededToWin
     */
    public static int changeDiscsNeededToWin(int numRows, int numColumns, int discsNeededToWin)
    {
        Scanner myInput = new Scanner(System.in);

        // Get number discs-in-a-row needed to win (with error checking)
        while (true) {
            try {
                // Get new value of discs
                System.out.println("\n|| Change Number of Discs-In-a-Row Needed to Win ||\nCurrent: " + discsNeededToWin + " Discs.\nEnter a new value: ");
                int tempDiscsNeededToWin = myInput.nextInt();

                // Error Check Updated Value & Proper Grammar
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

    /* Method Name: getMenuChoice
     * Description: Print Main Menu / Settings Menu & Return User Choice
     * Parameters: int discsNeededToWin
     * Returns: int menuChoice
     */
    public static int getMenuChoice(int discsNeededToWin)
    {
        // Init Variables
        Scanner myInput = new Scanner(System.in);
        int menuChoice = 0;

        // Repeat menu until user enters correct input
        while (true) {
            try {
                // Main Menu Options
                System.out.println("\n|| Connect " + discsNeededToWin + " ||");
                System.out.println("1: Start Game\n2: Change Settings\n3: Exit");
                System.out.println("Enter your choice: ");
                menuChoice = myInput.nextInt();

                // If user selects the settings menu, print the settings menu
                if (menuChoice == 2) {
                    while (true) {
                        try {
                            // Settings Menu Options
                            System.out.println("\n|| Settings ||");
                            System.out.println("1: Change Game Mode\n2: Change Board Width\n3: Change Board Height\n4: Change number of discs-in-a-row needed to win\n5: Go back");
                            System.out.println("Enter your choice: ");

                            // Because menuChoice is being handled by main(),
                            // increment by 3 to differentiate settings from main menu options.
                            menuChoice = myInput.nextInt() + 3;

                            // Error Checking
                            if (menuChoice < 4 || menuChoice > 8) {
                                System.out.println("Invalid Input.");
                            }
                            // If user selects "Go Back"
                            else if (menuChoice == 8) {
                                break;
                            }
                            // If user enters a valid input
                            else {
                                return menuChoice;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input.");
                            myInput.nextLine();
                        }
                    } // end settings menu loop
                } // end setting menu if statement

                // If user selects a non-existent value on the main menu
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
        } // End Main Menu Loop

        // After all checks have passed, return user's menu choice to main()
        return menuChoice;
    } // end getMenuChoice()

    /* Method Name: changeGameMode
     * Description: Changes Game Mode (1=PvP, 2=PvCPU)
     * Parameters: None
     * Returns: Returns updated int gameMode();
     */
    public static int changeGameMode()
    {
        // Init Variables
        Scanner myInput = new Scanner(System.in);
        int gameMode = 0;

        // Get game mode from user (with error checking). Repeat menu until they enter a proper value.
        while (true) {
            try {
                // Print Game Mode Options
                System.out.println("\n|| Choose a Game Mode ||\n1: Player vs. Player\n2: Player vs. Computer\nEnter your choice:");
                gameMode = myInput.nextInt();

                // Check if user tried to select a non-existent option
                if (gameMode > 2 || gameMode < 1) {
                    System.out.println("Invalid Input.");
                }
                // Give player feedback that their selection worked (important)
                else {
                    if (gameMode == 1) {
                        System.out.println("Gamemode set to Player vs. Player.");
                    } else {
                        System.out.println("Gamemode set to Player vs. Computer.");
                    }
                    break;
                } // End error checking & feedback if statement
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                myInput.nextLine();
            } // end Game Mode menu try/catch
        } // end Game Mode menu selection loop

        // After all checks have passed, return user's menu choice to main()
        return gameMode;
    } // end changeGameMode()

    /* Method Name: changeBoardWidth
     * Description: Changes Board Width (num of columns)
     * Parameters: int numColumns
     * Returns: Updated board width (int numColumns)
     */
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
                } else if (tempNumColumns < 3) {
                    System.out.println("The minimum board size is 3x3");
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

    /* Method Name: changeBoardHeight
     * Description: Changes Board Height (num of rows)
     * Parameters: int numRows
     * Returns: Updated board height (int numRows)
     */
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
                } else if (tempNumRows < 3) {
                    System.out.println("The minimum board size is 3x3");
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

    /* Method Name: startGame
     * Description: It's the main game loop. Starts game with specific board size and game mode.
     * Parameters: int numRows, int numColumns, int gameMode, int discsNeededToWin
     * Returns: None
     */
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

            // Print Board
            printBoard(board, discsNeededToWin);

            // If its X's turn:
            if (turn.equals("X")) {

                // If it's the CPU's turn:
                if (cpuCharacter.equals("X")) {
                    // Get CPU's move
                    board = AI.makeAiMove(board, turn, discsNeededToWin);
                    System.out.println("CPU is Calculating Move...");
                    Thread.sleep(2000);
                }

                // If it's a player's turn:
                else {
                    // Get player's move
                    board = placeDisc(board, turn);
                }

                // Swap turn to the other player for the next iteration of this loop
                turn = "O";
            }
            // If its O's turn:
            else {

                // If it's the CPU's turn:
                if (cpuCharacter.equals("O")) {
                    // Get CPU's move
                    board = AI.makeAiMove(board, turn, discsNeededToWin);
                    System.out.println("CPU is Calculating Move...");
                    Thread.sleep(2000);
                }
                // If it's a player's turn:
                else {
                    // Get player's move
                    board = placeDisc(board, turn);
                }

                // Swap turn to the other player for the next iteration of this loop
                turn = "X";
            }

            // Check if there is a winner after each round
            winner = checkIfWin(board, discsNeededToWin);
        } // end main gameplay loop

        // Once there is a winner, print final winning board
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

    /* Method Name: printBoard
     * Description: Prints Out The Connect-4 Board.
     * Parameters: String[][] board, int discsNeededToWin
     * Returns: None
     */
    public static void printBoard(String[][] board, int discsNeededToWin)
    {
        // Prints Title CENTERED to Board - veryyyyy prettyyyyyy (* ^ ω ^)
        // Total width of board = width of board with all the columns and formatting
        int totalWidthOfBoard = (board[0].length * 4);
        // Characters in "Connect 4" = 9.
        // Print half of (width - 9) dashes on each side of the title to center it.
        System.out.print("|");
        for (int i = 0; i < (totalWidthOfBoard - 9)/2 ; i++) {
            // dashes BEFORE the "title"
            System.out.print("-");
        }
        System.out.print("Connect " + discsNeededToWin);
        for (int i = 0; i < (totalWidthOfBoard - 9)/2; i++) {
            // dashes AFTER the "title"
            System.out.print("-");
        }
        System.out.print("|\n");

        // Print each row, starting from the top of the board
        for (int row = board.length - 1; row >= 0; row--) {
            // Print each column in that row
            System.out.print("| ");
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(board[row][column] + " | ");
            }
            System.out.println();
        } // end main row printer loop

        // Print column numbers
        System.out.print("  ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print((i+1) + "   ");
        }
        System.out.println("\n");
    } // end printBoard()

    /* Method Name: emptyBoard
     * Description: Fills Connect 4 Board With Blank Spaces
     * Parameters: String[][] board
     * Returns: Emptied Connect 4 board (String[][] board)
     */
    public static String[][] emptyBoard(String[][] board)
    {
        // Change each row, starting from bottom left:
        for (int row = 0; row < board.length; row++ ) {
            // Change each column in that row:
            for (int column = 0; column < board[row].length; column++) {
                // Set each cell to a blank space:
                board[row][column] = " ";
            }
        }

        // Return the emptied board
        return board;
    } // end emptyBoard()

    /* Method Name: selectPlayer
     * Description: Only for PvCPU games. Asks user for who they want to play as ('X' or 'O')
     *              --> For PvP games, the users can figure out who plays as X and O themselves. Lol.
     * Parameters: None
     * Returns: Returns cpuCharacter (returning player character as well is redundant)
     */
    public static String selectPlayer()
    {
        // Init variables
        Scanner myInput = new Scanner(System.in);
        String playerCharacter = "";
        String cpuCharacter = "";

        // Get who user wants to play as ('X' or 'O)
        while (true) {
            // Get user choice
            System.out.println("Do you want to play as X or O? (X/O)");
            playerCharacter = myInput.next().toUpperCase();

            // If user chooses to be X
            if (playerCharacter.equals("X")) {
                System.out.println("Player is X and CPU is O.");
                cpuCharacter = "O";
                break;
            }
            // If user chooses to be O
            else if (playerCharacter.equals("O")) {
                System.out.println("Player is O and CPU is X.");
                cpuCharacter = "X";
                break;
            }
            // Otherwise, user has entered an invalid choice.
            else {
                System.out.println("Invalid Input.");
            }
        } // end "get who user wants to play as" loop

        // Returns the CPU's character. Returning player character as well is redundant.
        return cpuCharacter;
    } // end getGameMode()

    /* Method Name: placeDisc
     * Description: Asks user for their move and places disc in board.
     * Parameters: String[][] board,String turn
     * Returns: Returns updated board (String[][] board)
     */
    public static String[][] placeDisc(String[][] board, String turn)
    {
        // Init scanner
        Scanner myInput = new Scanner(System.in);

        // Extra loop to repeat code if user tries to place disc on full column
        // The purpose of this loop will become more apparent in 30 lines :)
        while (true) {

            // These vars will be the final coordinates of the disc after it is dropped into a column
            int placeDiscAtRow = 0;
            int placeDiscInColumn = 0;

            // Get what column user wants to place their piece in.
            // Repeats until user enters valid input.
            // Does not check if piece will overflow column - that will happen later.
            while (true) {
                try {
                    // Get user input:
                    System.out.println(turn + ": Choose column to place disc in: ");
                    placeDiscInColumn = myInput.nextInt() - 1;

                    // If input is out of bounds:
                    if (placeDiscInColumn > board[0].length - 1 || placeDiscInColumn < 0) {
                        System.out.println("Invalid Input.");
                    } else {
                        break;
                    }

                // If input isn't a number:
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input.");
                    myInput.nextLine();
                } // end try/catch
            } // end "get user move" loop

            // Find what row in column to place chip at (making disc "fall to bottom")
            for (int row = 0; row < board.length; row++) {
                if (!board[row][placeDiscInColumn].equals(" ")) {
                    placeDiscAtRow = row + 1;
                }
            }

            // Check if placing disk here would overflow the column and cause the piece to fall on the floor :(
            if (placeDiscAtRow > 5) {
                // If it will, yell at user, and repeat selection loop:
                System.out.println("Invalid Input.");
            } else {
                // If the move is perfectly valid, put move into board array, and end column selection loop:
                board[placeDiscAtRow][placeDiscInColumn] = turn;
                break;
            }  // end column overflow check
        } // end column selection loop

        // Return updated board
        return board;
    } // end placeDisc()


    /* Method Name: checkIfWin
     * Description: Checks the board to see if anybody won.
     * Parameters: String[][] board, int discsNeededToWin
     * Returns: Winner (String playerToCheck) or an empty string if nobody has won.
     */
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

        } // end "check both players" loop (repeats twice)

        // If there is no winner, return empty string
        return "";
    } // end checkIfWin()
} // end class Connect_4
