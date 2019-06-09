package problem_solving;

import java.util.*;

public class SpecialPalindrome {

    public static void main(String[] args) {
        String s = "aaaa";
        List<String> res = specialPalindromes(s);
        System.out.println(res.size());
        System.out.println(res);
    }

    private static List<String> specialPalindromes(String s) {
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();
        List<String> pals = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            charIndexMap.computeIfAbsent(s.charAt(i), m -> new ArrayList<>()).add(i);
        }

        for (HashMap.Entry<Character, List<Integer>> entry : charIndexMap.entrySet()) {
            pals.addAll(processIndexes(entry.getKey(), entry.getValue(), s));
        }
        return pals;
    }

    private static List<String> processIndexes(Character key, List<Integer> value, String s) {
        List<String> palindromes = new ArrayList<>();
        Map<Character, List<Integer>> chunkLengths = new HashMap<>();
        int chunks = 0;
        //continous numbers
        for (int j = 1; j < value.size(); j++) {
            if (value.get(j) - value.get(j - 1) == 1) {
                chunks++;
            } else if (value.get(j) - value.get(j - 1) == 2) {
                palindromes.addAll(computeFromInput(value.get(j) - 1, s, key));
                if (chunks > 0) {
                    chunkLengths.computeIfAbsent(key, m -> new ArrayList<>()).add(chunks);
                }
                chunks = 0;
            }
        }
        if (chunks > 0) {
            chunkLengths.computeIfAbsent(key, m -> new ArrayList<>()).add(chunks);
        }

        if (chunkLengths.size() == 0) {
            int co = 0;
            while (co < value.size()) {
                palindromes.add(String.valueOf(key));
                co++;
            }
        }
        palindromes.addAll(processChunks(chunkLengths));
        return palindromes;
    }

    private static Collection<? extends String> processChunks(Map<Character, List<Integer>> chunkLengths) {
        List<String> palindromes = new ArrayList<>();
        for (Map.Entry<Character, List<Integer>> entry : chunkLengths.entrySet()) {
            for (Integer in : entry.getValue()) {
                List<String> comb = getPowerSet(entry.getKey(), in);
                palindromes.addAll(comb);
            }
        }
        return palindromes;
    }

    private static List<String> getPowerSet(Character key, Integer in) {
        char[] c = new char[in];
        List<String> pals = new ArrayList<>();
        Arrays.fill(c, key);
        for (int i = 0; i < (1 << in); i++) {
            String cin = "";
            for (int j = 0; j < in; j++) {
                if ((i & (1 << j)) > 0) {
                    cin = cin.concat(String.valueOf(c[j]));
                }
            }
            if (!cin.trim().isEmpty())
                 pals.add(cin);
        }
        return pals;
    }

    private static List<String> computeFromInput(int index, String s, Character key) {
        List<String> results = new ArrayList<>();
        int i = index - 1;
        int j = index + 1;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j) && s.charAt(i) == key) {
                results.add(s.substring(i, j + 1));
            }
            i--;
            j++;
        }
        return results;
    }
}
