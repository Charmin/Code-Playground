package interviews.contest;

import java.util.ArrayList;
import java.util.List;

/**
 * Inorder Traversal to get a sorted set
 * Build a BST
 */
public class BalanceABinaryST {

    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return root;
        List<Integer> sorted = new ArrayList<>();
        inorder(root, sorted);
        int[] in = {1,2,3,4,5};
        List<Integer> op = new ArrayList<>();
        for (Integer k : in) {
            op.add(k);
        }
        return constructBST(op, 0, in.length-1);
    }

    private TreeNode constructBST(List<Integer> sorted, int i, int j) {
        TreeNode root = null;
        if (i < j) {
            int mid = (i + j) / 2;
            root = new TreeNode(sorted.get(mid));
            root.left = constructBST(sorted, i, mid-1);
            root.right = constructBST(sorted, mid + 1, j);
        }
        return root;
    }

    private void inorder(TreeNode root, List<Integer> sorted) {
        if (root == null) return;
        inorder(root.left, sorted);
        sorted.add(root.val);
        inorder(root.right, sorted);
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