package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoSumVar {

    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(2, -9, -5, -8, -4, -2, 1, 1, 0, 6, -3, 9, -8, 2, -2, 9, 7, 6, 9, 1, 4, 1, 0, -5, -1, -4, 2, 2, 0, -2, 3, -4, -2, 1, 7, 5, -3, -1, -3, 6, 2, 6, 8, -6, -9, 7, 1, 6, -6, -6, -5, -6, -6, 7, -9, 8, -4, -1, 9, -7, 10, -5, -1, 10, 3, 2, -5, -4, 10, -10, 5, -2, 10, -3, 5, 3, 4, 9, 0, 0, -9);
        List<Integer> C = Arrays.asList(3, -9, 13, 5, 1);
        int B = 4;
        List<Integer> result = twoSum1(C, B);
        System.out.println("done");
    }

    private static List<Integer> twoSum1(List<Integer> a, int b) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        ArrayList<Integer> ans = new ArrayList<Integer>();

        for (int i = 0; i < a.size(); i++) {
            int curr = a.get(i);

            if (map.containsKey(b - curr)) {
                int index = map.get(b - curr);
                ans.add(index);
                ans.add(i + 1);
                return ans;
            } else if (!map.containsKey(curr)) {
                map.put(curr, i + 1);
            }
        }
        return ans;
    }

    private static List<Integer> twoSum(List<Integer> A, int B) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A == null || A.size() == 0) {
            return result;
        }
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            indexMap.computeIfAbsent(A.get(i), j -> new LinkedList<>()).add(i);
        }
        List<Integer> uniq = new ArrayList<>(A);
        uniq = uniq.stream().distinct().collect(Collectors.toList());
        if (uniq.size() == 1 && A.size() > 1) {
            if (uniq.get(0) * 2 == B) {
                return new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                }};
            }
        }
        uniq.sort(Comparator.comparingInt(a -> a));
        int s = 0;
        int e = uniq.size() - 1;
        List<List<Integer>> allSums = new ArrayList<>();
        while (s <= e) {
            int sum = uniq.get(s) + uniq.get(e);
            if (sum < B) {
                s++;
            } else if (sum > B) {
                e--;
            } else {
                List<Integer> hj = new ArrayList<Integer>();
                hj.add(uniq.get(s));
                hj.add(uniq.get(e));
                allSums.add(hj);
                e--;
                s++;
            }
        }
        int secIndex = Integer.MAX_VALUE;
        int firstIndex = Integer.MAX_VALUE;
        for (List<Integer> r : allSums) {
            indexMap.get(r.get(0)).sort(Comparator.comparingInt(o -> o));
            indexMap.get(r.get(1)).sort(Comparator.comparingInt(o -> o));
            int r1 = indexMap.get(r.get(0)).get(0);
            int r2 = indexMap.get(r.get(1)).get(0);
            if (r1 == r2) {
                if (indexMap.get(r.get(0)).size() > 1 && secIndex > indexMap.get(r.get(0)).get(1)) {
                    firstIndex = r1;
                    secIndex = indexMap.get(r.get(0)).get(1);
                }
            }
            if (r1 < r2) {
                if (r2 < secIndex) {
                    secIndex = r2;
                    firstIndex = r1;
                }
            } else if (r1 > r2) {
                if (r1 < secIndex) {
                    secIndex = r1;
                    firstIndex = r2;
                }
            }
        }
        if (secIndex != Integer.MAX_VALUE) {
            result.add(firstIndex + 1);
            result.add(secIndex + 1);
        }
        return result;
    }
}
