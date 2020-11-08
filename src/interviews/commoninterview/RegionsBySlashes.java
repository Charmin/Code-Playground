package interviews.commoninterview;

import java.util.HashSet;
import java.util.Set;

public class RegionsBySlashes {

    public static void main(String[] args) {
        String[] grid = {" /", "/ "};
        int size = regionsBySlashes(grid);
        System.out.println(size);
    }


    public static int regionsBySlashes(String[] grid) {
        int N = grid.length;
        if (grid.length == 0) return 0;
        int c = grid[0].length();

        DSU uf = new DSU(N * N * 4);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                int root = (i * N + j) * 4;
                if (grid[i].charAt(j) == '/') {
                    uf.union(root, root + 1);
                    uf.union(root + 2, root + 3);
                }

                if (grid[i].charAt(j) == '\\') {
                    uf.union(root, root + 2);
                    uf.union(root + 1, root + 3);
                }

                if (grid[i].charAt(j) == ' ') {
                    uf.union(root, root + 2);
                    uf.union(root, root + 1);
                    uf.union(root + 1, root + 2);
                    uf.union(root + 2, root + 3);
                }

                if (j + 1 < c) {
                    uf.union(root + 2, (root + 4) + 1); //left right
                }
                if (j - 1 >= 0) {
                    uf.union(root + 1, (root - 4) + 2);
                }

                if (i + 1 < N) {
                    uf.union(root + 3, (root + 4 * N));
                }
                if (i - 1 >= 0) {
                    uf.union(root, (root - 4 * N) + 3);
                }
            }
        }

        //traverse the union find DS to count the connected components

        Set<Integer> comps = new HashSet<>();
        int[] p = uf.parent;

        for (int b : p) {
            comps.add(uf.find(b));
        }
        return comps.size();
    }
}

class DSU {

    int[] parent;

    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int a, int b) {
        int i = find(a);
        int j = find(b);
        parent[i] = j;
    }

    public int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        int p = find(parent[a]);
        parent[a] = p;
        return p;
    }
}

class Item {
    int rank;

    public Item(int r) {
        this.rank = r;
    }
}
