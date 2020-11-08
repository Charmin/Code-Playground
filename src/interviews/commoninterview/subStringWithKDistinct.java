package interviews.commoninterview;

import java.util.LinkedHashMap;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 */
public class subStringWithKDistinct {

    public static void main(String[] args) {
        String s = "accaabbooo";
        int res = lengthOfLongestSubstringKDistinct(s, 3);
        System.out.println(res);
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int i = 0;
        int j = 0;

        int maxLen = Integer.MIN_VALUE;
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }
        LinkedHashMap<Character, Integer> indexMap = new LinkedHashMap<>(k+1);

        while (j < s.length()) {
            if (indexMap.containsKey(s.charAt(j))) {
                indexMap.remove(s.charAt(j));
            }
            indexMap.put(s.charAt(j), j);
            if (indexMap.keySet().size() == k + 1) {
                maxLen = Math.max(maxLen, j - i);
                char leftMostChar = indexMap.keySet().iterator().next();
                i = indexMap.get(leftMostChar) + 1;
                indexMap.remove(leftMostChar);
            }
            j++;
        }

        return maxLen;
    }
}
