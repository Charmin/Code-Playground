package problem_solving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chaitra.kr on 06/05/18.
 */
public class CoinChange {

    private static Map<Long, List<CoinSet>> mapOfWays = new HashMap<>();
    private static long nways = 0;

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        long[] c = new long[m];
//        for (int c_i = 0; c_i < m; c_i++) {
//            c[c_i] = in.nextLong();
//        }
//        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
//        List<Long> coinSetForInput = Arrays.stream(c).boxed().collect(toList());
//        long ways = getWays(n, coinSetForInput);
        long n = 5;
        long[] cache = new long[(int) (n + 1)];
        //int sum = 11;
        int[] coins = new int[3];
        coins[0] = 1;
        coins[1] = 2;
        coins[2] = 3;

        int[] coinI = new int[3];
        coinI[0] = 1;
        coinI[1] = 2;
        coinI[2] = 3;

        Map<Integer, Integer> memo = new HashMap<>();
        Map<Integer, Integer> caches = new HashMap<>();
        getCoinChanges(coinI, coinI.length - 1, 5, caches);
        System.out.println(caches.get(5));
        memo.put(0, 1);
        int ways = getWaysCoinsRec(coins, coins.length - 1, 5, memo);
        System.out.println(ways);
    }

    private static boolean isLesserThanAll(long n, List<Long> set) {
        boolean isLesser = false;
        if (n != 0 && set.stream().allMatch(a -> a > n)) {
            isLesser = true;
        }
        return isLesser;
    }

    private static long getWaysDynamic(long n, List<Long> coinSetForInput) {
        long ways = 0;

        if (mapOfWays.containsKey(n)) {
            List<CoinSet> coinSets = mapOfWays.get(n);
            for (CoinSet coinSet : coinSets) {
                if ((coinSet.getCoinList().size() == coinSetForInput.size()) && coinSet.getCoinList().containsAll(coinSetForInput)) {
                    ways = coinSet.getNways();
                }
            }
        } else {
            if (n == 0) {
                ways = 1;
            } else if (coinSetForInput.size() == 0 || n < 0) {
                ways = 0;
            } else if (coinSetForInput.size() >= 2) {
                long subSum = n - coinSetForInput.get(coinSetForInput.size() - 1);
                long withCoin = 0;
                if (mapOfWays.containsKey(subSum)) {
                    withCoin = checkFromCache(subSum, coinSetForInput);
                } else {
                    withCoin = getWaysDynamic(subSum, coinSetForInput);
                }
                List<Long> subCoinSet = coinSetForInput.subList(0, coinSetForInput.size() - 1);
                long withoutCoin = getWaysDynamic(n, subCoinSet);
                ways = withCoin + withoutCoin;
            } else {
                long p = coinSetForInput.get(0);
                long diff = n - p;
                while (diff > 0) {
                    diff = diff - p;
                }
                if (diff == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
            CoinSet coinSet = new CoinSet(ways, coinSetForInput);
            mapOfWays
                    .computeIfAbsent(n, c -> new ArrayList<>())
                    .add(coinSet);

        }
        return ways;
    }

    static class CoinSet {
        Long nways;
        List<Long> coinList;

        CoinSet(long ways, List<Long> coinList) {
            nways = ways;
            this.coinList = coinList;
        }

        public Long getNways() {
            return nways;
        }

        public List<Long> getCoinList() {
            return coinList;
        }
    }

    private static long checkFromCache(long n, List<Long> coinSetForInput) {
        long ways = 0;
        for (CoinSet coinSet : mapOfWays.get(n)) {
            if (coinSet.getCoinList().size() == coinSetForInput.size() && coinSet.getCoinList().containsAll(coinSetForInput)) {
                ways = coinSet.getNways();
                break;
            }
        }
        return ways;
    }

    /* fails for
    *
    * 219 24
      36 10 42 7 50 1 49 24 37 12 34 13 39 18 8 29 19 43 5 44 28 23 35 26
    *
    * */

    public static long getWaysCoins(List<Long> c, int i, long n, Map<String, Long> memo) {
        if (n == 0) {
            return 1;
        }

        if (i < 0 | n < 0) {
            return 0;
        }

        String key = i + "|" + n;
        //String key = String.valueOf(n);

        if (!memo.containsKey(key)) {
            long withCoin = getWaysCoins(c, i, n - c.get(i), memo);
            long withOutCoin = getWaysCoins(c, i - 1, n, memo);

            long ways = withCoin + withOutCoin;
            memo.put(key, ways);
        }

        return memo.get(key);
    }



    //works not
    public static int getWaysCoinsRec(int[] nums, int i, int n, Map<Integer, Integer> memo) {
        if (n == 0) {
            return 1;
        }

        if (i < 0 | n < 0) {
            return 0;
        }

        int sol = 0;
        if (memo.get(n) != null) {
            return memo.get(n);
        }
        for (int j = i; j >= 0; j--) {
            sol = sol + getWaysCoinsRec(nums, j, n - nums[j], memo);
            memo.put(n, sol);
        }
        return sol;
    }

    static int getCoinChanges(int[] denominations, int index, int amount, Map<Integer, Integer> cache) {

        if (amount == 0) {
            return 1;
        }

        if (amount < 0 || (index > denominations.length - 1)) {
            return 0;
        }


        if (cache.get(amount) != null) {
            return cache.get(amount);
        }

        for (int k = 1; k <= amount; k++) {
            cache.put(k, 0);
        }
        cache.put(0, 1);

        for (int i = index; i < denominations.length; i++) {
            for (int j = denominations[i]; j <= amount; j++) {
                cache.put(j, cache.get(j) + cache.get(j - denominations[i]));
            }
        }

        String c = new String("{}");

        return cache.get(amount);

    }
}
