package problem_solving.Strings;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 30/04/19.
 */
public class LargestNumber {

    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(12, 121, 99);
        List<String> S = A.stream().map(a -> String.valueOf(a)).collect(Collectors.toList());
        System.out.println(getLargestNum(S));
    }

    private static String getLargestNum(final List<String> A) {
        int i = 0;
        while (i < A.size() && A.get(i) == "0") {
            i++;
        }
        if (i == A.size()) {
            return "0";
        }
        int[] t = new int[10];

        A.sort((String a, String b) -> {
            String ab = a + b;
            String ba = b + a;
            if (ab.compareTo(ba) > 0) {
                return -1;
            } else {
                return 1;
            }
        });
        StringBuilder b = new StringBuilder();

        A.stream().forEach(s -> b.append(String.valueOf(s)));
        return b.toString();
    }

    public static String largestNumber(final List<Integer> A) {

        List<Integer> result = new ArrayList(A);
        List<Integer> S = qsort(result, 0, result.size()-1);

        StringBuilder b = new StringBuilder();
        S.stream().forEach(s -> b.append(String.valueOf(s)));
        return b.toString();
    }

    private static List<Integer> qsort(List<Integer> input, int low, int high) {
        if (low < high) {
            int p = partition(input, low, high);
            qsort(input, low, p-1);
            qsort(input, p+1, high);
        }
        return input;
    }

    private static int partition(List<Integer> in, int low, int high) {
        int p = low;
        int i = low + 1;
        int j = high;

        while (i <= j) {
            while (i < in.size() && (compare(in.get(i), in.get(p)) == 1 || compare(in.get(i), in.get(p)) == 0)) {
                i++;
            }

            while(j > 0 && compare(in.get(j), in.get(p)) == -1) {
                j--;
            }

            if (i < j) {
                int t = in.get(i);
                in.set(i, in.get(j));
                in.set(j, t);
            }
        }

        if (j >= 0) {
            int temp = in.get(p);
            in.set(p, in.get(j));
            in.set(j, temp);
        }

        return j;
    }

    private static int compare(int a, int b) {
        int res = 0;

        if (a == 0 && b == 0) {
            return 0;
        }
        int r1 = a % 10;
        int r2 = b % 10;
        if (r1 < r2) {
            res = -1;
        } else if (r1 > r2){
            res = 1;
        } else {
            res = compare(a/10, b/10);
        }

        return res;
    }

}
