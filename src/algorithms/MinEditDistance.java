package algorithms;

/**
 * Created by chaitra.kr on 09/11/18.
 */
public class MinEditDistance {

    public static void main(String[] args) {
        //String s1 = "dinitrophenylhydrazine";
        //String s2 = "benzalphenylhydrazone";
//        String s1 = "mmsmsphenylhydrazine";
//        String s2 = "mmsmsphenylhydrazone";
        String s1 = "horse";
        String s2 = "ros";
        System.out.println(minDistance(s1, s2));
    }

//    public static int minDistance(String word1, String word2) {
//        if (word1.length() == 0 && word2.length() == 0) {
//            return 0;
//        } else if (word1.length() == 0 && word2.length() > 0) {
//            return word2.length();
//        } else if (word1.length() > 0 && word2.length() == 0) {
//            return word1.length();
//        } else {
//            char lastChar1 = word1.charAt(word1.length() - 1);
//            char lastChar2 = word2.charAt(word2.length() - 1);
//            if (lastChar1 == lastChar2) {
//                word1 = word1.substring(0, word1.length() - 1);
//                word2 = word2.substring(0, word2.length() - 1);
//                return minDistance(word1, word2);
//            } else {
//                String w1 = word1.substring(0, word1.length() - 1);
//                String w2 = word2.substring(0, word2.length() - 1);
//                return 1 + Math.min(minDistance(w1, word2), Math.min(minDistance(word1, w2), minDistance(w1, w2)));
//            }
//        }
//    }

    public static int minDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int sol[][] = new int[len1+1][len2+1];
        return editDistance(len1, len2, s1, s2, sol);
    }

    public static int editDistance(int len1, int len2, String word1, String word2, int[][] sol) {
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }

        if (sol[len1][len2] > 0) {
            return sol[len1][len2];
        }

        if (word1.charAt(len1-1) != word2.charAt(len2-1)) {
            int d1 = Math.min(editDistance(len1 - 1, len2, word1, word2, sol), editDistance(len1 - 1, len2 - 1, word1, word2, sol));
            sol[len1][len2] = 1 + Math.min(d1, editDistance(len1, len2 - 1, word1, word2, sol));
        } else {
            sol[len1][len2] = editDistance(len1 - 1, len2 - 1, word1, word2, sol);
        }

        return sol[len1][len2];
    }


}
