package interviews.amazon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KPopularKeywords {

    public static void main(String[] args) {
        Set<String> w = Stream.of("anacell", "cetracular", "betacellular").collect(Collectors.toSet());
        int k = 2;
        String review = "Anacell provides the best services in the city, betacellular has awesome services, Best services provided by anacell, everyone should use anacell";
        List<String> res = getKMostPopularKeywords(review, k, w);
        for (int i = 0; i < k; i++) {
            System.out.println(res.get(i));
        }
    }

    public static List<String> getKMostPopularKeywords(String review, int k, Set<String> words) {
        Map<String, Integer> fre = new HashMap<>();

        String[] r = review.split("\\W+");
        for (String s : r) {
            if (words.contains(s.toLowerCase())) {
                fre.putIfAbsent(s, 0);
                fre.put(s, fre.get(s) + 1);
            }
        }

        PriorityQueue<String> q = new PriorityQueue<String>((s1, s2) -> {
            return fre.get(s1) - fre.get(s2);
        });

        for (Map.Entry<String, Integer> s : fre.entrySet()) {
            if (q.size() < k) {
                q.offer(s.getKey());
            } else {
                if (fre.get(q.peek()) < fre.get(s.getKey())) {
                    q.poll();
                    q.offer(s.getKey());
                }
            }
        }

        List<String> res = new ArrayList<>();
        while (!q.isEmpty()) {
            res.add(q.poll());
        }

        return res;
    }
}
