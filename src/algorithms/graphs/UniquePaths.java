package algorithms.graphs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chaitra.kr on 10/03/19.
 */
public class UniquePaths {


    public static void main(String[] args) {
        //int p = uniquePaths(3,2);
        //System.out.println(p);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int a = 10;
        char im = (char) 10;
        System.out.println(a);
        System.out.println(im - (char)0);
    }

    // DFS solution which exceeds time
    public static int uniquePaths(int m, int n) {

        if (m==0) {
            return 0;
        }
        if (m==1 && n == 1) {
            return 1;
        }
        double i = (double) 5;
        StringBuilder s = new StringBuilder();
        s.setCharAt(0, s.charAt(3));
        int[][] grid = new int[m][n];
        Position sp = new Position(0, 0);
        List<List<Position>> paths = new LinkedList<>();
        List<Position> path = new LinkedList<>();
        getPaths(grid, path, paths, sp);
        return paths.size();
    }

    private static void getPaths(int[][] grid, List<Position> path, List<List<Position>> paths, Position cur) {
        if (cur.x == grid.length-1 && cur.y == grid[0].length-1) {
            List<Position> pathN = new LinkedList<>();
            for (Position p : path) {
                pathN.add(p);
            }
            paths.add(pathN);
            path.clear();
            return;
        }

        path.add(cur);
        int[][] dir = {{0 ,1}, {1 , 0}};
        for (int i = 0; i < 2; i++) {
            int nx = cur.x + dir[i][0];
            int ny = cur.y + dir[i][1];
            if (nx >= 0 && ny >=0 && nx < grid.length && ny < grid[0].length) {
                Position next = new Position (nx, ny);
                getPaths(grid, path, paths, next);
            }
        }
    }


}

class Position {

    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

