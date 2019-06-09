package datastructures;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by chaitra.kr on 21/10/18.
 */
public class TreeRelated {

    public static void
    main(String[] args) {
        Tree tree = new Tree(40);
        TreeNode root = tree.root;
        tree.insertLeft(root, 30);
        tree.insertRight(root, 60);
        tree.insertRight(root.getRight(), 70);
        tree.insertLeft(root.getRight(), 50);
        tree.insertRight(root.getLeft(), 35);
        tree.insertLeft(root.getLeft(), 20);

        Tree tree2 = new Tree(50);
        tree2.insertLeft(tree2.root, 30);
        tree2.insertRight(tree2.root, 30);
        tree2.insertRight(tree2.root.getLeft(), 35);
        tree2.insertLeft(tree2.root.getRight(), 35);
        tree2.insertLeft(tree2.root.getLeft().getRight(), 32);
        tree2.insertRight(tree2.root.getRight().getLeft(), 32);

        Tree tree3 = new Tree(40);
        TreeNode root3 = tree3.root;
        tree3.insertLeft(root3, 30);
        tree3.insertRight(root3, 60);
        tree3.insertRight(root3.getRight(), 70);
        tree3.insertLeft(root3.getRight(), 50);
        tree3.insertRight(root3.getLeft(), 35);
        tree3.insertLeft(root3.getLeft(), 20);
        tree3.insertRight(root3.getRight().getRight(), 80);

        TreeLCAResult lca = getLCA(root3, root3.getRight().getRight().getRight(), root3.getRight().getLeft());
        System.out.println("LCA of " + root3.getRight().getRight().getRight().getData() + " ," + root3.getRight().getLeft().getData() + ": " + lca.lca.getData());
        Map<Integer, List<TreeNode>> map = getLevelOrder(root);
//        for (Map.Entry<Integer, List<TreeNode>> m : map.entrySet()) {
//            System.out.print(m.getKey() + " ");
//            m.getValue().stream().forEach(v -> System.out.print(v.getData() + " "));
//            System.out.println();
//        }
        System.out.println("Second Way:");
        List<List<Integer>> levelOrdeList = getLevelOrder1(root);
        for (List<Integer> list : levelOrdeList) {
            list.stream().forEach(s -> System.out.print(s + " "));
            System.out.println();
        }

        System.out.println("Is Balanced:" + checkIfBalanced(root));
        System.out.println("Symmetric:" + isSymmetric(tree2.root));
    }

    private static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.getRight() != null && root.getLeft() != null) {
            return isMirror(root.getLeft(), root.getRight());
        }
        return false;
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.getData() == right.getData() &&
                    isMirror(left.getLeft(), right.getRight()) &&
                    isMirror(left.getRight(), right.getLeft());
        } else {
            return false;
        }
    }


    public static Map<Integer, List<TreeNode>> getLevelOrder(TreeNode treeNode) {
        Map<Integer, List<TreeNode>> levelMap = new HashMap<>();
        java.util.LinkedList<LevelNode> queue = new LinkedList<>();
        LevelNode cur = new LevelNode(treeNode, 0);
        queue.add(cur);
        while (!queue.isEmpty()) {
            LevelNode node = queue.remove();
            levelMap.computeIfAbsent(node.getLevel(), l -> new ArrayList<>()).add(node.getTreeNode());
            if (node.getTreeNode().getLeft() != null || node.getTreeNode().getRight() != null) {
                int level = node.getLevel() + 1;
                if (node.getTreeNode().getLeft() != null)
                    queue.add(new LevelNode(node.getTreeNode().getLeft(), level));
                if (node.getTreeNode().getRight() != null) {
                    queue.add(new LevelNode(node.getTreeNode().getRight(), level));
                }
            }
        }
        return levelMap;
    }

    public static List<List<Integer>> getLevelOrder1(TreeNode treeNode) {
        List<List<Integer>> levelOrderList = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        java.util.LinkedList<TreeNode> queueToProcess = new LinkedList<>();
        queueToProcess.add(treeNode);
        int size = queueToProcess.size();
        int count = size;
        while (count > 0) {
            TreeNode node = queueToProcess.remove();
            count--;
            levelList.add(node.getData());
            if (node.getLeft() != null) {
                queueToProcess.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queueToProcess.add(node.getRight());
            }
            if (count == 0) { // reached the end of this level
                levelOrderList.add(levelList);
                levelList = new ArrayList<>();
                count = queueToProcess.size();
            }
        }
        return levelOrderList;
    }

    public static boolean checkIfBalanced(TreeNode node) {
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        if (Math.abs(leftHeight - rightHeight) <= 1) {
            return true;
        }
        return false;
    }

    private static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    public static TreeLCAResult getLCA(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) {
            return new TreeLCAResult(0, null);
        }

        TreeLCAResult left = getLCA(root.getLeft(), node1, node2);
        TreeLCAResult right = getLCA(root.getRight(), node1, node2);

        int numNodes = left.numNodes + right.numNodes;
        if (root.getData() == node1.getData()) {
            numNodes += 1;
        }
        if (root.getData() == node2.getData()) {
            numNodes += 1;
        }

        if (numNodes == 2) {
            if (left.lca == null && right.lca == null) {
                return new TreeLCAResult(numNodes, root);
            } else if (left.lca != null) {
                return left;
            } else {
                return right;
            }
        } else {
            return new TreeLCAResult(numNodes, null);
        }
    }


}

class LevelNode {
    TreeNode treeNode;
    int level;

    public LevelNode(TreeNode treeNode, int level) {
        this.treeNode = treeNode;
        this.level = level;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

class TreeLCAResult {
    int numNodes;
    TreeNode lca;

    public TreeLCAResult(int numNodes, TreeNode lca) {
        this.numNodes = numNodes;
        this.lca = lca;
    }
}
