package interviews.amazon;

import java.util.*;

public class CutOffTrees {

    public static void main(String[] args) {
        int[][] pg = {
                {1, 2, 3},
                {0, 0, 4},
                {7, 6, 5}
        };
        System.out.println(cutOffTree(pg));
    }


    public static int cutOffTree(int[][] farr) {
        PriorityQueue<int[]> trees = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < farr.length; i++) {
            for (int j = 0; j < farr[i].length; j++) {
                if (farr[i][j] > 1) {  //If the point is neither plain nor an obstacle
                    int[] pos = {i, j, farr[i][j]};
                    trees.add(pos);
                }
            }
        }

        int total = 0;
        int[] src = {0, 0, Integer.MAX_VALUE};
        int sx = src[0];
        int sy = src[1];

        while (!trees.isEmpty()) {
            int[] tgs = trees.remove();
            int dis = bfs(sx, sy, tgs[0], tgs[1], farr);
            if (dis == -1) return -1;
            total += dis;
            sx = tgs[0];
            sy = tgs[1];
        }
        return total;
    }

    private static int bfs(int x, int y, int tx, int ty, int[][] farr) {
        int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        LinkedList<int[]> q = new LinkedList<>();

        q.add(new int[]{x, y, 0});
        Set<Integer> visited = new HashSet<>();
        visited.add(x * farr[0].length + y);

        while (!q.isEmpty()) {
            int[] nex = q.poll();
            if (nex[0] == tx && nex[1] == ty) {
                return nex[2];
            }
            for (int[] d : dirs) {
                int m = nex[0] + d[0];
                int n = nex[1] + d[1];
                int code = m * farr[0].length + n;
                if (m < 0 || n < 0 || m > farr.length - 1 || n > farr[0].length - 1 || farr[m][n] == 0 || visited.contains(code))
                    continue;
                q.add(new int[]{m, n, nex[2] + 1});
                visited.add(code);
            }
        }
        return -1;
    }

}
