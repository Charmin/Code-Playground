package interviews.amazon;

import java.util.HashMap;
import java.util.Map;

public class DiceRolls {

    public static void main(String[] args) {
        Map<String, Integer> cache = new HashMap<>();
        int sum = (getRolls(30, 300, 30, cache));
        System.out.println(sum);
        System.out.println("done");
    }

    private static int getRolls(int d, int target, int f, Map<String, Integer> cache) {
        int mod = 1000000000 + 7;

        if (target == 0 && d == 0) { // spot on, you have a solution!
            return 1;
        }


        if (target == 0 || d == 0) { // if one of them becomes zero before the other, you will never reach the target
            return 0;
        }

        String key = d + "|" + target;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int totalWays = 0;
        for (int i = 1; i <= f; i++) {
            if (target - i >= 0) {
                totalWays = (totalWays + (getRolls(d - 1, target - i, f, cache))) % mod;

            } else {
                break;
            }
        }

        cache.put(key, totalWays);
        return (totalWays);
    }
}
