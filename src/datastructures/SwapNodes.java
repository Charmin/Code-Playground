package datastructures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 01/05/18.
 */
public class SwapNodes {

    public static List<Integer> treeList = new ArrayList<>();

    static int[][] swapNodes(int[][] indexes, int[] queries) {
        /*
         * Write your code here.
         */

        treeList.add(1);
        for (int i = 0; i < indexes.length; i++) {
            treeList.add(indexes[i][0]);
            treeList.add(indexes[i][1]);
        }

        List<Integer> inOrder = getInOrderTree(treeList);
        return null;
    }

    private static List<Integer> getInOrderTree(List<Integer> treeList) {
        return null;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

class Tree {
    TreeNode root;

    public Tree(int root) {
        this.root = new TreeNode(root);
        this.root.setLeft(null);
        this.root.setRight(null);
    }

    public Tree() {
        root = new TreeNode(1);
        root.setLeft(null);
        root.setRight(null);
    }

    public TreeNode insertLeft(TreeNode root, int left) {
        TreeNode leftTreeNode = new TreeNode(left);
        root.setLeft(leftTreeNode);
        return root;
    }

    public TreeNode insertRight(TreeNode root, int right) {
        TreeNode rightTreeNode = new TreeNode(right);
        root.setRight(rightTreeNode);
        return root;
    }

    public void insert(int index) {

        if(index > SwapNodes.treeList.size()-1) {

        }
        TreeNode treeNode = new TreeNode(SwapNodes.treeList.get(index));
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
