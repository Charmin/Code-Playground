package problem_solving;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by chaitra.kr on 14/05/18.
 */
public class NonRepeat {

    private static final Scanner scanner = new Scanner(System.in);
    long MAX_VAL = (long) Math.pow(10, 5);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        Scanner scanner = new Scanner(System.in);

        int x = Integer.parseInt(scanner.nextLine());
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int y = Integer.parseInt(scanner.nextLine());
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int z = Integer.parseInt(scanner.nextLine());
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int res = nonRepeatingDigitProductCount(x, y, z);

//        bufferedWriter.write(String.valueOf(res));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(res);

        scanner.close();
    }

    private static int nonRepeatingDigitProductCount(int x, int y, int z) {
        int count = 0;
        for (long i = y; i <= z; i++) {
            long product = x * i;
            if(!checkIfDigitExists(product, i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean checkIfDigitExists(long product, long i) {
        boolean notDisJoint = true;
        Set<Long> digitsInProduct = getDigits(product);
        Set<Long> digitsInNumber = getDigits(i);
        if (Collections.disjoint(digitsInNumber, digitsInProduct)) {
            notDisJoint = false;
        }
        return notDisJoint;
    }

    private static Set<Long> getDigits(long p) {
        Set<Long> digits = new HashSet<Long>();
        if (p >= 10) {
            do {
                long r = p % 10;
                digits.add(r);
                p = p / 10;
            } while (p >= 10);
        }
        digits.add(p);
        return digits;
    }
}
