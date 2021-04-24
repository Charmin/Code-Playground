package interviews.amazon.OA2021;

import java.util.*;

/**
* Given a multiset (set that allows for multiple instances of same value),
* partition it into two multisets A and B such that the sum of A is greater than that of B. Return A.
* If more than one such As exists, return the one with minimal size.
 *
    Examples
    Example 1:
    Input:
    nums = [4, 5, 2, 3, 1, 2]

    Output:
    [4, 5]
    *
    Input:
    nums = [10, 5, 3, 1, 20]
    Output:

    * [20]

    Input:
    nums = [1, 2, 3, 5, 8]
    Output:
    [5, 8]

    15, 20, 20, 20, 50
*
Explanation:
We can divide the numbers into two subsets A = [4, 5] and B = [1, 2, 2, 3].
* The sum of A is 9 which is greater than the sum of B which is 8.
* There are other ways to divide but A = [4, 5] is of minimal size of 2.
* */
public class OptimiseBoxWeight {

    static int sum = 0;
    static int sumSoFar = Integer.MAX_VALUE;
    //This is knapsack problem, but I did a different thing here
    public static void main(String[] args) {
        List<Integer> r = optimizingBoxWeights(Arrays.asList(15, 20, 20, 20, 50));

        for (int n : r) {
            System.out.print(n+" ");
        }
    }

    public static List<Integer> optimizingBoxWeights(List<Integer> arr) {

        for (int i : arr) {
            sum += i;
        }
        return new ArrayList<>(optimise(arr.size() - 1, arr, new HashSet<>(), 0, new HashMap<>()));
    }

    private static Set<Integer> optimise(int start, List<Integer> arr, Set<Integer> set, int curSum, Map<Integer, Set<Integer>> memo) {
        if (start < 0)  {
            return curSum >= (sum - curSum) ? new HashSet<>(set) : new HashSet<>();
        }

        if (curSum >= (sum - curSum)) {
            if (curSum < sumSoFar) {
                sumSoFar = curSum;
                return new HashSet<>(set);
            } else {
               return new HashSet<>();
            }
        }

        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        set.add(arr.get(start));
        Set<Integer> with = new HashSet<>();
        Set<Integer> without = new HashSet<>();

        with.addAll(optimise(start - 1 , arr, set, curSum + arr.get(start), memo));
        set.remove(arr.get(start));
        without.addAll(optimise(start - 1, arr, set, curSum, memo));


        if (with.size() < without.size()) {
            memo.put(start, with);
            return new HashSet<>(with);
        } else {
            memo.put(start, without);
            return new HashSet<>(without);
        }
    }
}
