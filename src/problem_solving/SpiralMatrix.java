package problem_solving;

import java.util.*;

import static java.lang.System.in;

/**
 * Created by chaitra.kr on 14/05/18.
 */
public class SpiralMatrix {

    private static Map<Integer, List<Boolean>> visitedMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        int testCases = Integer.parseInt(scanner.nextLine());
        Map<Integer, List<Integer>> adjacencyMatrix = new HashMap<>();
        for (int t = 0; t < testCases; t++) {
            List<Integer> list;
            List<Boolean> visitedSet;
            int j = 0;
            int i;
            while (j < 4) {
                i = 0;
                list = new ArrayList<>(4);
                visitedSet = new ArrayList<>(4);
                while (i < 4) {
                    list.add(scanner.nextInt());
                    visitedSet.add(false);
                    i++;
                }
                scanner.nextLine();
                adjacencyMatrix.put(j, list);
                visitedMap.put(j, visitedSet);
                j++;
            }
            printMatrix(adjacencyMatrix);
            visitedMap.clear();
        }

    }

    private static void printMatrix(Map<Integer, List<Integer>> adjacencyMatrix) {

        boolean allVisited = false;
        int maxRow = adjacencyMatrix.size() - 1;
        int maxCol = adjacencyMatrix.get(0).size() - 1;
        List<Integer> result = new ArrayList<>();
        int row = 0;
        int col = 0;

        while (!allVisited) {
            allVisited = true;

            while (col <= maxCol && visitedMap.get(row).get(col) != true) {
                result.add(adjacencyMatrix.get(row).get(col));
                visitedMap.get(row).set(col, true);
                col++;
                allVisited = false;
            }
            col--;
            row++;
            while (col >= 0 && row <= maxRow && visitedMap.get(row).get(col) != true) {
                result.add(adjacencyMatrix.get(row).get(col));
                visitedMap.get(row).set(col, true);
                row++;
                allVisited = false;
            }
            row--;
            col--;
            while (col >= 0 && row >= 0 && visitedMap.get(row).get(col) != true) {
                result.add(adjacencyMatrix.get(row).get(col));
                visitedMap.get(row).set(col, true);
                col--;
                allVisited = false;
            }
            col++;
            row--;
            while (row >= 0 && col <= maxCol && visitedMap.get(row).get(col) != true) {
                result.add(adjacencyMatrix.get(row).get(col));
                visitedMap.get(row).set(col, true);
                row--;
                allVisited = false;
            }
            col++;
            row++;
        }
        result.stream().forEach(s -> System.out.print(s + " "));
    }
}
