package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 15/03/17.
 */
public class PalIndex {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            String inQuery = in.next();
            StringBuilder inputString = new StringBuilder(inQuery);
            System.out.println("Length" + inQuery.length());
            List<Long> check = new ArrayList<>();
            boolean isPal = isThisPal(inputString, check);
            if (isPal)
                System.out.println("-1");
            else {
                List<Long> index = getThatIndex(inQuery, check);
                System.out.println("Check: " + check.size());
                if (index.size() > 0) {
                    System.out.println(index.get(0));
                } else {
                    System.out.println("oops!! " + index.size());
                }
            }
        }
    }

    private static List<Long> getThatIndex(String inQuery, List<Long> check) {

        StringBuilder builder = new StringBuilder(inQuery);
        List<Long> indexList = new ArrayList<>();
        int n = inQuery.length();

        for (int i = 0; i < check.size(); i++) {
            boolean found = false;
            System.out.print(" here : " + check.get(i));
            if (check.get(i) == 0) {
                StringBuilder temp1 = new StringBuilder(builder);
                temp1.deleteCharAt(i);
                if (isThisPal(temp1, new ArrayList<>())) {
                    System.out.print(" Pal :");
                    indexList.add((long) i);
                    found = true;
                } else {
                    StringBuilder temp2 = new StringBuilder(builder);
                    temp2.deleteCharAt(n - 1 - i);
                    if (isThisPal(temp2, new ArrayList<>())) {
                        System.out.print(" Pal :");
                        indexList.add((long) n - 1 - i);
                        found = true;
                    }
                }
            }
            if (found) break;
        }

        return indexList;
    }

    private static boolean isThisPal(StringBuilder inQuery, List<Long> check) {

        int p2 = inQuery.length() - 1;
        boolean isPal = true;
        for (int i = 0; i < (inQuery.length() / 2); i++) {
            if (inQuery.charAt(i) != inQuery.charAt(p2 - i)) {
                isPal = false;
                check.add((long) 0);
            } else
                check.add((long) 1);
        }
        return isPal;
    }
}