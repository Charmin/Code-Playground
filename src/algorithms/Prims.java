package algorithms;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by chaitra.kr on 29/12/18.
 */
/* Sample input
*
4 5
1 2 7
1 4 6
4 2 9
4 3 8
2 3 6
* */

public class Prims {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Integer vertexs = scanner.nextInt();
        Map<Integer, Boolean> visitedMap = new HashMap<>();

        Set<Integer> visitedSet = new HashSet<>();
        Map<Integer, Integer> vIndex = new HashMap<>();
        int j = 0;
        Graf graf = new Graf(vertexs);
        Integer edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            if (!vIndex.containsKey(v1)) {
                vIndex.put(v1, j);
                j++;
            }
            if (!vIndex.containsKey(v2)) {
                vIndex.put(v2, j);
                j++;
            }
            int w = scanner.nextInt();
            graf.addEdge(vIndex.get(v1), vIndex.get(v2), w);
            scanner.nextLine();
        }


        Graf primsMST = prims(graf, visitedSet, 1, vIndex);
        LinkedList<Edge>[] edges1 = primsMST.adjList;
        IntStream.range(0, vertexs).boxed().forEach(k -> visitedMap.put(k, false));
        int w = 0;
        for (LinkedList<Edge> edges2 : edges1) {
            for (Edge e : edges2) {
                if (!visitedMap.get(e.getEither()) || !visitedMap.get(e.getOther())) {
                    w = w + e.getWeight();
                    visitedMap.put(e.getEither(), true);
                    visitedMap.put(e.getOther(), true);
                }
            }
        }
        System.out.println(w);

        //less efficient
//        GraphN graphN = new GraphN(vertexs);
//        Map<Integer, Map<Integer, Integer>> adjMap = new HashMap<>();
//        for (int i = 0; i < edges; i++) {
//            int v1 = scanner.nextInt();
//            int v2 = scanner.nextInt();
//            int w = scanner.nextInt();
//            adjMap.computeIfAbsent(v1, k -> new HashMap<>()).put(v2, w);
//            adjMap.computeIfAbsent(v2, k -> new HashMap<>()).put(v1, w);
//            scanner.nextLine();
//        }
//        graphN.setAdjMap(adjMap);
//        int weights = 0;
//        Map<Integer, Map<Integer, Integer>> mst = primsMST(1, graphN, visitedMap);
//        for (Map.Entry<Integer, Map<Integer, Integer>> entry : mst.entrySet()) {
//            for (Map.Entry<Integer, Integer> en : entry.getValue().entrySet()) {
//                System.out.println(entry.getKey() + " ==> " + en.getKey() + "-" + en.getValue());
//                weights += en.getValue();
//            }
//        }
//        System.out.println("Total weights : " + weights);
    }

    private static Graf prims(Graf graf, Set<Integer> visitedSet, int start, Map<Integer, Integer> vIndex) {
        LinkedList<Edge> neighbours = graf.adjList[vIndex.get(start)];
        PriorityQueue<Edge> trailQueue = new PriorityQueue<>(new EdgeComparator());
        trailQueue.addAll(neighbours);
        Graf mst = new Graf(graf.vertices);
        visitedSet.add(vIndex.get(start));
        while (visitedSet.size() != graf.vertices) {
            Edge minEdge = trailQueue.remove();
            LinkedList<Edge> nEdges = new LinkedList<>();
            if (!visitedSet.contains(minEdge.getEither())) {
                nEdges = graf.adjList[minEdge.getEither()];
                visitedSet.add(minEdge.getEither());
                mst.addEdge(minEdge.getEither(), minEdge.getOther(), minEdge.getWeight());
            } else if (!visitedSet.contains(minEdge.getOther())) {
                nEdges = graf.adjList[minEdge.getOther()];
                visitedSet.add(minEdge.getOther());
                mst.addEdge(minEdge.getEither(), minEdge.getOther(), minEdge.getWeight());
            }
            for (Edge edge : nEdges) {
                trailQueue.add(edge);
            }
        }
        return mst;
    }

    private static Map<Integer, Map<Integer, Integer>> primsMST(int start, GraphN graphN, Map<Integer, Boolean> visitedMap) {
        Map<Integer, Map<Integer, Integer>> mst = new HashMap<>();
        int parent = start;
        visitedMap.put(parent, true);
        Map<Integer, Integer> candidates = graphN.getAdjMap().get(parent);
        Map<Integer, Map<Integer, Integer>> initMst = new HashMap<>();
        initMst.put(parent, candidates);
        boolean allVisited = false;

        while (!allVisited) {
            Pair<Integer, Pair<Integer, Integer>> edgePair = getMinimumUnvisited(initMst, visitedMap);
            if (edgePair != null) {
                Map<Integer, Integer> weightMap = new HashMap<>();
                weightMap.put(edgePair.getValue().getKey(), edgePair.getValue().getValue());
                mst.computeIfAbsent(edgePair.getKey(), m -> new HashMap<>()).put(edgePair.getValue().getKey(), edgePair.getValue().getValue());
                visitedMap.put(edgePair.getValue().getKey(), true);
                Map<Integer, Integer> newCandidates = graphN.getAdjMap().get(edgePair.getValue().getKey()); //put all its neighbours
                initMst.put(edgePair.getValue().getKey(), newCandidates);
            }
            Optional<Boolean> notVisited = visitedMap.values().stream().filter(k -> k == false).findAny();
            if (!notVisited.isPresent()) {
                allVisited = true;
            }
        }
        return mst;
    }

    private static Pair<Integer, Pair<Integer, Integer>> getMinimumUnvisited(Map<Integer, Map<Integer, Integer>> candidates, Map<Integer, Boolean> visitedMap) {
        int min = Integer.MAX_VALUE;
        Pair<Integer, Pair<Integer, Integer>> edge = null;
        Pair<Integer, Integer> edgeWait;
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : candidates.entrySet()) {
            for (Map.Entry<Integer, Integer> entry1 : entry.getValue().entrySet()) {
                if (!visitedMap.get(entry1.getKey())) {
                    if (entry1.getValue() < min) {
                        min = entry1.getValue();
                        edgeWait = new Pair<>(entry1.getKey(), entry1.getValue());
                        edge = new Pair<>(entry.getKey(), edgeWait);
                    }
                }
            }
        }
        return edge;
    }
}


class GraphN {

    Map<Integer, Map<Integer, Integer>> adjMap;
    int vertices;

    public GraphN(int v) {
        vertices = v;
        adjMap = new HashMap<>();
    }

    public Map<Integer, Map<Integer, Integer>> getAdjMap() {
        return adjMap;
    }

    public void setAdjMap(Map<Integer, Map<Integer, Integer>> adjMap) {
        this.adjMap = adjMap;
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }
}

class Graf {

    int vertices;
    LinkedList<Edge>[] adjList;


    public Graf(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
    }

    //if nodes are 1 indexed
    public void addEdge(int src, int dest, int w) {
        Edge edge = new Edge(src, dest, w);
        if (adjList[src] == null) {
            adjList[src] = new LinkedList<>();
        }
        if (adjList[dest] == null) {
            adjList[dest] = new LinkedList<>();
        }
        adjList[src].add(edge);
        adjList[dest].add(edge);
    }

    public void addEdge(Edge e) {
        if (adjList[e.getEither()] == null) {
            adjList[e.getEither()] = new LinkedList<>();
        }
        if (adjList[e.getOther()] == null) {
            adjList[e.getOther()] = new LinkedList<>();
        }
        adjList[e.getEither()].add(e);
        adjList[e.getOther()].add(e);
    }


}

class Edge {
    int either;
    int other;
    int weight;

    public Edge(int e, int o, int weight) {
        this.either = e;
        this.other = o;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getEither() {
        return either;
    }

    public void setEither(int either) {
        this.either = either;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}

class EdgeComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {
        if (o1.getWeight() < o2.getWeight()) {
            return -1;
        } else if (o1.getWeight() > o2.getWeight()) {
            return 1;
        } else {
            return 0;
        }
    }
}
