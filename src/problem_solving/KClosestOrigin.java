package problem_solving;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by chaitra.kr on 09/04/19.
 */

//return 1 if rhs should be before lhs
//return -1 if lhs should be before rhs
//return 0 otherwise (meaning the order stays the same)

public class KClosestOrigin {

    static PriorityQueue<Pair<Integer, Integer>> q;

    public static void main(String[] args) {
        int[][] inp = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        q = new PriorityQueue<Pair<Integer, Integer>>(k, (c1, c2) -> {
            return c1.getValue() - c2.getValue();
        });

        int[][] val = getKClosest(inp, k);
        System.out.println(val);
    }

    private static int[][] getKClosest(int[][] inp, int i) {
        for (int k = 0; k < inp.length; k++) {
            int d = inp[k][0] * inp[k][0] + inp[k][1] * inp[k][1];
            Pair<Integer, Integer> p = new Pair<>(k, d);
            q.add(p);
        }

        int[][] res = new int[i][2];
        int l = 0;
        while (!q.isEmpty() && l < i) {
            Pair<Integer, Integer> po = q.poll();
            res[l][0] = inp[po.getKey()][0];
            res[l][1] = inp[po.getKey()][1];
            l++;
        }

        return res;
    }
}
