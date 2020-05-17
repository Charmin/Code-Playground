package interviews.microsoft;

import java.util.LinkedList;
import java.util.List;

public class ZigZagTraversal {

    public List<List<Integer>> zigzagLevelOrderOld(TreeNode root) {
        int s = 1;

        List<List<Integer>> list = new LinkedList<>();

        if (root == null) { return list; }

        LinkedList<TreeNode> stack1 = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();

        stack1.push(root);
        LinkedList<TreeNode> cur = stack1;
        LinkedList<TreeNode> next = stack2;

        List<Integer> level  = new LinkedList<>();

        while (!cur.isEmpty()) {
            TreeNode t = cur.pop();
            if (s == 1) { //add left then right
                if (t.left != null) {
                    next.push(t.left);
                }
                if (t.right != null) {
                    next.push(t.right);
                }
            } else { //add right then left
                if (t.right != null) {
                    next.push(t.right);
                }
                if (t.left != null) {
                    next.push(t.left);
                }
            }

            level.add(t.val);
            if (cur.isEmpty()) {
                LinkedList<Integer> copy = new LinkedList<>();
                copy.addAll(level);
                list.add(copy);
                level.clear();
                LinkedList<TreeNode> temp = cur;
                cur = next;
                next = temp;
                s = Math.abs(s-1);
            }
        }

        return list;
    }
}
