package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by chaitra.kr on 26/04/19.
 */
public class KMP {

    public static void main(String[] args) {
//        String a = "vcuszhlbtpmksjleuchmjffufrwpiddgyynfujnqblngzoogzg";
//        String b = "fufrwpiddgyynfujnqblngzoogzgvcuszhlbtpmksjleuchmjf";
        String a = "aaaaab";
        String b = "aab";
        char i = '.';
        char j = '2';
        int k = 3;
        String A = "     ";
        String B = A.trim();
        System.out.println("Before" + A + "this");
        System.out.println("After" + B + "this");
        System.out.println(B.length());
        char d = 'e';
        a.trim();
        System.out.println(d < '0' || d > '9');
        Set<Character> se = new HashSet() {{
            add('d');
            add('f');
        }};
        se.stream().forEach(s -> System.out.print(s));
        System.out.println(i >= '0' && i <= '9');
        //System.out.println(isRotated);
    }

    private static boolean isSubtring(String s, String b) {
        boolean isPresent = kmp(s, b);
        return isPresent;
    }

    private static boolean kmp(String s, String b) {
        int[] lsp = getLsp(b);
        int i = 0;
        int j = 0;

        int len = s.length();
        int len2 = b.length();
        int foundIndex = -1;
        while (i < len) {
            while (j > 0 && s.charAt(i) != b.charAt(j)) {
                j = lsp[j - 1];
            }

            if (s.charAt(i) == b.charAt(j)) {
                j++;
            }

            if (j == len2) {
                foundIndex = i;
                System.out.println(foundIndex);
                return true;
            }
            i++;
        }

        return false;
    }

    private static int[] getLsp(String b) {
        int n = b.length();
        int[] lsp = new int[n];
        if (b.length() > 1) {
            int i = 1;
            while (i < n) {
                int j = lsp[i - 1];
                while (j > 0 && b.charAt(i) != b.charAt(j)) { //had suffix of length >0 which was prefix in the prev location, but not anymore
                    j = lsp[j - 1];
                }

                if (b.charAt(i) == b.charAt(j)) {
                    j++;
                }
                lsp[i] = j;
                i++;
            }
        }
        return lsp;
    }
}
