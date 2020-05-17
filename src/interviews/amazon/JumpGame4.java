package interviews.amazon;

import java.util.*;

public class JumpGame4 {

    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{25, -51, 61, -74, -51, -30, 58, 36, 68, -80, -64, 25, -30, -53, 36, -74, 61, -100, -30, -52}));
    }

    public static int minJumps(int[] arr) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Set<Integer> valSet = new HashSet<>();
        Map<Integer, Set<Integer>> vals = new HashMap<>();
        //construct the adjecency list
        for (int i = 0; i < arr.length; i++) {
            adj.computeIfAbsent(arr[i], p -> new ArrayList<>()).add(i);
        }
        //bfs on the list from src(0) to target(n-1)
        return bfs(adj, arr);
    }

    private static int bfs(Map<Integer, List<Integer>> adj, int[] arr) {
        Set<Integer> visited = new HashSet<>();
        int end = arr.length - 1;
        visited.add(0);
        int dep = -1;
        Queue<int[]> q = new ArrayDeque<>();
        int[] elem = new int[]{0, 0};
        q.add(elem);

        while (!q.isEmpty()) {
            int[] e = q.poll();
            if (e[0] == end) {
                return e[1];
            }

            if (e[0] + 1 < arr.length && !visited.contains(e[0] + 1)) {
                visited.add(e[0] + 1);
                q.add(new int[]{e[0] + 1, e[1] + 1});
            }

            if (e[0] - 1 >= 0 && !visited.contains(e[0] - 1)) {
                visited.add(e[0] - 1);
                q.add(new int[]{e[0] - 1, e[1] + 1});
            }

            for (int a : adj.get(arr[e[0]])) {
                if (!visited.contains(a)) {
                    visited.add(a);
                    q.add(new int[]{a, e[1] + 1});
                }
            }
        }

        return dep;
    }
}
