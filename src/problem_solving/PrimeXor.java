package problem_solving;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 10/05/18.
 */
public class PrimeXor {


    static int primeXor(int[] a) {
        List<Integer> inputs = Arrays.stream(a).boxed().collect(Collectors.toList());
        int xor = inputs.get(0) ^ inputs.get(1);
        System.out.println("XOR " + inputs.get(0) + " and  " + inputs.get(1) + "-" + xor);
        int xor1 = inputs.get(0) ^ inputs.get(1) ^ inputs.get(2);
        System.out.println("XOR " + inputs.get(0) + " and  " + inputs.get(1) + "-" + xor);
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int a_i = 0; a_i < n; a_i++) {
                a[a_i] = in.nextInt();
            }
            int result = primeXor(a);
            System.out.println(result);
        }
        in.close();
    }

}
