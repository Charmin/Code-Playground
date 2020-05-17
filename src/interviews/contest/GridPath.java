package interviews.contest;

import java.util.HashSet;
import java.util.Set;

public class GridPath {

    public static void main(String[] args) {
        int[][] grid = {{4, 1}, {6, 1}};
        System.out.println(hasValidPath(grid));
        System.out.println("done");
    }

    public static boolean hasValidPath(int[][] grid) {
        if (grid.length == 0) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        return dfs(grid, 0, 0, grid.length - 1, grid[0].length - 1, visited);
    }

    //find a path from x,y to x1 y1
    private static boolean dfs(int[][] grid, int x, int y, int x1, int y1, Set<Integer> visited) {
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        if (x == x1 && y == y1) {
            return true;
        }
        int a;
        int b;
        boolean res = false;
        switch (grid[x][y]) {
            case 1:
                for (int[] d : dir) {
                    if (d[0] == 0 && d[1] == -1) { //left
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 5 || grid[a][b] == 3) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    } else if (d[0] == 0 && d[1] == 1) { //right
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 6 || grid[a][b] == 4) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    }
                    if (res) return true;
                }
            case 2:
                for (int[] d : dir) {
                    if (d[0] == -1 && d[1] == 0) { //up down
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 4 || grid[a][b] == 3) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    } else if (d[0] == 1 && d[1] == 0) {
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 6 || grid[a][b] == 5) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    }
                    if (res) return true;
                }
            case 3:
                for (int[] d : dir) {
                    if (d[0] == 0 && d[1] == -1) { //left down
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 4 || grid[a][b] == 6 || grid[a][b] == 1) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    } else if (d[0] == 1 && d[1] == 0) {
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 5 || grid[a][b] == 6 || grid[a][b] == 2) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    }
                    if (res) return true;
                }
            case 4:
                for (int[] d : dir) {
                    if (d[0] == 0 && d[1] == 1) { //right down
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 1 || grid[a][b] == 3 || grid[a][b] == 5) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    } else if (d[0] == 1 && d[1] == 0) {
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 2 || grid[a][b] == 5 || grid[a][b] == 6) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    }
                    if (res) return true;
                }
            case 5:
                for (int[] d : dir) {
                    if (d[0] == -1 && d[1] == 0) { //up left
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 2 || grid[a][b] == 3 || grid[a][b] == 4) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    } else if (d[0] == 0 && d[1] == -1) {
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 1 || grid[a][b] == 4 || grid[a][b] == 6) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    }
                    if (res) return true;
                }
            case 6:
                for (int[] d : dir) {
                    if (d[0] == -1 && d[1] == 0) { //up right
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 2 || grid[a][b] == 3 || grid[a][b] == 4) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    } else if (d[0] == 0 && d[1] == 1) {
                        a = x + d[0];
                        b = y + d[1];
                        int code = a * grid[0].length + b;
                        if (a >= 0 && b >= 0 && a < grid.length && b < grid[0].length && !visited.contains(code)) {
                            if (grid[a][b] == 1 || grid[a][b] == 3 || grid[a][b] == 5) {
                                visited.add(code);
                                res = dfs(grid, a, b, x1, y1, visited);
                            }
                        }
                    }
                    if (res) return true;
                }
        }
        return res;
    }
}
