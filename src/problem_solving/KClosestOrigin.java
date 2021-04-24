package problem_solving;

import algorithms.IceCreamParlour;

import java.util.PriorityQueue;

/**
 * Created by chaitra.kr on 09/04/19.
 */

//return 1 if rhs should be before lhs
//return -1 if lhs should be before rhs
//return 0 otherwise (meaning the order stays the same)

public class KClosestOrigin {

    static PriorityQueue<IceCreamParlour.Pair> q;

    public static void main(String[] args) {
        int[][] inp = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        q = new PriorityQueue<IceCreamParlour.Pair>(k, (c1, c2) -> {
            return (int) c1.getSecond() - (int) c2.getSecond();
        });

        int[][] val = getKClosest(inp, k);
        System.out.println(val);
    }

    private static int[][] getKClosest(int[][] inp, int i) {
        for (int k = 0; k < inp.length; k++) {
            int d = inp[k][0] * inp[k][0] + inp[k][1] * inp[k][1];
            IceCreamParlour.Pair<Integer, Integer> p = new IceCreamParlour.Pair<>(k, d);
            q.add(p);
        }

        int[][] res = new int[i][2];
        int l = 0;
        while (!q.isEmpty() && l < i) {
            IceCreamParlour.Pair<Integer, Integer> po = q.poll();
            res[l][0] = inp[po.getFirst()][0];
            res[l][1] = inp[po.getFirst()][1];
            l++;
        }

        return res;
    }
}
