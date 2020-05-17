package interviews.amazon;

import java.util.HashSet;
import java.util.Set;

public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = {
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}
        };
        solve(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void solve(char[][] board) {
        // pass 1: mark all points reachable from the edge as 1;
        int cols = board[0].length;
        int rows = board.length;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i == 0 || j == 0 || i == rows - 1 || j == cols - 1) && board[i][j] == 'O' && !visited.contains(i * cols + j)) {
                    dfs(board, i, j, visited);
                }
            }
        }

        //pass 2: replace all 0s by X
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        //pass 3: replace all 1s by 0
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void dfs(char[][] board, int i, int j, Set<Integer> visited) {
        int cols = board[0].length;
        visited.add(i * cols + j);
        board[i][j] = '1';

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d: dirs) {
            int i1 = d[0] + i;
            int j1 = d[1] + j;
            int code = i1*cols + j1;
            if (j1 < 0 || i1 < 0 || i1 >= board.length || j1 >= cols || visited.contains(code)) continue;

            if (board[i1][j1] == 'O') {
                dfs(board, i1, j1, visited);
            }
        }
    }


}
