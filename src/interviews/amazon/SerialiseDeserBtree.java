package interviews.amazon;

import java.util.LinkedList;
import java.util.Queue;

public class SerialiseDeserBtree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        StringBuilder b = new StringBuilder();
        while (!q.isEmpty()) {
            TreeNode n = q.remove();
            if (n == null) {
                b.append("null,");
                continue;
            }
            b.append(""+n.val+",");
            q.add(n.left);
            q.add(n.right);
        }

        b.deleteCharAt(b.length()-1);
        System.out.print(b.toString());
        return b.toString();
    }

    //Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] sp = data.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(sp[0]));
        q.add(root);
        TreeNode no = root;
        for (int i = 1; i < sp.length; i++) {
            no = q.poll();
            if (!sp[i].equals("null")) {
                no.left = new TreeNode(Integer.parseInt(sp[i]));
                q.add(no.left);
            }
            if (!sp[++i].equals("null")) {
                no.right = new TreeNode(Integer.parseInt(sp[i]));
                q.add(no.right);
            }
        }
        return root;
    }
}
