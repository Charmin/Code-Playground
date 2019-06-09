package problem_solving;

import java.util.*;

public class KSumSubSets {

    public static void main(String[] args) {
//        HashSet<Integer> set = new HashSet<>();
//        List<Integer> v = Arrays.asList(3,5,7,8,1);
        int n = 4;
        int k =2;
        List<List<Integer>> res = combine(n,k);
        System.out.println(res);

//        List<HashSet<Integer>> sets = new ArrayList<>();
//        kSum(v, 8, sets, set);
//        System.out.println(sets);
    }

//    private static List<Integer> kSumRec(List<Integer> v, int k, int index, List<LinkedList<Integer>> sets, LinkedList<Integer> set) {
//
//        if (k == 0) {
//            LinkedList<Integer> seCopy = new LinkedList<>();
//            seCopy.addAll(set);
//            sets.add(seCopy);
//        }
//
//        set.add(v.get(index));
//        if (v.get(index) <= k) {
//            List<Integer> with = kSumRec(v.subList(index + 1, v.size()), k - v.get(index), index + 1, sets, set);
//            List<Integer> without = kSumRec(v.subList(index + 1, v.size()), k, index + 1, sets, set);
//
//        }
//
//    }

    private static void kSum(List<Integer> v, int k, List<HashSet<Integer>> sets, Set<Integer> set) {
        int n = v.size();
        int twoPr = 1 << n;
        for (int i = 1; i < twoPr; i++) {
            HashSet<Integer> subSet = new HashSet<>();
            int subSum = 0;
            int p = i;
            for (int j = 0 ; j < n; j++) {
                System.out.println(p);
                int l = p & 1;
                if (l > 0) {
                    subSum += v.get(j);
                    subSet.add(v.get(j));
                }
                p = p & p-1;
            }
            if (subSum == k) {
                sets.add(subSet);
            }
        }
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> input = new ArrayList<>();
        for (int i = 0;i< n;i++) {
            input.add(i+1);
        }
        LinkedList<Integer> inter = new LinkedList<>();
        makeCombination(input, result, inter, k, 0,0);
        return result;
    }

    private static void makeCombination(List<Integer> input, List<List<Integer>> result, LinkedList<Integer> inter, int k, int i, int kIndex) {

        if (kIndex == k) {
            List<Integer> copy = new ArrayList<>();
            copy.addAll(inter);
            result.add(copy);
            return;
        }

        if (i == input.size()) {
            return;
        }

        inter.add(input.get(i));
        makeCombination(input, result, inter, k , i+1, kIndex+1);
        inter.removeLast();
        makeCombination(input, result, inter, k, i+1, kIndex);

    }

}
