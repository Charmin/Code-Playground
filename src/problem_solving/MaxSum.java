package problem_solving;

import java.util.*;

public class MaxSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        for (int j = 0; j < input; j++) {
            int size = scanner.nextInt();
            int[] arr = new int[size];
            List<Integer> positives = new ArrayList<>();
            List<Integer> negatives = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
                if (arr[i] >= 0) {
                    positives.add(i);
                }
                if (arr[i] < 0) {
                    negatives.add(i);
                }
            }
            //bruteForce(arr, positives, negatives);
            kadane(arr, positives, negatives);
        }
    }


    static int find_max_sum_nonadjacent(int[] a) {
        Map<Integer, Integer> indexSumCache = new HashMap<>();
        int sum = 0;
        int result = getMaxSum(a, 0, sum, indexSumCache);
        if (sum > 0) {
            return sum;
        }
        return -1;
    }

    public static int getMaxSum(int[] a, int index, int sum, Map<Integer, Integer> cache) {

        if (index == a.length-1) {
            return sum;
        }

        if (cache.containsKey(index)) {
            return cache.get(index);
        }

        int with = getMaxSum(a, index+2, sum + a[index], cache);
        int without = getMaxSum(a, index+1, sum, cache);

        int max = Math.max(with, without);
        cache.put(index, max);

        return max;
    }

//-2 -3 4 -1 -2 1 5 -3
    private static void kadane(int[] arr, List<Integer> positives, List<Integer> negatives) {
        int maxSoFar = Integer.MIN_VALUE;
        int endSofar = 0;
        int s = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (s < 0) {
                    s = 0;
                }
            }
            s += arr[i];
            if (s > maxSoFar) {
                maxSoFar = s;
            }
        }
        System.out.println(maxSoFar);
    }

    private static int getMaxFromCumulativeSum(Map<Integer, Integer> cumulativeSum, Integer start, Integer end, int[] arr) {
        int maxC = 0;
        int cMax = cumulativeSum.containsKey(start - 1) ? cumulativeSum.get(start - 1) : 0;
        for (int i = start; i <= end; i++) {
            int newSum = cumulativeSum.get(i) - cMax;
            cumulativeSum.put(i, newSum);
        }
        for (Map.Entry<Integer, Integer> entry : cumulativeSum.entrySet()) {
            if (entry.getValue() >= maxC) {
                maxC = entry.getValue();
            }
        }
        return maxC;
    }

    private static void bruteForce(int[] arr, List<Integer> positives, List<Integer> negatives) {
        //all negatives
        int maxSum = 0;
        if (positives.size() == 0) {
            maxSum = Arrays.stream(arr).max().getAsInt();
        } else if (negatives.size() == 0) {
            maxSum = Arrays.stream(arr).sum();
        } else {
            int start = positives.stream().min(Integer::compare).get();
            int end = positives.stream().max(Integer::compare).get();
            int sum = 0;
            Map<Integer, Integer> cumulativeSum = new HashMap<>();
            if (end > start) {
                for (int i = start; i <= end; i++) {
                    sum += arr[i];
                    cumulativeSum.put(i, sum);
                }
                for (Integer pIndex : positives) {
                    maxSum = Math.max(maxSum, getMaxFromCumulativeSum(cumulativeSum, pIndex, end, arr));
                }
            } else {
                maxSum = arr[start];
            }
        }

        System.out.println(maxSum);
    }

//    public class output {
//
//        public static class input
//        {
//            public static void foo() { }
//        }
//    }
}
