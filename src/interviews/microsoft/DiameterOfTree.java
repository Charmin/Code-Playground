package interviews.microsoft;

import java.util.*;

/**
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
 *
 * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.
 * Each node has labels in the set {0, 1, …, edges.length}.
 */
public class DiameterOfTree {

    static int res = 0;

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}};
        int[][] ed = {{0, 1}, {1, 2}, {2, 3}, {1, 4}, {4, 5}};

        getDiameter(ed);
    }

    private static void getDiameter(int[][] edges) {
        Set<Integer> vertices = new HashSet<>();
        List<int[]> edgeList = new ArrayList<>();
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] e : edges) {
            vertices.add(e[0]);
            vertices.add(e[1]);
            edgeList.add(new int[]{e[0], e[1]});
            edgeList.add(new int[]{e[1], e[0]});
        }
        for (int[] e : edgeList) {
            adj.computeIfAbsent(e[0], l -> new ArrayList<>()).add(e);
        }

        Set<Integer> visited = new HashSet<>();

        for (Integer v : vertices) {
            dfs(adj, visited, v, 0);
        }
        System.out.println(res);
    }

    private static void dfs(Map<Integer, List<int[]>> adj, Set<Integer> visited, int v, int dist) {
        List<int[]> path = adj.get(v);

        for (int[] ed : path) {
            if (!visited.contains(ed[1])) {
                visited.add(v);
                int edgeCount = dist + 1;
                res = Math.max(res, edgeCount);
                dfs(adj, visited, ed[1], edgeCount);
                visited.remove(v);
            }
        }
    }

    /**
     * If your graph is implemented using adjacency lists, wherein each node maintains a list of all its adjacent edges, then,
     * for each node, you could discover all its neighbors by traversing its adjacency list just once in linear time.
     * For a directed graph, the sum of the sizes of the adjacency lists of all the nodes is E (total number of edges).
     * So, the complexity of DFS is O(V) + O(E) = O(V + E).
     *
     *
     */

}
