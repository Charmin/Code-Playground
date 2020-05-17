package interviews.contest;

import java.util.*;
import java.util.stream.Collectors;

public class StringMatchArray {

    public static void main(String[] args) {
        String[] words = {"mass", "as", "hero", "superhero"};
        Map<String, int[]> prefixTables = getPrefixTab(words);
        Map<String, Integer> firstLetterMap = new HashMap<>();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            int index = i > 0 ? words[i - 1].length() : i;
            firstLetterMap.put(words[i], index);
            s.append(words[i]);
        }

    }

    public List<String> stringMatching(String[] words) {
        List<String> sorted = Arrays.stream(words).sorted((o1, o2) -> {
            if (o1.length() < o2.length()) {
                return 1;
            } else if (o1.length() > o2.length()) {
                return 0;
            } else {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());
        Set<String> st = new HashSet<>();
        for (int i = 0; i < sorted.size(); i++) {
            for (int j = i + 1; j < sorted.size(); j++) {
                if (sorted.get(j).contains(sorted.get(i))) {
                    st.add(sorted.get(i));
                }
            }
        }
        return new ArrayList<>(st);
    }

    private static Map<String, int[]> getPrefixTab(String[] words) {
        Map<String, int[]> tMap = new HashMap<>();
        for (String w : words) {
            int[] tab = getTab(w);
            tMap.put(w, tab);
        }
        return tMap;
    }

    private static int[] getTab(String w) {
        int[] p = new int[w.length()];
        int i = 0;
        p[0] = 0;

        for (int j = 1; j < w.length(); j++) {
            if (w.charAt(i) == w.charAt(j)) {
                i++;
                p[j] = p[j - 1] + 1;
            } else {
                while (i > 0 && w.charAt(i) != w.charAt(j)) {
                    i = p[i - 1];
                }
                if (w.charAt(i) == w.charAt(j)) {
                    p[j] = 1;
                }
            }
            j++;
        }
        return p;

    }
}
