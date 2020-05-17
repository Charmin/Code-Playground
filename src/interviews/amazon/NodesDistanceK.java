package interviews.amazon;

import java.util.*;

public class NodesDistanceK {

    public static void main(String[] args) {
        int[] root = new int[] {3,5,1,6,2,0,8,-1,-1,7,4};
        TreeNode r = buildTree(0, 3, root);
        TreeNode target = new TreeNode(5);
        System.out.println(distanceK(r, target, 2));
        int K = 2;
    }

    private static TreeNode buildTree(int j, int val,  int[] root) {
        TreeNode ot = null;
        if (val == -1) {
          return ot;
        }
        ot = new TreeNode(val);

        if (2*j + 1 < root.length) {
            ot.left = buildTree(2 * j + 1, root[2 * j + 1], root);
        }
        if (2*j + 2 < root.length) {
            ot.right = buildTree(2 * j + 2, root[2 * j + 2], root);
        }
        return ot;
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null) return null;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        getParentMap(root, null, parentMap);

        return bfsFromTarget(target, parentMap, K);
    }


    private static void getParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> map) {
        if (node == null) {
            return;
        }
        map.put(node, parent);
        getParentMap(node.left, node, map);
        getParentMap(node.right, node, map);
    }

    private static List<Integer> bfsFromTarget(TreeNode target, Map<TreeNode, TreeNode> map, int k) {
        List<Integer> res = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        Queue<QNode> ch = new ArrayDeque<>();
        ch.offer(new QNode(target, 0));

        while (!ch.isEmpty()) {
            QNode t = ch.poll();
            if (t.d == k) {
                res.add(t.t.val);
            }

            if (t.t.left != null && !visited.contains(t.t.left.val) && t.d <= k) {
                ch.offer(new QNode(t.t.left, t.d + 1));
                visited.add(t.t.left.val);
            }

            if (t.t.right != null && !visited.contains(t.t.right.val) && t.d <= k) {
                ch.offer(new QNode(t.t.right, t.d + 1));
                visited.add(t.t.right.val);
            }

            if (map.get(t.t) != null && !visited.contains(map.get(t.t).val) && t.d <= k) {
                visited.add(map.get(t.t).val);
                ch.offer(new QNode(map.get(t.t), t.d + 1));
            }
        }

        return res;
    }
}

class QNode {
    TreeNode t;
    int d;

    public QNode(TreeNode t, int g) {
        this.t = t;
        this.d = g;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}