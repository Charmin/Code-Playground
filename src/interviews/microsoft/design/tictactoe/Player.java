package interviews.microsoft.design.tictactoe;

import java.util.LinkedList;

public class Player {
    int[] rowsSum;
    int[] colsSum;
    int diags = 0;
    int revDiags = 0;
    LinkedList<int[]> moves;
    int id;

    public Player(final int p, int n) {
        if (p != 1 && p != -1) {
            throw new IllegalArgumentException("Invalid player ID");
        }
        rowsSum = new int[n];
        colsSum = new int[n];
        moves = new LinkedList<>();
        this.id = p;
    }

    public void move(int n, int x, int y) {
        rowsSum[x] += id;
        colsSum[y] += id;
        if (x == y) {
            diags++;
        }
        if (x == n-1-y) {
            revDiags++;
        }
        moves.push(new int[] {x, y});
    }

    public void undo(int n) {
        int[] prev = moves.pop();
        rowsSum[prev[0]] -= id;
        colsSum[prev[1]] -= id;
        diags--;
        if (prev[0] == prev[1]) {
            diags--;
        }
        if (prev[0] == n-1-prev[1]) {
            revDiags--;
        }
    }

    public int[] getRowsSum() {
        return rowsSum;
    }

    public int[] getColsSum() {
        return colsSum;
    }

    public int getDiags() {
        return diags;
    }

    public int getRevDiags() {
        return revDiags;
    }

    public int getId() {
        return id;
    }

    public LinkedList<int[]> getMoves() {
        return moves;
    }
}
