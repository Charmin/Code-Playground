package interviews.amazon.OA2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllCyclesInGraph {

    static int cycleCount = 0;

    public static void main(String[] args) {
        List<int[]> edges = new ArrayList<>();
//        edges.add(new int[] {1, 2});
//        edges.add(new int[] {2, 4});
//        edges.add(new int[] {2, 5});
//        edges.add(new int[] {3, 5});
//        edges.add(new int[] {4, 5});
//        edges.add(new int[] {5, 6});

        edges.add(new int[] {1, 2});

//        edges.add(new int[] {1, 2});
//        edges.add(new int[] {1, 3});
//        edges.add(new int[] {2, 3});
//        edges.add(new int[] {2, 4});
//        edges.add(new int[] {4, 5});
//        edges.add(new int[] {3, 4});

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], m -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], m -> new ArrayList<>()).add(edge[0]);
        }

        Map<Integer, List<Integer>> cycles = printAllCyclesInGraph(6, adjList);


        int res = Integer.MAX_VALUE;

        for (Map.Entry<Integer, List<Integer>> cycle : cycles.entrySet()) {
            if (cycle.getValue().size() == 3) { //cycles with length 3
                int sum = 0;
                for (Integer node : cycle.getValue()) {
                    sum += (adjList.get(node).size() - 2);
                }
                res = Math.min(res, sum);
            }
        }

        for (List<Integer> r : cycles.values()) {
            for (int e : r) {
                System.out.print(e+" ");
            }
            System.out.println();
        }

        System.out.println(res);
    }

    //Undirected graph
    public static Map<Integer, List<Integer>> printAllCyclesInGraph(int vertices, Map<Integer, List<Integer>> adjList) {

        Map<Integer, List<Integer>> cyclesMap = new HashMap<>();
        int[] colorMarker = new int[vertices+1];
        int[] parent = new int[vertices+1];

        dfsCycleCount(1, -1, adjList, colorMarker, parent, cyclesMap);

        List<List<Integer>> res = new ArrayList<>();
        //res.addAll(cyclesMap.values());
        return cyclesMap;
    }

    private static void dfsCycleCount(int cur, int parent,
                               Map<Integer, List<Integer>> adjList,
                               int[] colorMarker, int[] parentMap,
                               Map<Integer, List<Integer>> cyclesMap) {

        //If the node is completely visited, dont search further
        if (colorMarker[cur] == 2) {
            return;
        }

        parentMap[cur] = parent; //update the parent

        //If visited, its of a cycle, hence compute cycle length
        if (colorMarker[cur] == 1) {
            cycleCount++;
            int temp = parent;
            while (temp != cur) {
                cyclesMap.computeIfAbsent(cycleCount, c -> new ArrayList<>()).add(temp);
                temp = parentMap[temp];
            }
            cyclesMap.computeIfAbsent(cycleCount, c -> new ArrayList<>()).add(temp);
            return;
        }

        //Not at all visited, hence recursively search
        if (colorMarker[cur] == 0) {
            colorMarker[cur] = 1;
            for (Integer neighbour : adjList.get(cur)) {
                if (neighbour != parent) {
                    dfsCycleCount(neighbour, cur, adjList, colorMarker, parentMap, cyclesMap);
                }
            }
        }
        colorMarker[cur] = 2;
    }
}

