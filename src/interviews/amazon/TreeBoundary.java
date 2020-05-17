package interviews.amazon;

import java.util.*;

//BFS will not work !!
public class TreeBoundary {


}

class Ele {
    TreeNode node;
    int level;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        Set<TreeNode> set = new HashSet<>();

        LinkedList<TreeNode> left = new LinkedList<>();
        LinkedList<TreeNode> right = new LinkedList<>();
        LinkedList<TreeNode> leaves = new LinkedList<>();

        int h = getHeight(root);

        ArrayDeque<Ele> q = new ArrayDeque<>();
        Ele roo = new Ele(root, 0);

        q.add(roo);
        left.add(root);

        int count = q.size();

        while (!q.isEmpty()) {
            Ele item = q.remove();

            count--;
            if (item.node.left != null) {
                q.add(new Ele(item.node.left, item.level+1));
            }
            if (item.node.right != null) {
                q.add(new Ele(item.node.right, item.level+1));
            }

            if (item.level == h) {
                leaves.add(item.node);
            }

            if (count == 0) { //end of a level
                right.add(item.node);
                if (!q.isEmpty()) {
                    left.add(q.peek().node); //start of the q for next level
                }
                count = q.size();
            }
        }

        LinkedList<Integer> result =  new LinkedList<>();

        while (!left.isEmpty()) {
            TreeNode e =  left.poll();
            if(set.add(e)) {
                result.add(e.val);
            }
        }

        while (!leaves.isEmpty()) {
            TreeNode e = leaves.remove();
            if(set.add(e)) {
                result.add(e.val);
            }
        }

        while (!right.isEmpty()) {
            TreeNode e = right.pop();
            if(set.add(e)) {
                result.add(e.val);
            }
        }

        return result;
    }

    private int getHeight(TreeNode r) {
        if (r == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(r.left), getHeight(r.right));
    }

    public Ele(TreeNode n, int l) {
        this.node = n;
        this.level = l;
    }

    public TreeNode get(int v) {
        if(level == v) {
            return node;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ele ele = (Ele) o;
        return level == ele.level &&
                Objects.equals(node, ele.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, level);
    }
}