package problem_solving;


import java.util.*;

/**
 * Created by chaitra.kr on 17/11/18.
 */
public class SurroundedRegions {

    public static Set<Cordinate> borderConnections = new HashSet<>();
    static int MAX_ROW = 0;
    static int MAX_COL = 0;

    public static void main(String[] args) {
        char[][] board =
                {{'O', 'X', 'O'},
                        {'X', 'O', 'X'},
                        {'O', 'X', 'O'}};
        solve(board);
    }

    public static void solve(char[][] board) {
        if (board!=null && board.length > 0 && board[0].length > 0) {
            List<List<Character>> input = convertBoard(board);
            MAX_ROW = board.length - 1;
            MAX_COL = board[0].length - 1;

            for (int i = 1; i < input.size(); i++) {
                for (int j = 1; j < input.get(i).size(); j++) {
                    Cordinate cordinate = new Cordinate(i, j);
                    if (input.get(i).get(j).equals('O')) {
                        Set<Cordinate> visited = new HashSet<>();
                        LinkedList<Cordinate> queue = new LinkedList<>();
                        queue.add(cordinate);
                        if (!isBorderConnected(visited, queue, input)) {
                            board[i][j] = 'X';
                        }
                    }
                }
            }
        }
        System.out.println("");
    }

    private static boolean isBorderConnected(Set<Cordinate> visited, LinkedList<Cordinate> queue, List<List<Character>> input) {
        if (borderConnections.contains(queue.getFirst())) {
            return true;
        }
        while (!queue.isEmpty()) {
            Cordinate cordinate = queue.remove();
            visited.add(cordinate);
            List<Cordinate> n = getNeighbours(cordinate, input);
            for (Cordinate n1 : n) { //4 loop
                if (!visited.contains(n1)) {
                    queue.add(n1);
                }
            }
            if (!borderConnections.contains(cordinate)) {
                if (cordinate.getX() == 0 || cordinate.getX() == MAX_ROW || cordinate.getY() == 0
                        || cordinate.getY() == MAX_COL) {
                    borderConnections.addAll(queue);
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Cordinate> getNeighbours(Cordinate cordinate, List<List<Character>> input) {
        int[][] dirArray = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        List<Cordinate> neighbours = new ArrayList<>();
        for (int[] dir : dirArray) {
            int x = cordinate.getX() + dir[0];
            int y = cordinate.getY() + dir[1];
            if (x >= 0 && x <= MAX_ROW && y >= 0 && y <= MAX_COL && input.get(x).get(y) == 'O') {
                Cordinate cordinate1 = new Cordinate(x, y);
                neighbours.add(cordinate1);
            }
        }
        return neighbours;
    }

    private static List<List<Character>> convertBoard(char[][] board) {
        List<List<Character>> input = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            List<Character> subInput = new ArrayList<>();
            for (int j = 0; j < board[i].length; j++) {
                subInput.add(board[i][j]);
            }
            input.add(subInput);
        }
        return input;
    }

    static class Cordinate {
        int x;
        int y;

        public Cordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cordinate cordinate = (Cordinate) o;

            if (x != cordinate.x) return false;
            if (y != cordinate.y) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
