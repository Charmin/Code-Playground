package problem_solving;

public class RemoveDuplicates {

    public static void main(String[] args) {
        String s = "pbbcggttciiippooaais";
        String res = removeDuplicates(s, 2);
        System.out.println(res);
    }

    public static String removeDuplicates(String s, int k) {
        int sc = -1;
        int se = 0;

        if (s == null || s == "" || s.length() == 1) {
            return s;
        }

        while (se > sc) {
            sc = se;
            int i = 0;
            int j = i + 1;
            int i1 = 0;
            int c = 0;
            while (j < s.length()) {
                if (s.charAt(i) == s.charAt(j)) {
                    c++;
                    if (i1 > i) {
                        i1 = i;
                    }
                } else {
                    c = 0;
                    i1 = j;
                }
                if (c == k-1) {
                    s = s.replace(s.subSequence(i1,j+1), "");
                    se++;
                    c = 0;
                    j = i1 == 0 ? 1 : i1;
                } else {
                    j++;
                }
                i = j-1;
            }
        }
        return s;
    }
}
