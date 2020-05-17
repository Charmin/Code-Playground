package algorithms.graphs;

import javafx.util.Pair;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hit", "hot", "dot", "dog", "lot", "log", "cog");
        String start = "hit";
        String end = "cog";

        ladderLength(start, end, wordList);
        System.out.println("Result >>>>>>:");
    }

    private static int ladderLength(String start, String end, List<String> wordList) {
        Map<String, List<String>> allComb = new HashMap<>();
        int len = start.length(); // all words are of equal length

        wordList.forEach(
                word -> {
                    for (int i = 0; i < word.length(); i++) {
                        String newWord = word.substring(0, i) + "*" + word.substring(i + 1, len);
                        System.out.println(newWord);
                        List<String> tranformations = allComb.getOrDefault(newWord, new ArrayList<>());
                        tranformations.add(word);
                        allComb.put(newWord, tranformations);
                    }
                }
        );
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();
        return BFS(start, end, allComb, Q, visited);
    }

    private static int BFS(String start, String end, Map<String, List<String>> allComb, Queue<Pair<String, Integer>> q, Map<String, Boolean> visited) {
        int len = start.length();
        List<List<String>> results = new ArrayList<>();
        LinkedList<String> result = new LinkedList<>();
        q.add(new Pair<>(start, 1));
        visited.put(start, true);
        while (!q.isEmpty()) {
            Pair<String, Integer> item = q.poll();
            int level = item.getValue();
            result.add(item.getKey());
            for (int i = 0; i < len; i++) {
                String newWord = item.getKey().substring(0, i) + "*" + item.getKey().substring(i + 1, len);
                List<String> combs = allComb.get(newWord);
                if (combs.contains(end)) {
                    List<String> s = new ArrayList<>();
                    s.addAll(result);
                    results.add(s);
                    result.clear();
                    return level + 1;
                }
                for (String s : combs) {
                    if (visited.get(s) == null || !visited.get(s)) {
                        q.add(new Pair<>(s, level + 1));
                        visited.put(s, true);
                    }
                }
            }
        }
        return 0;
    }


}
