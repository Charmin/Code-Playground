package problem_solving;

import java.util.*;

/**
 * Created by chaitra.kr on 01/05/18.
 */
public class StringTransform {

    private static List<Character> characterList = new ArrayList<>();
    private static int length;

    static String abbreviation(String a, String b) {
        length = b.length();
        characterList.clear();

        if (a.length() > b.length() && compareStringR(a, b)) {
            return "YES";
        } else {
            return "NO";
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            String a = in.next();
            String b = in.next();
            String result = abbreviation(a, b);
            System.out.println(result);
        }
        in.close();
    }

    private static boolean compareStringR(String a, String b) {
        int startIndex = 0;
        if (!characterList.isEmpty() && characterList.size() == length) {
            if(remainingAIsLower(a)) {
                return true;
            }
            return false;
        }
        if (Character.valueOf(a.charAt(startIndex)).equals(Character.valueOf(b.charAt(startIndex)))) {
            if (a.length() > 1 && b.length() > 1) {
                characterList.add(b.charAt(startIndex));
                a = a.substring(startIndex + 1);
                b = b.substring(startIndex + 1);
            } else {
                if (b.length() > 0) {
                   characterList.add(b.charAt(startIndex));
                }
            }
            return true && compareStringR(a, b);
        } else {
            if (!String.valueOf(a.charAt(startIndex)).equals(String.valueOf(b.charAt(startIndex))) &&
                    String.valueOf(a.charAt(startIndex)).equalsIgnoreCase(String.valueOf(b.charAt(startIndex)))) {
                a = changeCase(a, startIndex);
            } else if (Character.isLowerCase(a.charAt(startIndex))) {
                a = a.substring(startIndex + 1, a.length());
            } else {
                return false;
            }
            return true && compareStringR(a, b);
        }
    }

    private static boolean remainingAIsLower(String a) {
        boolean isLower = true;
        for (int i = 1; i< a.length();i++) {
            if(Character.isUpperCase(a.charAt(i))) {
                return false;
            }
        }
        return isLower;
    }

    private static String changeCase(String a, int i) {
        a = a.replace(a.charAt(i), Character.toUpperCase(a.charAt(i)));
        return a;
    }

}
