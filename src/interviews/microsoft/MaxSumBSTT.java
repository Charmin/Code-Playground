package interviews.microsoft;


/**
 * Given a binary tree root, the task is to return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 */
public class MaxSumBSTT {

    int maxVal = 0;
    //Post order traversal to check if the
    public int maxSumBST(TreeNode root) {
        if (root == null) return 0;

        Info res = postOrder(root);
        return maxVal;
    }

    public Info postOrder(TreeNode root) {
        if (root == null) {
            return new Info(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        Info l = postOrder(root.left);
        Info r = postOrder(root.right);

        Info res = new Info();
        if (l.isBST && r.isBST &&  (root.left == null || root.val > l.max) && (root.right == null || root.val < r.min)) {
            int lsum = root.left != null ? l.sum : 0;
            int rum = root.right != null ? r.sum : 0;
            int sum = root.val + lsum + rum;
            int min = Math.min(root.val, l.min);
            int max = Math.max(root.val, r.max);
            maxVal = Math.max(maxVal, sum);
            res = new Info(true, min, max, sum);
        }

        return res;
    }
}

class Info {

    boolean isBST;
    int min;
    int max;
    int sum;
    public Info(boolean bo, int a, int b, int c) {
        this.isBST = bo;
        this.min = a;
        this.max = b;
        this.sum = c;
    }

    public Info() {}
}
