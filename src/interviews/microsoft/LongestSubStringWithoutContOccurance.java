package interviews.microsoft;


/**
 * Given a string s containing only a and b, find longest substring of s such that s
 * does not contain more than two contiguous occurrences of a and b.
 */
public class LongestSubStringWithoutContOccurance {

    public static void main(String[] args) {
        String s = "aabbaaaaabb";
        String s2 = "abbaabbaaabbaaa";
        String c1 = getLongestSubstring(s);
        System.out.println(c1);
        String c2 = getLongestSubstring(s2);
        System.out.println(c2);
    }

    private static String getLongestSubstring(String ns) {
        String s = ns;
        int aC = 0;
        int bC = 0;
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < ns.length(); i++) {
            if (aC == 3 || bC == 3) {
                int len = (i - 1) - start;
                if (len > maxLen) {
                    s = ns.substring(start, i-1);
                    maxLen = len;
                }
                start = i-1;
                if (aC == 3) {
                    aC = 0;
                } else {
                    bC = 0;
                }

            }
            if (ns.charAt(i) == 'a') {
                aC++;
                bC = 0;
            }
            if (ns.charAt(i) == 'b') {
                bC++;
                aC = 0;
            }
        }
        return s;
    }
}
