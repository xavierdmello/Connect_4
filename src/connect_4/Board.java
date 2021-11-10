/*  Name: Xavier D'Mello
 *  Course: ICS 3U
 *  Teacher: Mrs. McCaffery
 *  Date: November 10th, 2021
 *  Description: Class that holds a board and its respective iceCream score.
 *               The AI will create a Board object for each possible move,
 *               and do whatever move will give it the most iceCream.
 */

package connect_4;

import java.util.Arrays;

public class Board
{
    // Init variables
    String[][] board;
    int score;

    // Constructor
    public Board(String[][] board, int score)
    {
        this.board = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
        this.score = score;
    } // end Board constructor
} // end class Board
