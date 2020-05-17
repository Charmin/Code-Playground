package algorithms;

import java.util.*;

/**
 * Created by chaitra.kr on 10/02/18.
 */
public class NumberIslands {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < testCases; i++) {
            int rows = Integer.parseInt(scanner.nextLine());
            int cols = Integer.parseInt(scanner.nextLine());
            Map<Integer, List<Integer>> matrix = new HashMap<>();
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    int e = scanner.nextInt();
                    if (matrix.get(j) != null) {
                        integers.add(e);
                        matrix.put(j, integers);
                    }
                    matrix.get(e).add(e);
                }
            }
            int islands = findIslands(matrix);
        }
    }

    private static int findIslands(Map<Integer, List<Integer>> matrix) {
       return 0;
    }

    public static Map<Integer, Set<DFSNode>> getDFSNodeMap(Map<Integer, List<Integer>> matrix) {
        Map<Integer, Set<DFSNode>> matrixNodesMap = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : matrix.entrySet()) {
            Set<DFSNode> dfsNodes = new HashSet<>();
            int colCount = 0;
            for (int col : entry.getValue()) {
                DFSNode dfsNode = getDFSNode(colCount, entry.getKey(), matrix.size(), entry.getValue().size(), col);
                dfsNodes.add(dfsNode);
                colCount++;
            }
            matrixNodesMap.put(entry.getKey(), dfsNodes);
        }
        return matrixNodesMap;
    }

    private static DFSNode getDFSNode(Integer colIndex, int rowIndex, int maSize, int colCount, int value) {
        DFSNode dfsNode = new DFSNode();
        dfsNode.setValue(value);
        dfsNode.visited = false;
        if ((rowIndex == 0) || rowIndex == (maSize-1)) {
            if (colIndex == 0 || colIndex == (colCount-1))
                dfsNode.setNeighboursCount(3);
            else
                dfsNode.setNeighboursCount(5);
        } else {
            if (colIndex == 0 || colIndex == (colCount -1)) {
                dfsNode.setNeighboursCount(5);
            } else {
                dfsNode.setNeighboursCount(8);
            }
        }
        return dfsNode;
    }
}

class DFSNode {
    int value;
    boolean visited;
    int neighboursCount;
    Set<DFSNode> neighbours;

    public void setValue(int value) {
        this.value = value;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setNeighboursCount(int neighboursCount) {
        this.neighboursCount = neighboursCount;
    }

    public void setNeighbours(Set<DFSNode> neighbours) {
        this.neighbours = neighbours;
    }

    public int getValue() {
        return value;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getNeighboursCount() {
        return neighboursCount;
    }

    public Set<DFSNode> getNeighbours() {
        return neighbours;
    }
}
