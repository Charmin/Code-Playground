package interviews.contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class sum4Divisors {

    public static void main(String[] args) {
        int[] in = {21,21};
        System.out.println(sumFourDivisors(in));
        System.out.println();
    }

    public static int sumFourDivisors(int[] nums) {
        Map<Integer, Set<Integer>> divCount = new HashMap<>();
        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int n : nums) {
            if (divCount.get(n) == null) {
                divCount.put(n, new HashSet<>());
                int sum = 0;
                sum += divCount.get(n).add(1) ? 1 : 0;
                sum += divCount.get(n).add(n) ? n : 0;

                int i = 3;
                if (n % 2 == 0) { //even
                    sum += divCount.get(n).add(2) ? 2 : 0;
                    sum += divCount.get(n).add(n / 2) ? (n / 2) : 0;

                    while (divCount.get(n).size() <= 4 && (i*i) <= n) {
                        if (n % i == 0) {
                            sum += divCount.get(n).add(i) ? i : 0;
                            sum += divCount.get(n).add(n / i) ? (n / i) : 0;
                        }
                        i++;
                    }
                } else {
                    while (divCount.get(n).size() <= 4 && (i*i) <= n) {
                        if (n % i == 0) {
                            sum += divCount.get(n).add(i) ? i : 0;
                            sum += divCount.get(n).add(n / i) ? (n / i) : 0;
                        }
                        i += 2;
                    }
                }
                sumMap.put(n, sum);
            }
        }

        int res = 0;
        for (int n : nums) {
            if (divCount.get(n).size() == 4) {
                res += sumMap.get(n);
            }
        }
        return res;
    }
}
