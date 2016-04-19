public class NumberGameMain {
    public static void main(String[] args) {
        NumberGameBoard board = new NumberGameBoard();
        board.runAI(0, 0);
        board.printBoard();
    }
}
