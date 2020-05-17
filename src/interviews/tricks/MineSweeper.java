package interviews.tricks;

public class MineSweeper {

    public static void main(String[] args) {
        char[][] b = new char[][]{{'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        char[][] u = updateBoard(b, new int[]{3, 0});
        for (int j = 0; j < u.length; j++) {
            for (int i = 0; i < u[0].length; i++) {
                System.out.print(u[j][i] + ",");
            }
            System.out.println();
        }
    }

    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        char[][] b = updateCell(board, click[0], click[1]);
        return b;
    }

    private static char[][] updateCell(char[][] b, int x, int y) {
        char c = b[x][y];
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        switch (c) {
            case 'E':
                int mines = getAdjMines(b,x,y);
                if (mines > 0) {
                    b[x][y] = (char) ('0'+ mines);
                } else {
                    b[x][y] = 'B';
                    for (int[] d : dir) {
                        int nx = x + d[0];
                        int ny = y + d[1];
                        if (isValidCell(b, nx, ny)) {
                            int ec = getAdjMines(b, nx, ny);
                            if (ec > 0) {
                                b[nx][ny] = (char) ('0' + ec);
                            } else {
                                b = updateCell(b, nx, ny);
                            }
                        }
                    }
                }
                break;
            case 'M':
                b[x][y] = 'X';
                break;
        }

        return b;
    }

    private static int getAdjMines(char[][] b, int i, int j) {
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        int c = 0;
        for (int[] d : dir) {
            int nx = i + d[0];
            int ny = j + d[1];
            if (nx < 0 || ny < 0 || nx >= b.length || ny >= b[0].length) continue;
            if (b[nx][ny] == 'M') {
                c++;
            }
        }
        return c;
    }

    private static boolean isValidCell(char[][] b, int i, int j) {
        if (i < 0 || j < 0 || i >= b.length || j >= b[0].length || b[i][j] == 'B') {
            return false;
        }
        return true;
    }
}
