package problem_solving;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 07/11/18.
 */
public class NQueens {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> positions = new LinkedList<>();
        List<List<Integer>> solutions = new LinkedList<>();
        List<List<Integer>> nQueens = nQueens(n, 0, (LinkedList<Integer>) positions, solutions);
        System.out.println(nQueens.size());
        for (List<Integer> list : nQueens) {
            list.stream().forEach(e -> System.out.print(" "+e));
            System.out.println();
        }
    }


    private static List<List<Integer>> nQueens(int n, int rowIndex, LinkedList<Integer> positions, List<List<Integer>> solutions) {

        if (rowIndex == n) { //reached the nth row and hence the solution found
            List<Integer> positionCopy = new ArrayList<>(positions);
            solutions.add(positionCopy);
            return solutions;
        }

        for (int col = 0; col < n; col++) {
            positions.add(col);
            if (isValidColumn(col, positions)) {
                nQueens(n, rowIndex + 1, positions, solutions);
            }
            positions.removeLast();
        }
        return solutions;
    }

    private static boolean isValidColumn(int col, LinkedList<Integer> positions) {
        int newQueenPos = positions.size()-1;
        if (positions.size() > 1) {
            for (int i = 0; i < positions.size()-1; i++) {
                if (positions.get(i) == col) {
                    return false;
                }
                if (Math.abs(positions.get(newQueenPos) - positions.get(i)) == newQueenPos-i)  {//if they are equidistant
                    // from the existing queens, they may be  in the diagonals {
                    return false;
                }
            }
        }
        return true;
    }
}
