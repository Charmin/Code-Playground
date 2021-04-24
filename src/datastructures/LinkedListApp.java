package datastructures;

import algorithms.IceCreamParlour;

/**
 * Created by chaitra.kr on 26/09/17.
 */

public class LinkedListApp {

    public static void main(String[] args) {
//        LinkedPList linkedListOne = new LinkedPList();
//        linkedListOne.insert(4);
//        linkedListOne.insert(5);
//        linkedListOne.insert(6);
//        linkedListOne.insert(7);
//        linkedListOne.insert(8);
//        linkedListOne.insert(9);
//        linkedListOne.insert(4);
//        linkedListOne.display();

        char[][] grid = {{1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        };
        StringBuilder b = new StringBuilder();
        b.append("c");
        b.append("d");
        b.deleteCharAt(b.length()-1);
        System.out.println(b);
        int islands = numIslands(grid);


        int[][] grid2 = {
                {1, 0, 1, 1, 0},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0}
        };
        int size = maxRegionSize(grid2);
        System.out.println("Max region: " + size);
    }

    public static int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int regions = 0;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; //right left up down
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (bfs(i, j, grid, direction, visited)) {
                        regions++;
                    }
                }
            }
        }
        return regions;
    }

    public static boolean bfs(int i, int j, char[][] grid, int[][] directions, boolean[][] visited) {
        java.util.LinkedList<IceCreamParlour.Pair<Integer, Integer>> queue = new java.util.LinkedList<>();
        if (!visited[i][j]) {
            queue.add(new IceCreamParlour.Pair(i, j));
            visited[i][j] = true;
            while (!queue.isEmpty()) {
                IceCreamParlour.Pair<Integer, Integer> cur = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int m = cur.getFirst() + directions[k][0];
                    int n = cur.getSecond() + directions[k][1];
                    if (m >= 0 && n >= 0 && m < grid.length && n < grid[0].length) {
                        if (!visited[m][n] && grid[m][n] == 1) {
                            queue.add(new IceCreamParlour.Pair(m, n));
                            visited[m][n] = true;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    static int maxRegionSize(int[][] grid) {
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, {1,1}, {-1,1}, {1,-1},{-1,-1} };
        int oldVisited = 0;
        int newVisits = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    DFS(i, j, grid, dir, visited);
                    int visitedLen = getVistedLength(visited);
                    newVisits = Math.max(newVisits, visitedLen - oldVisited);
                    oldVisited = visitedLen;
                }
            }
        }
        return newVisits;
    }

    private static int getVistedLength(boolean[][] visited) {
        int vcount = 0;
        for (int i = 0; i<visited.length;i++) {
            for (int j = 0; j < visited[0].length; j++) {
                if (visited[i][j]) {
                    vcount+=1;
                }
            }
        }
        return vcount;
    }

    private static void DFS(int i, int j, int[][] grid, int[][] dir, boolean[][] visited) {
        IceCreamParlour.Pair<Integer, Integer> cur = new IceCreamParlour.Pair(i, j);
        visited[i][j] = true;
        for (int k = 0; k < 8; k++) {
            int m = cur.getFirst() + dir[k][0];
            int n = cur.getSecond() + dir[k][1];
            if (m < grid.length && m >=0 && n < grid[0].length && n >=0) {
                if (!visited[m][n] && grid[m][n] == 1) {
                    visited[m][n] = true;
                    DFS(m, n, grid, dir, visited);
                }
            }
        }
    }
}
