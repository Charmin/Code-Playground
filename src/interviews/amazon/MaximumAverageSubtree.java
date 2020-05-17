package interviews.amazon;

import java.util.*;

/*
* 	     20
	   /   \
	 12     18
  /  | \   / \
11   2  3 15  8

*/

//Not the correct verified solution
public class MaximumAverageSubtree {

    public static void main(String[] args) {
        TNode root = new TNode(20);
        root.addChild(12);
        root.addChild(18);
        root.children.get(0).addChild(11);
        root.children.get(0).addChild(2);
        root.children.get(0).addChild(3);
        root.children.get(1).addChild(15);
        root.children.get(1).addChild(8);
        TNode res = null;
        int avg = Integer.MIN_VALUE;
        Map<TNode, Res> avgMap = new HashMap<>();
        getNodeAvg(root, avgMap);
        for (Map.Entry<TNode, Res> m : avgMap.entrySet()) {
            int avg1 = m.getValue().sum / m.getValue().nodesCount;
            if (avg1 > avg) {
                avg = avg1;
                res = m.getKey();
            }
        }
        System.out.println("Max root: " + res.val);
    }

    public static Map<TNode, Res> getNodeAvg(TNode root, Map<TNode, Res> avg) {
        Map<TNode, Res> map = new HashMap<>();
        if (root == null) {
            map.put(root, new Res(0,0));
            return map;
        }

        Res res = new Res(root.val, 1);
        if (root.children.size() == 0) {
            res = new Res(root.val, 1);
            map.put(root, res);
            return map;
        }

        for (TNode c : root.children) {
            map = getNodeAvg(c, avg);
            res.nodesCount += map.get(c).nodesCount;
            res.sum += map.get(c).sum;
        }
        avg.put(root, res);
        return avg;
    }

}

class TNode {
    int val;
    List<TNode> children;

    public TNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }

    public void addChild(int c) {
        TNode ch = new TNode(c);
        children.add(ch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TNode tNode = (TNode) o;
        return val == tNode.val &&
                children.equals(tNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, children);
    }
}

class Res {
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getNodesCount() {
        return nodesCount;
    }

    public void setNodesCount(int nodesCount) {
        this.nodesCount = nodesCount;
    }

    int sum;
    int nodesCount;

    public Res(int s, int n) {
        sum = s;
        nodesCount = n;
    }


}