package interviews.microsoft;

import java.util.LinkedList;

public class PathSum {

    public static void main(String[] args) {

    }

    private static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return (root.val == sum);
        }
        LinkedList<Integer> l = new LinkedList<>();
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);

    }
}
