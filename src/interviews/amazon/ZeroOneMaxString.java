package interviews.amazon;

import java.util.HashMap;
import java.util.Map;

public class ZeroOneMaxString {

    public static void main(String[] args) {
        //String[] arr = {"10", "0001", "111001", "1", "0"};
        String[] arr = {"10", "0", "1"};
        int r = findMaxForm(arr, 1, 1);
        System.out.println(r);
    }

    //Memoization
    public static int findMaxForm(String[] strs, int m, int n) {
        if ((m == 0 && n == 0) || strs.length == 0) {
            return 0;
        }
        Map<String, Integer> memo = new HashMap<>();
            int[][] tab = new int[m + 1][n + 1];
        int res2 = findWaysTD(strs, m, n, tab);
        //int res = findWays(strs, 0, m, n, 0, memo);
        return res2;
    }

    private static int findWays(String[] strs, int i, int m, int n, int count, Map<String, Integer> memo) {

        if (i == strs.length) {
            return count;
        }

        if (m == 0 && n == 0) return 0;

        int zc = getCount('0', strs[i]);
        int nzc = getCount('1', strs[i]);

        String key = m + "|" + n + "|" + i;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = 0;
        if (canForm(strs[i], m, n)) {
            int with = findWays(strs, i + 1, m - zc, n - nzc, count + 1, memo);
            int without = findWays(strs, i + 1, m, n, count, memo);
            res += Math.max(with, without);
        } else {
            res += findWays(strs, i + 1, m, n, count, memo);
        }

        memo.put(key, res);
        return res;
    }

    private static boolean canForm(String s, int m, int n) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (m == 0) return false;
                m--;
            } else {
                if (n == 0) return false;
                n--;
            }
        }
        return true;
    }

    private static int getCount(char c, String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == c ? 1 : 0;
        }
        return count;
    }

    //bottom up
    public static int findWaysTD(String[] strs, int m, int n, int[][] tab) {
        for (int k = 0; k < strs.length; k++) {
            int c0 = getCount('0', strs[k]);
            int c1 = getCount('1', strs[k]);

            for (int i = m; i >= c0; i--) {
                for (int j = n; j >= c1; j--) {
                    tab[i][j] = Math.max(1 + tab[i - c0][j - c1], tab[i][j]);
                }
            }
        }
        return tab[m][n];
    }


}
