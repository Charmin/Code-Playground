package interviews.amazon.OA2021;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array and a sorting algorithm, the sorting algorithm will do a selection swap. Find the number of swaps to sort the array.
 *
 * Example 1:
 * Input: [5, 4, 1, 2]
 * Output: 5
 * Explanation:
 * Swap index 0 with 1 to form the sorted array [4, 5, 1, 2].
 * Swap index 0 with 2 to form the sorted array [1, 5, 4, 2].
 * Swap index 1 with 2 to form the sorted array [1, 4, 5, 2].
 * Swap index 1 with 3 to form the sorted array [1, 2, 5, 4].
 * Swap index 2 with 3 to form the sorted array [1, 2, 4, 5].
 * 1
 */
public class NumberOfSwapsToSort {

    static int[] mergedCount;
    public static int numberOfSwapsToSort(List<Integer> nums) {

        int[] num = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            num[i] = nums.get(i);
        }
        List<Integer> countOfSmallerThanSelf = countSmaller(num);

        int res = 0;
        for (int s : countOfSmallerThanSelf) {
            res += s;
        }

        return res;
    }

    public static List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        int[] indexMap = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexMap[i] = i;
        }

        List<Integer> res = new ArrayList<>();
        mergedCount = new int[nums.length];
        mergeSort(nums, 0, nums.length-1, indexMap);

        for (int e : mergedCount) {
            System.out.print(e);
            res.add(e);
        }

        return res;
    }

    private static void mergeSort(int[] nums, int start, int end, int[] index) {

        if (start < end) {
            int mid = start + (end - start)/2;
            mergeSort(nums, start, mid, index);
            mergeSort(nums, mid+1, end, index);
            merge(nums, start, end, mid, index);
        }

    }

    private static void merge(int[] nums, int start, int end, int mid, int[] index) {

        int i = start;
        int j = mid+1;

        int k = start;

        int count = 0;

        int[] indexSnapshot = new int[index.length];
        for (int e = 0; e < index.length; e++) {
            indexSnapshot[e] = index[e];
        }

        while (i <= mid && j <= end) {
            if (nums[indexSnapshot[i]] > nums[indexSnapshot[j]]) { //this j will be the greater than count for all elements in array1
                index[k] = indexSnapshot[j];
                j++;
                count++;
            } else {
                index[k] = indexSnapshot[i];
                mergedCount[indexSnapshot[i]] += count;
                i++;
            }
            k++;
        }


        while (i <= mid) {
            index[k] = indexSnapshot[i];
            mergedCount[indexSnapshot[i]] += count;
            i++;
            k++;
        }

        while (j <= end) {
            index[k] = indexSnapshot[j];
            j++;
            k++;
        }

    }
}
