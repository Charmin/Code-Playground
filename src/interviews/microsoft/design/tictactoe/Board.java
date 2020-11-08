package interviews.microsoft.design.tictactoe;

/*

From leetcode:

Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.

Requirements:
 - 2 players
 - board
 -


*/

import java.util.HashMap;
import java.util.Map;

/**
 * Board
 * - initialise
 * - getBoard()
 * - getWinner()
 * - getCurrentPlayer()
 * - makeMove(Move m)
 * Game
 * -
 * - gameId
 * - player1
 * - player2
 * - List<Move>
 * <p>
 * GamePlay
 * - boolean makeMove(int x, int y) (true/false indicates victory / faliure)
 * -
 */
public class Board {
    int[][] board;
    Player player1;
    Player player2;

    public Board(int n) {
        board = new int[n][n];
        player1 = new Player(-1, n);
        player2 = new Player(1, n);
    }

    public void undo(final Player p) {
        if (p.getMoves().isEmpty()) {
            throw new RuntimeException("There are no moved made by this player");
        }
        int[] lasMove = p.getMoves().peek();
        board[lasMove[0]][lasMove[1]] = 0;
        p.undo(board.length);
    }

    public int makeMove(Player player, int x, int y) throws IllegalArgumentException {
        validateMove(x, y);
        board[x][y] = player.getId();
        player.move(board.length, x, y);
        return isWinner(player, x, y) ? player.getId() : 0;
    }

    private void validateMove(int x, int y) {
        int n = board.length;
        if (x >= n || y >= n || x < 0 || y < 0) {
            throw new IllegalArgumentException("Invalid move");
        }
        if (board[x][y] != 0) {
            throw new IllegalArgumentException("This position is occupied");
        }
    }

    private boolean isWinner(Player player, int x, int y) {
        int n = board.length;
        return Math.abs(player.getRowsSum()[x]) == n || Math.abs(player.getColsSum()[y]) == n
                || player.getDiags() == n || player.getRevDiags() == n;
    }

    public void printBoard() {
        System.out.println("Player 1: "+player1.getId()+" Player 2: "+player2.getId());
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
