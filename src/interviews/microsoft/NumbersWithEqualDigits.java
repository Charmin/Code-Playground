package interviews.microsoft;

import java.util.HashMap;
import java.util.Map;

public class NumbersWithEqualDigits {

    public static void main(String[] args) {
        int[] in1 = {51, 71, 17, 42};
        int[] in2 = {42, 33, 60};
        int[] in3 = {51, 32, 43};
        System.out.println(getSum(in1));
        System.out.println(getSum(in2));
        System.out.println(getSum(in3));
    }

    private static int getSum(int[] nums) {
        int sum = -1;
        Map<Integer, Integer> sumToNum = new HashMap<>();

        for (int num : nums) {
            int dum = getDigitSum(num);
            if (!sumToNum.containsKey(dum)) {
                sumToNum.put(dum, num);
            } else {
                sum = Math.max(sum, num + sumToNum.get(dum));
            }
        }

        return sum;
    }

    private static int getDigitSum(int num) {
        int m = 0;
        while (num >= 10) {
            m += (num%10);
            num = num / 10;
        }
        m+=num;
        return m;
    }
}
