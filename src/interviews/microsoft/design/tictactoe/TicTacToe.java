package interviews.microsoft.design.tictactoe;

public class TicTacToe {

    public static void main(String[] args) {
        Board ticTacToe = new Board(3);
        ticTacToe.printBoard();
        checkWinner(ticTacToe.getPlayer1(), ticTacToe.makeMove(ticTacToe.getPlayer1(), 0, 0));
        ticTacToe.printBoard();
        checkWinner(ticTacToe.getPlayer2(), ticTacToe.makeMove(ticTacToe.getPlayer2(), 0,2));
        ticTacToe.printBoard();
        checkWinner(ticTacToe.getPlayer1(), ticTacToe.makeMove(ticTacToe.getPlayer1(), 1, 1));
        System.out.println("Do");
        ticTacToe.printBoard();
        ticTacToe.undo(ticTacToe.getPlayer1());
        System.out.println("Undo");
        ticTacToe.printBoard();
        checkWinner(ticTacToe.getPlayer2(), ticTacToe.makeMove(ticTacToe.getPlayer2(), 2,2));
        ticTacToe.printBoard();
        checkWinner(ticTacToe.getPlayer1(), ticTacToe.makeMove(ticTacToe.getPlayer1(), 2, 1));
        ticTacToe.printBoard();
        checkWinner(ticTacToe.getPlayer2(), ticTacToe.makeMove(ticTacToe.getPlayer2(), 1,2));
        ticTacToe.printBoard();
    }

    private static void checkWinner(final Player player, int makeMove) {
        int P = player.getId();
        if (makeMove == P) {
            System.out.println("Congrats!! Player with id " + P + " wins");
        }
    }
}
