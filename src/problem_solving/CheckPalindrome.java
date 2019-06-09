package problem_solving;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 30/09/18.
 */
public class CheckPalindrome {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String test = (String) scanner.next();
        LinkedList<String> pals = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            pals.add(test.substring(i - 1, i));
            //pals.add(scanner.next());
        }

        int res = getPalsCharacters(pals, n);
        System.out.println(res);
    }

    private static int getPalsCharacters(LinkedList<String> pals, int n) {
        int midIndex = n / 2;
        int count = 0;
        int i = midIndex - 1;
        int j = midIndex + 1;
        while (j < pals.size() || i >= 0) {
            if (i != 0 && j != pals.size() - 1) {
                if (pals.get(i).equals(pals.get(j))) {
                    i--;
                    j++;
                }
            } else if (i == 0) {
                if (pals.get(i).equals(pals.get(j))) {
                    i--;
                    j++;
                }
            }
            if (i > 0 && j < n - 1) {
                //equal ? ++ --
                //unequal add
            } else if (i == 0) {

            }
//            if (i >= 0 && j <= n - 1) {
//                if (!pals.get(i).equals(pals.get(j))) {
//                    pals.add(j, pals.get(i));
//                    count++;
//                } else {
//                    i--;
//                    j++;
//                }
//            } else if (j == n) {
//                pals.add(j, pals.get(i));
//                count++;
//                i--;
//                j++;
//            } else {
//                count++;
//                pals.add(i, pals.get(j));
//                j++;
//                i--;
//            }
        }
        return count;
    }
}
