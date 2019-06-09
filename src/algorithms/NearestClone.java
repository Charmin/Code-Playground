package algorithms;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by chaitra.kr on 31/12/18.
 */
public class NearestClone {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        int[][] rows = new int[e][2];
        Graff graff = new Graff(v);

        for (int i = 0; i < e; i++) {
            int et = scanner.nextInt();
            int ot = scanner.nextInt();
            rows[i][0] = et;
            rows[i][1] = ot;
        }

        List<Integer> colors = new ArrayList<>();
        for (int j = 0; j < v; j++) {
            colors.add(scanner.nextInt());
        }


        int targetColor = scanner.nextInt();

        for (int i = 0; i < rows.length; i++) {
            int val = rows[i][0] - 1;
            int val2 = rows[i][1] - 1;
            Node en = new Node(val, colors.get(val));
            Node on = new Node(val2, colors.get(val2));
            graff.addEdge(en, on, 1);
            graff.vertexSet.add(en);
            graff.vertexSet.add(on);
        }

        Map<Integer, Integer> sourceToDest = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        for (Node node : graff.vertexSet) {
           Pair<Integer, Integer> valToDistance;
           Set<Integer> visited = new HashSet<>();
            if (colors.get(node.val) == targetColor && !sourceToDest.values().contains(node.val)) {
                valToDistance = getShortestPath(graff, node, visited);
                if (valToDistance!=null && valToDistance.getValue() < minDistance) {
                    minDistance = valToDistance.getValue();
                    sourceToDest.put(node.val, valToDistance.getKey());
                }
            }
        }

        System.out.println("Min Distance " + minDistance);
    }

    private static Pair<Integer, Integer> getShortestPath(Graff graff, Node startNode, Set<Integer> visited) {
        LinkedList<Node> queues = new LinkedList<>();
        Pair<Integer,Integer> valueToDistance = null;
        int pathLength = 1;
        int minDistance = Integer.MAX_VALUE;
        LinkedList<GEdge> neighbours = graff.adjList[startNode.val];
        for (GEdge edge : neighbours) {
            Node other = edge.getOther(startNode, 1);
            queues.offer(other);
        }
        int count = queues.size();
        visited.add(startNode.val);

        while (!queues.isEmpty()) {
            Node n = queues.remove();
            visited.add(n.val);
            count--;
            if (n.color == startNode.color && n.val != startNode.val) {
                if (pathLength < minDistance) {
                    minDistance = pathLength;
                    valueToDistance = new Pair(n.val, minDistance);
                }
            }

            LinkedList<GEdge> neighbourList = graff.adjList[n.val];
            for (GEdge edge : neighbourList) {
                Node other = edge.getOther(n, 1);
                if (!visited.contains(other.val)) {
                    queues.offer(other);
                }
            }

            if (count == 0) {
                count = queues.size();
                pathLength++; //crossed one level
            }
        }
        return valueToDistance;
    }
}


class Graff {

    int vertices;
    Set<Node> vertexSet;
    LinkedList<GEdge>[] adjList;

    public Graff(int vertices) {
        this.vertices = vertices;
        this.adjList = new LinkedList[vertices];
        vertexSet = new HashSet<>();
    }

    public void addEdge(Node e, Node o, int w) {
        if (adjList[e.val] == null) {
            adjList[e.val] = new LinkedList<>();
        }
        if (adjList[o.val] == null) {
            adjList[o.val] = new LinkedList<>();
        }
        GEdge gEdge = new GEdge(e, o, w);
        adjList[e.val].add(gEdge);
        adjList[o.val].add(gEdge);
    }
}

class Node {
    int val;
    int color;

    public Node(int val, int color) {
        this.val = val;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (val != node.val) return false;
        return color == node.color;
    }

    @Override
    public int hashCode() {
        int result = val;
        result = 31 * result + color;
        return result;
    }
}

class GEdge {
    Node either;
    Node other;
    int weight;

    public GEdge(Node either, Node other, int weight) {
        this.either = either;
        this.other = other;
        this.weight = weight;
    }

    public Node getOther(Node either, int w) {
        Node other = null;
        if (this.either.val == either.val && this.weight == w) {
            other = this.other;
        } else if (this.other.val == either.val && this.weight == w) {
            other = this.either;
        }
        return other;
    }
}