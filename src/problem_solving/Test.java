package problem_solving;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 02/05/18.
 */
public class Test {

    private static List<Integer> arrayResult = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queryOne = Integer.valueOf(scanner.nextLine());
        List<Integer> arrayOne = new ArrayList<>();
        List<Integer> arrayTwo = new ArrayList<>();
        for (int i =0; i < queryOne; i++) {
            int n = Integer.valueOf(scanner.nextLine());
            arrayOne.add(n);
        }
        int queryTwo = Integer.valueOf(scanner.nextLine());

        for (int i =0; i<queryTwo; i++) {
            int n = Integer.valueOf(scanner.nextLine());
            arrayTwo.add(n);
        }

        int index = getStartIndex(arrayOne, arrayTwo);
        System.out.println(index);
    }

    private static int getStartIndex(List<Integer> arrayOne, List<Integer> arrayTwo) {
        int index = -1;
        if (arrayOne.size() >= arrayTwo.size()) {
            for (int j = 0; j < arrayOne.size(); j++) {
                if (arrayOne.get(j) == arrayTwo.get(0)) {
                    index = j;
                    if (checkIfArrayTwoPresent(arrayOne, arrayTwo, j)) {
                        break;
                    }
                }
            }
        }
        return index;
    }

    private static boolean checkIfArrayTwoPresent(List<Integer> arrayOne, List<Integer> arrayTwo, int j) {
        boolean isSame = false;
        int remLength = arrayOne.size() - j;

        if (remLength >= arrayTwo.size()) {
            for (int m = j; m < j + arrayTwo.size(); m++) {
                arrayResult.add(arrayOne.get(m));
            }
            if (compare(arrayResult, arrayTwo)) {
                isSame = true;
            }
        }
        return isSame;
    }

    private static boolean compare(List<Integer> arrayResult, List<Integer> arrayTwo) {
        boolean isSame = true;
        if (arrayResult.size() == arrayTwo.size()) {
            for (int l = 0; l < arrayResult.size(); l++) {
                if (arrayResult.get(l) != arrayTwo.get(l)) {
                    isSame = false;
                    break;
                }
            }
        } else {
            isSame = false;
        }
        return isSame;
    }


}
