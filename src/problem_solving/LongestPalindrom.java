package problem_solving;

import java.util.Arrays;

/**
 * Created by chaitra.kr on 08/04/19.
 */
public class LongestPalindrom {

   static int maxLen = 0;

    public static void main(String[] args) {
        String q = "cbbd";
        String h = longestPalindrome(q);
        System.out.println(h);

    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        int i = 1;
        int j = i-1;
        int k = i+1;
        StringBuilder res = new StringBuilder();
        String r = null;

        if (s.isEmpty() || s.equals("")) {
            String m = "\"\"";
            return m;
        }

        if(s.length() == 1) {
            return r;
        }

        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1) ? s.substring(0,2) : r;
        }

        if (s.length() <= 1) {
            return null;
        }

        while (i < len) {
            j = i-1;
            k = i+1;
            int m = 0;

            while (j >= 0 && s.charAt(i) == s.charAt(j)) {
                m = (i-j+1);
                j--;
            }

            if (m > maxLen) {
                r = s.substring(j+1, i+1);
                maxLen = m;
            }

            while (j >=0  && k < len && s.charAt(j) == s.charAt(k)) {
                m = (k -j+1);
                j--;
                k++;
            }

            if (m >= maxLen) {
                r = s.substring(j+1, k);
                maxLen = m;
            }

            i++;
        }
        return r;
    }
}
