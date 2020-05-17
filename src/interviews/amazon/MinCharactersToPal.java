package interviews.amazon;

/**
 * Return the minimum characters that are needed to be inserted to make the string a palindrome string.
 */
public class MinCharactersToPal {

    public static void main(String[] args) {
        String s = "ABC";
        String arg = getPalString(s);
        int len = arg.length() - s.length();
        System.out.println(len);
    }

    //This works but index out of bounds for some input
    private static String getPalString(String s) {
        if (s.length() == 1) {
            return s;
        }

        int i = 0;
        int j = s.length() - 1;

        String ns = s;
        if (s.charAt(i) != s.charAt(j)) {
            String sub = s.substring(i, j);
            ns = String.valueOf(s.charAt(j)).concat(getPalString(sub)).concat(String.valueOf(s.charAt(j)));
        } else {
            i++;
            j--;
            String sub = s.substring(i, j + 1);
            if (i <= j) {
                String n = getPalString(sub);
                if (!n.equals(sub)) { //the inner part wasn't a pal
                    sub = s.substring(i - 1, j + 1);
                    if (!ns.equals(sub)) { //the inner part wasn't a pal
                        ns = String.valueOf(s.charAt(j)).concat(getPalString(sub)).concat(String.valueOf(s.charAt(j)));
                    }
                } else {
                    ns = String.valueOf(s.charAt(i)).concat(n).concat(String.valueOf(s.charAt(j)));
                }
            }
        }

        return ns;
    }


    private int getMinPal(String s) {
        int[] lps = new int[s.length()];
        lps[0] = 0;
        int len = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(len) == s.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[len-1];
                }
            }
        }

        return lps[s.length()-1];
    }


}
