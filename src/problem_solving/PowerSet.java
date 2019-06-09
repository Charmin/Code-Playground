package problem_solving;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chaitra.kr on 08/11/18.
 */
public class PowerSet {

//    public static List<List<Integer>> getPowerSet(List<Integer> input) {
//        if (input.size() == 1) {
//
//        }
//    }

    private static void kSum(List<Integer> v, int k, List<HashSet<Integer>> sets, Set<Integer> set) {
        int n = v.size();
        int twoPr = 1 << n;
        for (int i = 1; i < twoPr; i++) {
            HashSet<Integer> subSet = new HashSet<>();
            int subSum = 0;
            int p = i & ~(i-1);
            for (int j = 0 ; j < n; j++) {
                if (p == 1) {
                    subSum += v.get(j);
                    subSet.add(v.get(j));
                }
                p = p >>1;
            }
            if (subSum == k) {
                sets.add(subSet);
            }
        }
    }
}
