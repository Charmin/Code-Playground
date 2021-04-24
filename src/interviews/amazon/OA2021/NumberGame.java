package interviews.amazon.OA2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of numbers and a target number. Find the number of pairs of numbers from the list whose sum is divisible by 60.
 *
 * Example 1:
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation:
 * (30, 150), (20, 100) and (20, 40) are pairs of numbers whose sum are divisible by 60.
 */
public class NumberGame {

    public static int numPairsDivisibleBy60(List<Integer> times) {

        if (times.size() < 2) {
            return 0;
        }

        Map<Integer, Integer> remainderCount = new HashMap<>();

        int count = 0;
        for (int i = 0; i < times.size(); i++) {
            if (times.get(i) % 60 == 0) {
                count += remainderCount.getOrDefault(0, 0);
            } else {
                count += remainderCount.getOrDefault(60 - (times.get(i) % 60), 0);
            }
            remainderCount.put(times.get(i) % 60, remainderCount.getOrDefault(times.get(i) % 60, 0) + 1);
        }

        return count;

    }
}
