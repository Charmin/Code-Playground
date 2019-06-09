package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chaitra.kr on 04/10/17.
 */
class Nodi {
    int data;
    Nodi right;
    Nodi left;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Nodi getRight() {
        return right;
    }

    public void setRight(Nodi right) {
        this.right = right;
    }

    public Nodi getLeft() {
        return left;
    }

    public void setLeft(Nodi l) {
        this.left = l;
    }
}

class BST {

    Nodi root;
    public BST() {

    }

    public Nodi getRoot() {
        return root;
    }

    public void insert(int in) {
        boolean left = false;
        boolean right = false;
        if (root == null) {
            root = new Nodi();
            root.setData(in);
            root.setLeft(null);
            root.setRight(null);
        } else {
            Nodi temp = new Nodi();
            temp.setLeft(null);
            temp.setRight(null);
            temp.setData(in);
            Nodi p = root;
            Nodi pre = p;
            while (p != null) {
                if (temp.getData() > p.getData()) {
                    pre = p;
                    right = true;
                    left = false;
                    p = p.getRight();
                } else {
                    pre = p;
                    left = true;
                    right = false;
                    p = p.getLeft();
                }
            }

            if (right) pre.setRight(temp);
            if (left) pre.setLeft(temp);

        }
    }

    public void display(Nodi root) {
        if(root == null)
            return;
        System.out.print(" "+root.getData());
        display(root.getLeft());
        display(root.getRight());
    }

    public int getRightHeight(Nodi root) {
        if(root == null) {
            return 0;
        }
        int rightHeight = 1 + getRightHeight(root.getRight());
        return rightHeight;
    }

    public int getLeftHeight(Nodi root) {
        if(root == null) {
            return 0;
        }
        int lHeight = 1 + getLeftHeight(root.getLeft());
        return lHeight;
    }

    public int getHeight(Nodi root) {
        if(root == null) {
            return 0;
        }
        int rightHeight = getHeight(root.getRight());
        int leftHeight =  getHeight(root.getLeft());

        if(rightHeight > leftHeight) {
            return 1+rightHeight;
        } else {
            return 1+leftHeight;
        }
    }
}

public class BSTApp {
    static List<Integer> inOrderList = new ArrayList<>();
    static BST bstGlobal = new BST();
    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert(5);
        bst.insert(6);
        bst.insert(3);
        bst.insert(2);
        bst.insert(4);
        bst.insert(1);

        bst.display(bst.getRoot());

        int rh = bst.getRightHeight(bst.getRoot());
        int lh = bst.getLeftHeight(bst.getRoot());

        int height1 = rh > lh ? rh : lh;
        int height = bst.getHeight(bst.getRoot());

        System.out.println("Height : "+height);
        //Nodi newRoot =
        convertToBalancedTree(bst.getRoot());

    }

    private static void convertToBalancedTree(Nodi root) {
        getInOrderList(root);
        int sizeOfList = inOrderList.size();
        int medianIndex = sizeOfList/2;
        List<Integer> inList = new ArrayList<>();
        Integer[] inOrderArray = inOrderList.toArray(new Integer[0]);
        bstGlobal.root = getBST(inOrderArray);
        bstGlobal.display(bstGlobal.getRoot());

    }

    private static void getInOrderList(Nodi root) {
        if(root == null) return;
        getInOrderList(root.getLeft());
        inOrderList.add(root.data);
        getInOrderList(root.getRight());
    }

    public static Nodi getBST(Integer[] inOrderArray) {
        if(inOrderArray.length < 1 ) {
            return bstGlobal.getRoot();
        }
        int indexOMid = inOrderArray.length/2;
        bstGlobal.insert(inOrderArray[indexOMid]);
        Integer[] leftArray = Arrays.copyOfRange(inOrderArray, 0, indexOMid);
        Integer[] rightArray = Arrays.copyOfRange(inOrderArray, indexOMid+1, inOrderArray.length);
        getBST(leftArray);
        getBST(rightArray);
        return bstGlobal.getRoot();
    }

    private static int getMidIndex(int length) {
        int mid = (length % 2 == 0 ? (length/2)-1 : length/2);
        return mid;
    }
}

