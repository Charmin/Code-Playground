package interviews.amazon.OA2021;

/*
* Find a pair of entries from two lists that yield a sum that is as close to a target number as possible, without exceeding it.

Each entry in a list is a key-value pair, where the key is a number identifier and the value is also a number. The output must be a list containing the pairs of numbers representing the identifiers that yield the result.

If a solution is not possible, return an empty list.

Input
a = an array of number pairs where the first number is an identifier and the second number is a value.

b = Same as a. Each identifier is unique in each list.

target = a number

Examples
Example 1:
Input:
a = [[1, 2], [2, 4], [3, 6]],
b = [[1, 2]],
target = 7,

Output: [[2, 1]]
Explanation:
There are only three possible pairs, [1, 1], [2, 1], and [3, 1]. They yield the sum values of 2 + 2 = 4, 4 + 2 = 6 and 6 + 2 = 8 respectively.

6 is the largest number that does not exceed 7, therefore [2, 1] is the optimal pair.

Example 2:
Input:
a = [[1, 3], [2, 5], [3, 7], [4, 10]],
b = [[1, 2], [2, 3], [3, 4], [4, 5]],
*
* a = [[1, 3], [2, 5], [3, 7], [4, 10]]
* b = [[1, 2], [2, 3], [3, 4], [4, 5]]
*
target = 10,

Output: [[2, 4], [3, 2]]
Explanation:
There are two pairs possible. The element with id = 2 from list a has a value of 5, and the element with id = 4 from list b also has a value of 5. Combined, they add up to 10.

Likewise, the element with id = 3 from a has a value of 7, and the element with id = 2 from b has a value of 3. These also add up to 10.

Therefore, the optimal pairs are [2, 4] and [3, 2].

Example 3:
Input:
a = [[1, 8], [2, 7], [3, 14]], b = [[1, 5], [2, 10], [3, 14]], target = 20,

Output: [[3, 1]]
Example 4:
Input:
a = [[1, 8], [2, 15], [3, 9]], b = [[1, 8], [2, 11], [3, 12]], target = 20,

Output: [[1, 3], [3, 2]]
*
* */

import java.util.ArrayList;
import java.util.List;

public class OptimalUtilsation {

    public static void main(String[] args) {
    }

    public static List<List<Integer>> getPairs(List<List<Integer>> a, List<List<Integer>> b, int target) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty()) {
            return new ArrayList<>();
        }

        a.sort(OptimalUtilsation::compare);
        b.sort(OptimalUtilsation::compare);

        List<int[]> res = new ArrayList<>();
        int i = 0;
        int j = b.size()-1;
        int max = 0;
        int jp = j;

        while (i < a.size() && j >= 0) {
            int curSum = a.get(i).get(1) + b.get(j).get(1);
            if (curSum <= target) {

                if (curSum > max) {
                    max = curSum;
                    res.clear();
                }

                jp = j; //copy of pointer to move forward
                res.add(new int[] {a.get(i).get(0), b.get(j).get(0)});

                while (jp > 0 && b.get(j).get(1) == b.get(jp-1).get(1)) {
                    res.add(new int[]{a.get(i).get(0), b.get(jp-1).get(0)});
                    jp--;
                }

                i++;
            } else {
                j--;
            }
        }

        List<List<Integer>> pairs = new ArrayList<>();
        for (int[] p : res) {
            List<Integer> r = new ArrayList<>();
            r.add(p[0]);
            r.add(p[1]);
            pairs.add(r);
        }

        return pairs;
    }

    private static int compare(List<Integer> i, List<Integer> j) {
        return i.get(1) - j.get(1);
    }


}
