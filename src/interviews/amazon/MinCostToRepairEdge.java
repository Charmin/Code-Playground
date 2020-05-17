package interviews.amazon;

/**
 * There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken disconnecting the graph.
 * Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.
 */

import java.util.*;

/**
 * Kruskal's algorithm can be shown to run in O(E log E) time, or equivalently, O(E log V) time,
 * where E is the number of edges in the graph and V is the number of vertices, all with simple data structures.
 * Kruskal Algorithm uses disjoint set data structure (DSU/Union Find)
 */
public class MinCostToRepairEdge {

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        int[][] repairs = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};

        int[][] edges1 = {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
        int[][] repair1 = {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};

        int[][] edges2 = {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
        int[][] repair2 = {{1, 6, 410}, {2, 4, 800}};

        System.out.println(getMinCost(edges, repairs, 5));
        System.out.println(getMinCost(edges1, repair1, 6));
        System.out.println(getMinCost(edges2, repair2, 6));
    }

    private static int getMinCost(int[][] edges, int[][] repairs, int n) {

        PriorityQueue<int[]> edgesP = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        Map<String, Integer> graph = new HashMap<>();
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            graph.put(e[0] + "-" + e[1], 0);
            int[] cedge = new int[e.length + 1];
            cedge[0] = e[0];
            cedge[1] = e[1];
            cedge[2] = 0;
        }

        for (int[] r : repairs) {
            graph.put(r[0] + "-" + r[1], r[2]);
            edgesP.offer(r);
        }

        //Make a union of all the connected(unbroken) edges so far
        for (Map.Entry<String, Integer> mp : graph.entrySet()) {
            if (mp.getValue() == 0) {
                int v1 = Integer.valueOf(mp.getKey().split("-")[0]);
                int v2 = Integer.valueOf(mp.getKey().split("-")[1]);
                uf.union(v1, v2);
            }
        }

        int cost = 0;
        //Add the broken edges by minimum cost, until the no of components is 1. This is a case of Kruskal's algo
        while (!edgesP.isEmpty()) {
            int[] e = edgesP.poll();
            if (!uf.connected(e[0], e[1])) {
                uf.union(e[0], e[1]);
                cost += e[2];
            }
        }
        return cost;
    }
}

//DSU by rank and path compression
class UnionFind {
    int[] parent;
    int[] rank;
    int count; //of components


    UnionFind(int vertices) {
        count = vertices;
        parent = new int[vertices + 1];
        for (int i = 1; i <= vertices; i++) {
            parent[i] = i;
        }
        rank = new int[vertices + 1];
    }

    void union(int p, int q) {
        int rootp = find(p);
        int rootq = find(q);
        if (rank[rootp] > rank[rootq]) {
            parent[rootq] = rootp;
        } else if (rank[rootp] < rank[rootq]) {
            parent[p] = rootq;
        } else {
            parent[q] = p;
            rank[p]++;
        }
        count--; //components count decreases after union
    }

    int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; //path compression
            p = find(parent[p]);
        }
        return p;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}