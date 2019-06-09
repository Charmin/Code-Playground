package InterviewBit.strings;

public class AmazingSubArray {

    public static void main(String[] args) {
        String in = "pGpEusuCSWEaPOJmamlFAnIBgAJGtcJaMPFTLfUfkQKXeymydQsdWCTyEFjFgbSmknAmKYFHopWceEyCSumTyAFwhrLqQXbWnXSn";
        int i = solve(in);
        System.out.println(i);
    }

    public static int solve(String A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }
        long count = 0L;
        if (A.length() == 1 && A.matches("[aeiouAEIOU](.*)")) {
            return 1;
        } else {
            long len = A.length();
            String in = A;
            int[] lsp = getLSP(in);
            for (int j = 0; j < in.length(); j++) {
                if (isVowel(in.charAt(j))) {
                    if (lsp[j] == 0) {
                        count += len;
                    } else {
                        int index = getNextZero(j + 1, lsp);
                        if (index != -1) {
                            long l = (long) index - j;
                            count += len - index;
                        }
                    }
                }
            }
        }
        int res = (int) (count % 10003);
        return res;
    }

    private static int[] getLSP(String in) {
        int[] lsp = new int[in.length()];
        int i = 1;
        int j;

        while (i < in.length()) {
            j = lsp[i - 1];
            while (j > 0 && in.charAt(j) != in.charAt(i)) {
                j = lsp[j - 1];
            }

            if (in.charAt(i) == in.charAt(j)) {
                j++;
            }
            lsp[i] = j;
            i++;
        }

        return lsp;
    }

    private static boolean isVowel(Character c) {
        Character b = Character.toLowerCase(c);
        return b == 'a' || b == 'e' || b == 'i' || b == 'o' || b == 'u';
    }

    private static int getNextZero(int j, int[] lsp) {
        for (int i = j; i < lsp.length; i++) {
            if (lsp[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
