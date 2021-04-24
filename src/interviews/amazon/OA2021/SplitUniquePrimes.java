package interviews.amazon.OA2021;

/*
    * Given a string made up of integers 0 to 9,
    * count the number of ways to split the string into prime numbers in the range of 2 to 1000 inclusive,
    * using up all the characters in the string.

    Each prime number, pn, must not contain leading 0s, and 2 <= pn <= 1000.

    Constraints
    The input string does not contain any leading 0s.

    Examples
    Example 1:
    Input: "31173"
    Output: 6
    Explanation:
    The string "31173" can be split into prime numbers in 6 ways:

    [3, 11, 7, 3]
    [3, 11, 73]
    [31, 17, 3]
    [31, 173]
    [311, 7, 3]
    [311, 73]
    *
 */


import java.util.*;

public class SplitUniquePrimes {

    public static void main(String[] args) {
        System.out.printf(" " + getPrimeSplit("31173"));
    }

    private static int getPrimeSplit(String num) {
        if (num == null || num.isEmpty()) {
            return 0;
        }

        Set<String> sieve = getSieve(1000);
        int n = num.length();
        int[] dp = new int[n];
        return getPrimes(0, num, sieve, dp);
    }

    private static int getPrimes(int start, String num, Set<String> sieve, int[] dp) {
        if (start == num.length()) {
            return 1;
        }

        if (dp[start] != 0) {
            return dp[start];
        }

        int c = 0;
        for (int end = start; end < num.length(); end++) {
            String s = num.substring(start, end+1);
            if (sieve.contains(s)) { //valid prime
                int p = getPrimes(end + 1, num, sieve, dp);
                if (p != -1) {
                    c+=p;
                }
            }
        }

        dp[start] = c;
        return dp[start];
    }

    private static Set<String> getSieve(int n) {
        int[] primes = new int[n + 1];
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (primes[i] == 0) {
                for (int j = i * i; j < n; j += i) {
                    if (primes[j] == 0) {
                        primes[j] = 1;
                    }
                }
            }
        }

        Set<String> sieve = new HashSet<>();
        for (int i = 2; i < primes.length; i++) {
            if (primes[i] == 0) {
                sieve.add(String.valueOf(i));
            }
        }
        return sieve;
    }
}
