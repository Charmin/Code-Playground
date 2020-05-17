package interviews.amazon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Low link value : The low link value of a node is the smallest id reachable from that node. All nodes part of the same connected component
 * have the same low link value
 * Bridges and articulation points
 * Bridge : The condition for a directed edge e to have nodes that belong to a bridge is when the " id (e.from) < lowlink(e.to) ".
 * Articulation points: if "(e.from) = lowlink(e.to)", there is an articulation point. Such a point must more than 1 outgoing edges. The starting point of a cycle is
 * a good articulation point
 */
public class CriticalConnection {

    public static void main(String[] args) {
        List<List<Integer>> con = new ArrayList<>();
        con.add(Arrays.asList(1, 0));
        con.add(Arrays.asList(2, 0));
        con.add(Arrays.asList(3, 0));
        con.add(Arrays.asList(4, 1));
        con.add(Arrays.asList(4, 2));
        con.add(Arrays.asList(4, 0));
        List<List<Integer>> r = criticalConnections(5, con);
        r.forEach(System.out::println);
    }

    //Tarjan's algorithm
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> crit = new ArrayList<>();

        if (n==1) {
            List<Integer> c = new ArrayList<>();
            crit.add(c);
            return crit;
        }

        Map<Integer, BNode> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new BNode(0, 0, i));
        }

        //Build adjacency matrix
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (List<Integer> e : connections) {
            adj.computeIfAbsent(e.get(0), a -> new ArrayList<>()).add(e.get(1));
            adj.computeIfAbsent(e.get(1), a -> new ArrayList<>()).add(e.get(0));
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(!visited.contains(i)) {
                visited.add(i);
                tarjanDfs(adj, visited, map, 0, -1, crit);
            }
        }
        return crit;
    }

    private static void tarjanDfs(Map<Integer, List<Integer>> adj, Set<Integer> visited, Map<Integer, BNode> map, int v, int parent, List<List<Integer>> res) {
        BNode nv = map.get(v);
        nv.lowLink = nv.id;
        for (Integer n : adj.get(v)) {
            if (n == parent) continue;
            if (!visited.contains(n)) {
                visited.add(n);
                BNode nn = map.get(n);
                nn.id = nv.id + 1;
                map.put(n, nn);
                tarjanDfs(adj, visited, map, n,v, res);
                nv.lowLink = Math.min(nv.lowLink, map.get(n).lowLink);  //Backtracking stage, where the low link is propagated
                map.put(v, nv);
                if (nv.id < map.get(n).lowLink) {
                    res.add(Arrays.asList(n,v));
                }
            } else {
                nv.lowLink = Math.min(nv.lowLink, map.get(n).id);     // Already visited node, hence the update the lowlink to the min
                                                                      // of id of the visited node
                map.put(v, nv);


            }
        }
    }
}

class BNode {
    int id;
    int lowLink; //the lowest id reachable from this node
    int val; //may be same as id

    public BNode(int id, int lowLink, int val) {
        this.id = id;
        this.lowLink = lowLink;
        this.val = val;
    }
}