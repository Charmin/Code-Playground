package problem_solving;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 09/12/18.
 */
public class IsAlienSorted {

    public static void main(String[] args) {
        String[] test = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        boolean is = isAlienSorted(test, order);
        System.out.println(is);
        int[] A = {4, -2, 2, -4};
        boolean yn = canReorderDoubled(A);
        System.out.println(yn);
    }

    class Truck {

        public Truck(int max, List<Integer> ints) {
            this.max = max;
            this.ints = ints;
        }

        private int max;
        private List<Integer> ints;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public List<Integer> getInts() {
            return ints;
        }

        public void setInts(List<Integer> ints) {
            this.ints = ints;
        }
    }


    public static boolean isAlienSorted(String[] words, String order) {
        Map<Character, List<String>> dict = new HashMap<>();
        for (int j = 0; j < order.length(); j++) {
            dict.put(order.charAt(j), new ArrayList<>());
        }
        if (!words[0].isEmpty()) {
            String trimmed = words[0].substring(1, words[0].length());
            dict.computeIfAbsent(words[0].charAt(0), k -> new ArrayList<>()).add(trimmed);
        }
        for (int i = 1; i < words.length; i++) {
            if (words[i - 1].isEmpty() && !words[i].isEmpty()) {
                return true;
            } else if (!words[i - 1].isEmpty() && words[i].isEmpty()) {
                return false;
            } else {
                if (order.indexOf(words[i].charAt(0)) >= order.indexOf(words[i - 1].charAt(0))) {
                    String trimmed = words[i].substring(1, words[i].length());
                    dict.computeIfAbsent(words[i].charAt(0), k -> new ArrayList<>()).add(trimmed);
                } else {
                    return false;
                }
            }
        }
        for (Map.Entry<Character, List<String>> entry : dict.entrySet()) {
            List<String> input = entry.getValue();
            if (input != null && !input.isEmpty()) {
                String[] arr = input.stream().toArray(String[]::new);
                if (arr.length > 1) {
                    return isAlienSorted(arr, order);
                }
            }
        }

        return true;
    }

    //Doubled pairs

    public static boolean canReorderDoubled(int[] A) {
        List<Integer> ins = Arrays.stream(A).boxed().collect(Collectors.toList());
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                A[i] = -1 * A[i];
            }
        }
        Arrays.sort(A);
        int k = 0;
        int m = A.length / 2;
        while (k < A.length / 2 && m < A.length)
            if (2 * A[k] == A[m]) {
                k++;
                m++;
            } else {
                return false;
            }
        return true;

    }
}
