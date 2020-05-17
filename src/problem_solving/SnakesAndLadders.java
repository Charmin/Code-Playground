package problem_solving;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SnakesAndLadders {

    public static void main(String[] args) {
        int[][] b = {{-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}};
        int s = snakesAndLadders(b);
        System.out.println(s);
    }

    public static int snakesAndLadders(int[][] board) {
        Queue<QItem> que = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(1);
        QItem item = new QItem(1, 0);
        que.add(item);
        int size = board[0].length;
        int finishCell = size * size;

        int[] moves = getMoves(board);
        while (!que.isEmpty()) {
            QItem qin = que.poll();
            int s = qin.getItem();
            if (s == finishCell) {
                return qin.getDices();
            }
            int s2 = 0;
                for (int j = 1; j <= 6 && s+j-1 < finishCell; j++) {
                s2 = moves[s + j - 1] == -1 ? s + j : moves[s + j - 1];
                if (!visited.contains(s2)) {
                    visited.add(s2);
                    que.add(new QItem(s2, qin.getDices() + 1));
                }
            }
        }
        return -1;
    }

    private static int[] getMoves(int[][] board) {
        int cells = board.length * board.length;
        int[] moves = new int[cells];
        int len = board.length;

        //traverse all cells and get the moves from the board
        for (int i = 1; i <= cells; i++) {
            int row = (len - 1) - ((i - 1) / len); //len-1 because of the zero indexed matrix
            int col = (row % 2 == len % 2) ? ((len - 1) - ((i - 1) % len)) : (i - 1) % len;
            moves[i - 1] = board[row][col];
        }
        return moves;
    }
}

class QItem {
    int item;
    int diceThrows;

    public QItem(int a, int b) {
        this.item = a;
        this.diceThrows = b;
    }

    public int getItem() {
        return item;
    }


    public int getDices() {
        return diceThrows;
    }
}
