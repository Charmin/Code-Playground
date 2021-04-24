package interviews.amazon.OA2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A librarian would like to count the number of enclosed items in a row that are between two dividers. A row is represented by a string s, each item is a *, and a divider is represented by |. A list of range tuples are given that represent each substring to consider, and the number of enclosed items for each substring must be returned in a list.
 * <p>
 * * = ascii number 42
 * | = ascii number 124
 * Example 1:
 * Input: s = |**|*|*, ranges = [[0, 4], [1, 6]]
 * Output: [2, 1]
 * Explanation:
 * The first range to consider is [0, 4] which corresponds to |**|*. There are 2 items in the first enclosed part.
 * <p>
 * For the second range, [1, 6], the substring is **|*|*, which contain only one enclosed section with one item in it.
 * <p>
 * Both of the answers are returned in an array, ie. [2, 1].
 * <p>
 * Example 2:
 * Input: s = *|*|, ranges = [[1, 3]]
 * Output: [1]
 * Explanation:
 * The substring from index = 1 to index = 3 is |*|. There is only one item and it is surrounded by two dividers. Therefore, the output is [1].
 */
public class ItemsInContainer {

    public static void main(String[] args) {
        String s = "|*|****|**|*";
        List<Integer> startIndices = Arrays.asList(3, 0);
        List<Integer> endIndices = Arrays.asList(10, 10);

        List<Integer> res = numberOfItems(s, startIndices, endIndices);
        res.forEach(a -> System.out.print(a + " "));
    }

    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {

        int n = s.length();
        int[][] cache = new int[n+1][n+1];
        List<Integer> res = new ArrayList<>();

        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }

        for (int l = 0; l < startIndices.size(); l++) {
            int i1 = startIndices.get(l);
            int i2 = endIndices.get(l);
            int count = 0;
            int closedCount = 0;
            boolean wasEnclosed = false;

            if (cache[i1][i2] >= 0) {
                continue;
            }

            int k = i1;
            while (k <= i2) {
                if (wasEnclosed) {
                    if (s.charAt(k) == '|') {
                        cache[i1][k] = count;
                        closedCount = count;
                    }
                    if (s.charAt(k) == '*') {
                        cache[i1][k] = closedCount;
                        count++;
                    }
                } else {
                    if (s.charAt(k) == '|') {
                        wasEnclosed = true;
                    }
                }
                k++;
            }

            res.add(cache[i1][i2] == -1 ? 0: cache[i1][i2]);
        }

        return res;
    }
}
