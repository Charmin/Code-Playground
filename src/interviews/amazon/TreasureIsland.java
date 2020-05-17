package interviews.amazon;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous reefs.
 * Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to the treasure island.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters.
 * You must start from the top-left corner of the map and can move one block up, down, left or right at a time.
 * The treasure island is marked as X in a block of the matrix. X will not be at the top-left corner.
 * Any block with dangerous rocks or reefs will be marked as D.
 * You must not enter dangerous blocks.
 * You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe.
 * Output the minimum number of steps to get to the treasure.
 */
public class TreasureIsland {

    public static void main(String[] args) {
        char[][] input = {
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}};

        int p = findPath(input);
        System.out.println(p);

    }

    private String getType(String log) {
        return log.split("\\W+")[0].contains("dig") ? "dig" : "let";
    }

    public static int findPath(char[][] land) {
        CNode start = new CNode(new int[]{0, 0}, null);
        Queue<CNode> q = new ArrayDeque<>();
        q.add(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        int path = -1;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            CNode el = q.poll();
            if (land[el.val[0]][el.val[1]] == 'X') {
                path = getPath(el);
                break;
            }
            for (int[] d : dir) {
                int x = el.val[0] + d[0];
                int y = el.val[1] + d[1];
                int code = x * (land[0].length) + y;
                if (x < 0 || y < 0 || x >= land.length || y >= land[0].length || visited.contains(code)) continue;
                if (land[x][y] != 'D') {
                    CNode e = new CNode(new int[]{x, y}, el);
                    q.add(e);
                }
                visited.add(code);
            }
        }
        return path;
    }

    private static int getPath(CNode el) {
        int p = 0;
        while (el.parent != null) {
            el = el.parent;
            p++;
        }
        return p;
    }
}

class CNode {
    int[] val;
    CNode parent;

    public CNode(int[] v, CNode p) {
        this.val = v;
        this.parent = p;
    }
}