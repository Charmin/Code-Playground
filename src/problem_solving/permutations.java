package problem_solving;

import java.util.*;

public class permutations {

    public static void main(String[] args) {
        String s = "AAC";

//        List<String> result = getAllPermutations(s);
//        result.stream().forEach(s1 -> System.out.println(s1));

        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> colArr = new LinkedList<>();
        nQueens(0, 4, results, colArr);
        System.out.println(results);
    }

    private static List<String> getAllPermutations(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder(s);
        getPermutation(0, sb, res);
        return res;
    }

    private static void getPermutation(int i, StringBuilder s, List<String> res) {

        if (i >= s.length() - 1) {
            res.add(s.toString());
            return;
        }

        for (int j = i; j < s.length(); j++) {
            if (!(i != j && s.charAt(i) == s.charAt(j))) {
                swap(s, i, j);
                getPermutation(i + 1, s, res);
                swap(s, i, j); // set it back to original
            }
        }

    }

    private static void swap(StringBuilder s, int i, int j) {
        char temp = s.charAt(i);
        s.setCharAt(i, s.charAt(j));
        s.setCharAt(j, temp);
    }

    private static void nQueens(int row, int n, List<List<Integer>> results, LinkedList<Integer> colArr) {

        if (row == n) {
            LinkedList<Integer> copy = new LinkedList<>();
            copy.addAll(colArr);
            results.add(copy);
            return;
        }

        for (int i = 0; i < n; i++) {
            colArr.add(i);
            if (isValid(colArr)) {
                nQueens(row + 1, n, results, colArr);
            }
            colArr.removeLast();
            Set<Integer> in = new HashSet<>();
        }

    }

    private static boolean isValid(LinkedList<Integer> colArr) {
        int cur = colArr.size() - 1;
        for (int i = 0; i < cur; i++) {
            int diff = Math.abs(colArr.get(i) - colArr.get(cur));
            if (diff == 0 || diff == cur - i) {
                return false;
            }
        }
        return true;
    }



}
