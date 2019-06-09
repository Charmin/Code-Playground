package problem_solving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 01/05/18.
 */
public class ValidateAscii {

    private static int lowerRange = 32;
    private static int upperRange = 126;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while(!end) {
            String value = scanner.nextLine();
            System.out.print(validate(value));
            if(value.equals("\\n")) {
                end = true;
            }
        }
    }

    static int[] oddNumbers(int l, int r) {
        List<Integer> ints = new ArrayList<>();
        int j = 0;
        for (int i=l; i<=r; i++) {
            if ((i % 2) != 0) {
                ints.add(i);
            }
        }

        int[] odds = new int[ints.size()];
        for (int n : ints) {
            odds[j] = n;
            j++;
        }

        return odds;
    }


    private static boolean validate(String value) {
        boolean isValid = false;
        String ascii = getAscii(value);
        System.out.println(Integer.toHexString(Integer.valueOf(ascii)));
        int intVal = Integer.valueOf(ascii);
        if (intVal >= lowerRange && intVal <= upperRange) {
            isValid = true;
        }
        return isValid;
    }

    private static String getAscii(String value) {
        StringBuilder builder = new StringBuilder();
        char[] array = value.toCharArray();
        for (char c : array) {
           builder.append((byte) c);
        }
        return builder.toString();
    }
}
