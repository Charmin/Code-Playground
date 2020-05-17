package interviews.amazon.bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class CoronaMatrix {

    //0 = human
    //1 = corona
    public static void main(String[] args) {
        int[][] city = {
                {0, 1, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0}
        };
        int cols = city[0].length;

        Set<Integer> visited = new HashSet<>();
        //Add positions of all 1s
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < city.length; i++) {
            for (int j = 0; j < city[i].length; j++) {
                int pos = i * cols + j;
                if (city[i][j] == 1) {
                    q.add(new int[]{i, j});
                    visited.add(pos);
                }
            }
        }

        int res = 0;
        int count = q.size();
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!q.isEmpty()) {
            int[] el = q.poll();
            count--;
            for (int[] d : dir) {
                int x = el[0] + d[0];
                int y = el[1] + d[1];
                int posN = x * city[0].length + y;
                if (x < 0 || y < 0 || x >= city.length || y >= city[0].length || visited.contains(posN)) continue;
                if (city[x][y] == 0) {
                    city[x][y] = 1;
                    q.add(new int[]{x, y});
                }
                visited.add(posN);
            }
            if (count == 0 && q.size() > 0) {
                res += 1;
                count = q.size();
            }
        }
        System.out.println(res);
    }
}
