package interviews.microsoft;

//543. Diameter of Binary Tree
public class MaxPathInTree {

    public static void main(String[] args) {

    }

    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getPath(root);
        return Math.max(0, res-1); //res represents the vertices
    }

    private int getPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftLen = 1 + getPath(root.left);
        int rightLen = 1 + getPath(root.right);

        res = Math.max(res, leftLen + rightLen - 1); // 1 is counted twice
        return Math.max(leftLen, rightLen);
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}