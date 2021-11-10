package connect_4;

import java.util.Arrays;

public class Board {

    String[][] board;
    int score;

    public Board(String[][] board, int score) {
        this.board = Arrays.stream(board).map(String[]::clone).toArray(String[][]::new);
        this.score = score;
    }
}
