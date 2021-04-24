package datastructures;


import java.util.*;
import java.util.LinkedList;

/**
 * Created by chaitra.kr on 07/07/18.
 */
public class GraphSolution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            Graph graph = new Graph();
            int startNodeId;
            int nodes = scanner.nextInt();
            int edges = scanner.nextInt();
            for (int k = 1; k <= nodes; k++) {
                GraphNode graphNode = new GraphNode(k);
                graphNode.setParentNode(-1);
                graph.getVertices().add(graphNode);
                graph.getLookUpMap().put(k, graphNode);
            }
            for (int j = 0; j < edges; j++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                GraphNode startNode = graph.getLookUpMap().get(start);
                GraphNode endNode = graph.getLookUpMap().get(end);
                startNode.getNeighbours().add(endNode);
                endNode.getNeighbours().add(startNode);
            }
            startNodeId = scanner.nextInt();
            List<Integer> distances = new java.util.LinkedList<>();

            GraphNode startNode = graph.getLookUpMap().get(startNodeId);
            startNode.setVisited(true);

            traverseBFS(startNode);

            for (int j = 1; j <= graph.getVertices().size(); j++) {
                if (j != startNodeId) {
                    GraphNode endNode = graph.getLookUpMap().get(j);
                    if (endNode.isVisited()) {
                        distances.add(tracePath(graph, startNode, endNode));
                    } else {
                        distances.add(-1);
                    }
                }
            }

            for (int k = 0; k < distances.size(); k++) {
                System.out.print(distances.get(k));
                if (k != distances.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static void traverseBFS(GraphNode startNode) {
        java.util.LinkedList<GraphNode> toVisit = new LinkedList<>();
        for (GraphNode graphNode : startNode.getNeighbours()) {
            if (!graphNode.isVisited()) {
                toVisit.add(graphNode);
                graphNode.setMarked(true);
                graphNode.setParentNode(startNode.getId());
            }
        }

        while (!toVisit.isEmpty()) {
            GraphNode graphNode = toVisit.poll();
            graphNode.setVisited(true);
            if (!graphNode.getNeighbours().isEmpty()) {
                for (GraphNode gn : graphNode.getNeighbours()) {
                    if (!gn.isVisited() && !gn.isMarked()) {
                        gn.setParentNode(graphNode.getId());
                        gn.setMarked(true);
                        toVisit.add(gn);
                    }
                }
            }
        }
    }

    private static int tracePath(Graph graph, GraphNode startNode, GraphNode endNode) {
        int distance = 6;
        int parent = endNode.getParentNode();
        while (parent != startNode.getId()) {
           distance += 6;
           endNode = graph.getLookUpMap().get(endNode.getParentNode());
           parent = endNode.getParentNode();
        }
        return distance;
    }


}

class GraphNode {
    Set<GraphNode> adjacencyList;
    boolean isVisited;
    boolean marked;
    int parentNode;
    int id;

    public GraphNode(int id) {
        adjacencyList = new HashSet<>();
        this.id = id;
    }

    public GraphNode getNode(int id) {
        return this;
    }

    public Set<GraphNode> getNeighbours() {
        return adjacencyList;
    }

    public int getId() {
        return id;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public void setParentNode(int parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNode graphNode = (GraphNode) o;
        return id == graphNode.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getParentNode() {
        return parentNode;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}

class Graph {
    private Map<Integer, GraphNode> idToNode;
    private Set<GraphNode> vertices;

    public Graph() {
        vertices = new HashSet<>();
        idToNode = new HashMap<>();
    }

    public Set<GraphNode> getVertices() {
        return vertices;
    }

    public Map<Integer, GraphNode> getLookUpMap() {
        return idToNode;
    }
}
