package datastructures;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chaitra.kr on 07/04/19.
 */
public class SumToBin {

    static int sum = 0;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);

        root.left.left  = new TreeNode(0);
        root.left.right  = new TreeNode(1);
        root.right.left  = new TreeNode(0);
        root.right.right = new TreeNode(1);
        int s = sumRootToLeaf(root);
        System.out.println(s);
        String p = "ABDB";
        System.out.println(p.indexOf('B'));

    }

    static int mod = 1000000007;
//
//    public static int sumRootToLeaf(TreeNode root) {
//        return dfs(root, 0);
//    }
//
//    public static int dfs(TreeNode root, int val) {
//        if (root == null) return 0;
//        val = (val * 2 + root.val) % mod;
//        return (root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val)) % mod;
//    }

    public static int sumRootToLeaf(TreeNode root) {
        helper(root,0);
        return sum;
    }

    public static void helper(TreeNode root,int curr){
        if(root == null){
            return;
        }
        curr = (curr << 1) | root.val;
        if(root.left==null && root.right==null){
            sum += curr;
        }

        if(root.left != null)
            helper(root.left,curr);
        if(root.right != null)
            helper(root.right,curr);
    }

    static class TreeNode {
        int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }

    }

}

