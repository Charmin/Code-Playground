package interviews.amazon.OA2021;

import java.util.*;

/**
 * Given four arrays of integers and an integer limit. We need to pick 1 number from each of the four arrays such that the sum of the selected numbers is smaller or equal to limit. Find the number of valid combinations.
 * <p>
 * Example
 * Input:
 * a = [2, 3, 5]
 * b = [5]
 * c = [2, 3, 10]
 * d = [1, 2]
 * limit = 11
 *
 * ordered_map<>: <7, 8, 10>
 *     [3, 4, 11, 4, 5, 12]
 *
 *
 */


public class CombinationThatSumToTarget {

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(2, 3);
        List<Integer> b = Arrays.asList(4);
        List<Integer> c = Arrays.asList(2, 3);
        List<Integer> d = Arrays.asList(1, 2);

        int ways = getNumberOfOptions(a, b, c, d, 10);
        System.out.println(ways);
    }

    private static int getNumberOfOptions(List<Integer> a, List<Integer> b, List<Integer> c, List<Integer> d, int limit) {
        List<Integer> sumList = new ArrayList<>();
        for (int i : a) {
            for (int j : b) {
                sumList.add(i+j);
            }
        }

        sumList.sort(Comparator.comparingInt(k -> k));
        Map<Integer, Integer> countMap = new HashMap<>();
        int res = 0;
        for (int m : c) {
            for (int n : d) {
                int cum = m + n;
                int value = limit - cum;
                if (countMap.containsKey(value)) {
                    res += countMap.get(value);
                } else {
                    int comb = getLessThanOrEqualToCount(limit - cum, sumList);
                    countMap.put(value, comb);
                    res += comb;
                }
            }
        }

        return res;
    }

    private static int getLessThanOrEqualToCount(int sum, List<Integer> sumList) {
        int n = sumList.size();
        int l = 0;
        int h = n-1;

        int k = 0;
        int count  = 0;
        while (k < sumList.size() && sumList.get(k) <= sum) {
            count++;
            k++;
        }

        return count;
//        while (l <= h) {
//            int mid = l + ((h - l) / 2);
//            if (sumList.get(mid) == sum) {
//                return mid;
//            } else {
//                if (sumList.get(mid) > sum && mid > 0 && sumList.get(mid-1) < sum) {
//                    return mid;
//                }
//                if (sumList.get(mid) < sum) {
//                    l = mid+1;
//                } else {
//                    h = mid - 1;
//                }
//            }
//        }
    }


    //this might be brute force
    static int getComb(int index, List<List<Integer>> superList, int limit) {

        if (limit == 0 || (limit > 0 && index >= superList.size())) {
            return 1;
        }

        if (limit < 0) {
            return 0;
        }

        int ways = 0;
        List<Integer> cur = superList.get(index);
        for (int num : cur) {
            ways += getComb(index + 1, superList, limit - num);
        }

        return ways;
    }
}
