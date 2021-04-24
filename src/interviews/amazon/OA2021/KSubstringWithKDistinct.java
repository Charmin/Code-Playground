package interviews.amazon.OA2021;

import java.util.*;

/*
*
*
* */
public class KSubstringWithKDistinct {

    public static List<String> substrings(String s, int k) {
        // WRITE YOUR BRILLIANT CODE HERE
        return getSubK(s, k);
    }

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        String s = "12123";
        int k = 2;
        List<String> res = substrings(s, k);
        System.out.println(String.join(" ", res));
    }

    //For number of sub arrays, [atMost(k) - atMost(k-1)] => exactly(k)
    //I can use a count map or a ordered map
    private static List<String> getSubK(String s, int k) {
        if (k == 0 || s == null || s.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(exactlyK(s, k));
    }

    private static Set<String> exactlyK(String s, int k) {
        int i = 0;
        int j = 0;
        Map<Character, Integer> count = new HashMap<>();
        Set<String> res = new HashSet<>();

        while (j < s.length()) {
            count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);

            while (count.keySet().size() > k || (j-i+1) > k) {
                count.put(s.charAt(i), count.get(s.charAt(i)) - 1);
                if (count.get(s.charAt(i)) == 0) {
                    count.remove(s.charAt(i));
                }
                i++;
            }

            if (count.keySet().size() == k && j-i+1 == k) {
                res.add(s.substring(i, j+1));
            }

            j++;
        }

        return res;
    }
}
