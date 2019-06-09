package algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by chaitra.kr on 08/03/19.
 */
public class IslandSize {


    public static void main(String[] args) {
        int[][] input = {{1,1,0,0,0},
                         {1,1,0,0,0},
                         {0,0,0,1,1},
                         {0,0,0,1,1},};
        int s = maxAreaOfIsland(input);
        System.out.println(s);
    }


    public static int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        Set<Pair> visited = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Pair cur = new Pair(i,j);
                int area = 0;
                if (grid[i][j] == 1 && !visited.contains(cur)) {
                    area = getArea(i, j, visited, grid);
                }
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    private static int getArea(int i, int j, Set<Pair> visited, int[][]grid) {
        Pair p = new Pair(i,j);
        int[][] dir = {{-1,0},{0,1},{0,-1},{1,0}};
        LinkedList<Pair> q = new LinkedList<Pair>();
        visited.add(p);
        q.add(p);
        int count = 1;
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int k = 0; k < dir.length; k++) {
                int nx = cur.x + dir[k][0];
                int ny = cur.y + dir[k][1];
                Pair np = new Pair(nx, ny);
                if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid.length && grid[nx][ny] == 1 && !visited.contains(np)) {
                    q.add(np);
                    visited.add(np);
                    count++;
                }
            }
        }
        return count;
    }
}

class Pair {

    int x;
    int y;

    public Pair(int a, int b) {
        this.x = a;
        this.y = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        if (x != pair.x) return false;
        return y == pair.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
 }

