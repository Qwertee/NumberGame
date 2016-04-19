import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jon on 4/18/16.
 */
public class NumberGameBoard {
    private NumberGamePiece[][] board;
    private int lastX, lastY;
    private int turn;
    
    public NumberGameBoard() {
        board = new NumberGamePiece[10][10];
        turn = 1;
        initializeBoard();
    }

    /**
     * Initializes the board to its default state of the following weights:
     *          5 5 5 5 5 5 5 5 5 5
     *          5 4 4 4 4 4 4 4 4 5
     *          5 4 3 3 3 3 3 3 4 5
     *          5 4 3 2 2 2 2 3 4 5
     *          5 4 3 2 1 1 2 3 4 5
     *          5 4 3 2 1 1 2 3 4 5
     *          5 4 3 2 2 2 2 3 4 5
     *          5 4 3 3 3 3 3 3 4 5
     *          5 4 4 4 4 4 4 4 4 5
     *          5 5 5 5 5 5 5 5 5 5
     */
    public void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // check for grids that need a weight of 5
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    board[i][j] = new NumberGamePiece(5, 0, i, j);
                }

                // check for grids that need a weight of 4
                else if (((i == 1 && j > 0 && j < 9) || (i == 8 && j > 1 && j < 9))  ||
                        ((j == 1 && i > 1 && i < 9) || (j == 8 && i > 1 && i < 9))) {
                    board[i][j] = new NumberGamePiece(4, 0, i, j);
                }

                // check for grids that need a weight of 3
                else if (((i == 2 && j > 1 && j < 8) || (i == 7 && j > 2 && j < 8))  ||
                        ((j == 2 && i > 2 && i < 8) || (j == 7 && i > 2 && i < 8))) {
                    board[i][j] = new NumberGamePiece(3, 0, i, j);
                }

                // check for grids that need a weight of 2
                else if (((i == 3 && j > 2 && j < 7) || (i == 6 && j > 3 && j < 7))  ||
                        ((j == 3 && i > 3 && i < 7) || (j == 6 && i > 3 && i < 7))) {
                    board[i][j] = new NumberGamePiece(2, 0, i, j);
                }
                // fill in the rest of the grid with weight 1
                else {
                    board[i][j] = new NumberGamePiece(1, 0, i, j);
                }
            }
        }
    }
    
    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j].getValue() == 0) {
                    System.out.print("-- ");
                }
                else if (board[i][j].getValue() < 10) {
                    System.out.print("0" + String.valueOf(board[i][j].getValue()) + " ");
                } else {
                    System.out.print(board[i][j].getValue() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("CURRENT SCORE: " + String.valueOf(turn - 1));
    }

    public void runAI(int startX, int startY) {
        board[startX][startY].setValue(1);
        lastX = startX;
        lastY = startY;
        turn++;
        List<NumberGamePiece> possibleMoves = getPossibleMoves();
        while (!possibleMoves.isEmpty()) {
            possibleMoves.get(0).setValue(turn);
            lastX = possibleMoves.get(0).getX();
            lastY = possibleMoves.get(0).getY();
            possibleMoves = getPossibleMoves();
            turn++;
        }

    }
    private List<NumberGamePiece> getPossibleMoves() {
        List<NumberGamePiece> list = new ArrayList<>();

        // check all 8 potential moves and add the available ones to the list
        // TODO Refactor this eventually.
        if (lastX + 3 <= 9 && board[lastX + 3][lastY].getValue() == 0) {
            list.add(board[lastX + 3][lastY]);
        }
        if (lastX - 3 >= 0 && board[lastX - 3][lastY].getValue() == 0) {
            list.add(board[lastX - 3][lastY]);
        }
        if (lastY + 3 <= 9 && board[lastX][lastY + 3].getValue() == 0) {
            list.add(board[lastX][lastY + 3]);
        }
        if (lastY - 3 >= 0 && board[lastX][lastY - 3].getValue() == 0) {
            list.add(board[lastX][lastY - 3]);
        }
        if((lastX + 2 <= 9 && lastY + 2 <= 9) && board[lastX + 2][lastY + 2].getValue() == 0) {
            list.add(board[lastX + 2][lastY + 2]);
        }
        if((lastX + 2 <= 9 && lastY - 2 >= 0) && board[lastX + 2][lastY - 2].getValue() == 0) {
            list.add(board[lastX + 2][lastY - 2]);
        }
        if((lastX - 2 >= 0 && lastY - 2 >= 0) && board[lastX - 2][lastY - 2].getValue() == 0) {
            list.add(board[lastX - 2][lastY - 2]);
        }
        if((lastX - 2 >= 0 && lastY + 2 <= 9) && board[lastX - 2][lastY + 2].getValue() == 0) {
            list.add(board[lastX - 2][lastY + 2]);
        }
        Collections.sort(list, new NumberGamePieceCompare());
        return list;
    }
}
