package interviews.amazon;

import java.util.*;

/**
 * Create deep copy of the graph
 */
public class CloneGraph {
    public Node cloneGraph(Node node) {

        if (node == null) {
            return node;
        }
        Set<Integer> cloned = new HashSet<>();
        Map<Integer, Node> cloneMap  = new HashMap<>();
        Node root = buildClone(node, cloneMap, cloned);
        return root;
    }

    private Node buildClone(Node node, Map<Integer, Node> cloneMap, Set<Integer> cloned) {

        Node root = new Node(node.val, new ArrayList<>());
        cloned.add(root.val);
        cloneMap.put(root.val, root);

        for (Node n : node.neighbors) {
            if (!cloned.contains(n.val)) {
                Node c = buildClone(n, cloneMap, cloned);
                root.neighbors.add(c);
            } else {
                Node c = cloneMap.get(n.val);
                root.neighbors.add(c);
            }
        }

        return root;
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}