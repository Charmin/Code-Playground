package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chaitra.kr on 20/10/18.
 */
public class BSTTreesSortStack {

    public static void main(String[] args) {
        Tree tree = new Tree(43);
        TreeNode root = tree.root;
        tree.insertLeft(root, 23);
        tree.insertRight(root, 47);
        tree.insertRight(root.getRight(), 53);
        TreeNode rootLeft = root.getLeft();
        tree.insertRight(root.getLeft(), 37);
        tree.insertRight(root.getLeft().getRight(), 41);
        tree.insertLeft(rootLeft.getRight(), 29);
        tree.insertRight(rootLeft.getRight().getLeft(), 31);
        List<TreeNode> sorts = getSorted(root);
        sorts.stream().forEach(s -> System.out.println(s.data));
    }

    public static List<TreeNode> getBSTTreeInSortOrder(TreeNode node) {
        List<TreeNode> sorted = new ArrayList<>();
        java.util.LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        int count = 0;
        TreeNode cur = stack.peek();
        while (stack.peekFirst() != node && count != 0) {
            cur = stack.peek();
            if (cur.getLeft() != null) {
                cur = cur.getLeft();
                stack.push(cur);
            } else {
                TreeNode n = stack.pop();
                sorted.add(n);
                if (cur.getRight() != null) {
                    cur = cur.getRight();
                    stack.push(cur);
                }
            }
        }
        return sorted;
    }

    public static List<TreeNode> getSorted(TreeNode node) {
        List<TreeNode> sorted = new ArrayList<>();
        java.util.LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = node;
        while (stack.peekFirst() != null || cur != null) {
            stack.push(cur);
            if (cur.getLeft() != null && !sorted.contains(cur.getLeft())) {
                cur = cur.getLeft();
            } else {
                TreeNode n = stack.pop(); //Add the element to stack
                if (n!=node) sorted.add(n);
                if (cur.getRight() != null && !sorted.contains(cur.getRight())) {
                    cur = cur.getRight(); //If there is an element in the right side, that element is the next cur
                } else {
                    if (stack.peek()!=null) {
                        cur = stack.pop(); //Else pop again, as this element is the leaf
                    } else
                        cur = null;
                }
            }
        }
        return sorted;
    }
}
