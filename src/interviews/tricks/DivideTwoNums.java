package interviews.tricks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivideTwoNums {

    public static void main(String[] args) {
        System.out.println(divide(
                2147483647, 1));
        List<Integer> p1 = new ArrayList<>();
    }

    public static int divide(int dividend, int divisor) {
        int sign = ((dividend> 0 && divisor < 0) || (dividend < 0 && divisor > 0)) ? -1 : 1;

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        if (dividend == Integer.MAX_VALUE-1 && divisor == 1) return Integer.MIN_VALUE;

        long div = Math.abs((long) divisor);
        long dvn = Math.abs((long) dividend);

        long power = 1;
        int res = 0;

        while (div <= dvn) {
            div = div << 1;
            power = power << 1;
        }

        div >>= 1;
        power >>= 1;

        while (div > 0) {
            if (div <= dvn) {
                dvn = dvn - div;
                res += power;
            }
            power = power >> 1;
            div = div >> 1;
        }
        return sign == -1 ? -res : res;
    }
}
