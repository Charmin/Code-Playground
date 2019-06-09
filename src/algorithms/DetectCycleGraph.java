package algorithms;

import java.util.*;

/**
 * Created by chaitra.kr on 18/11/18.
 */
public class DetectCycleGraph {

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        Map<GraphVertex<Integer>, List<GraphVertex<Integer>>> adjList = graph.getAdjList();
        for (int i = 0; i < 5; i++) {
            GraphVertex<Integer> vertex = new GraphVertex<>(i);
            adjList.put(vertex, new ArrayList<>());
        }
        GraphVertex g1 = new GraphVertex(0);
        GraphVertex g2 = new GraphVertex(1);
        GraphVertex g3 = new GraphVertex(2);
        GraphVertex g4 = new GraphVertex(3);
        GraphVertex g5 = new GraphVertex(4);

        adjList.get(g1).add(new GraphVertex<>(1));
        adjList.get(g1).add(new GraphVertex<>(3));
        adjList.get(g1).add(new GraphVertex<>(2));
        adjList.get(g2).add(new GraphVertex<>(2));
        adjList.get(g2).add(new GraphVertex<>(0));
        adjList.get(g3).add(new GraphVertex<>(0));
        adjList.get(g3).add(new GraphVertex<>(1));
        adjList.get(g4).add(new GraphVertex<>(0));
        adjList.get(g4).add(new GraphVertex<>(4));
        adjList.get(g5).add(new GraphVertex<>(3));

        Set<GraphVertex> visited = new HashSet();
        System.out.println(hasCycle(graph, g1, visited));

    }

    public static boolean hasCycle(Graph<Integer> graph, GraphVertex startVertex, Set<GraphVertex> visited) {
        Map<GraphVertex<Integer>, List<GraphVertex<Integer>>> adjList = graph.getAdjList();
        for (GraphVertex vertex : adjList.get(startVertex)) {
            if (!vertex.equals(startVertex.parent) && visited.contains(vertex)) {
                return true;
            }
            if (!visited.contains(vertex)) {
                visited.add(startVertex);
                if (vertex.parent == null)
                    vertex.setParent(startVertex);
                return hasCycle(graph, vertex, visited);
            }
        }
        return false;
    }
}
