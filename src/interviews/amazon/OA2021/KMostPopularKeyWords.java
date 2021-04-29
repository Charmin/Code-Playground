package interviews.amazon.OA2021;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Find the keywords that are most frequently mentioned in a given list of text snippets.
 * Return a list of the top k most frequently mentioned keywords, sorted in increasing order by their frequency, ingnoring case sensitivity.
 *
 * A "mention" of a keyword happens when the keyword appears at least once in a text snippet.
 * If a keyword appears more than once in a snippet, it only counts as one "mention". Sort alphabetically if multiple keywords are mentioned the same number of times.
 *
 * Input
 * k: a number
 * keywords: a list of words
 * snippets: a list of text snippets, each containing a single contiguous paragraph
 *
 * Output
 * A list of words sorted from most frequenly mentioned to least frequently mentioned.
 *
 * Examples
 * Example 1:
 * Input:
 * k = 2
 *
 * keywords = ["gatsby", "american", "novel"]
 * snippets = [
 *   "Classic. Yes. The great American novel. Hmph, so I heard.",
 *   "Most American high school students are assigned to read this novel.",
 *   "The Great Gatsby is often described as a paean to the Great American Dream",
 * ]
 * Output: ["american", "novel"]
 * Explanation:
 * american is mentioned 3 times and novel is mentioned 2 times.
 *
 * Examples
 * Example 2:
 * Input:
 * k = 2
 *
 * keywords = ["gatsby", "american", "novel"]
 * snippets = [
 *   "The opening of The Great Gatsby -- its first 3-4 pages -- ranks among the best of any novel in the English language.",
 *   "It is masterful, some may say the great American novel.",
 *   "The Great Gatsby is a 1925 novel written by American author F. Scott Fitzgerald",
 * ]
 * Output: ["novel", "american"]
 * Explanation:
 * novel appears 3 times, but both american and gatsby appear twice. However, we must only return one of those, and american appears first alphabetically.
 */
public class KMostPopularKeyWords {

    public static void main(String[] args) {

    }

    static Pattern pattern = Pattern.compile("\\W");
    public static List<String> getKMostPopularKeywords(String review, int k, Set<String> words) {
        Map<String, Integer> fre = new HashMap<>();

        String[] r = pattern.split(review);

        for (String s : r) {
            String lower = s.toLowerCase();
            if (words.contains(lower)) {
                fre.put(lower, fre.getOrDefault(lower, 0) + 1);
            }
        }

        PriorityQueue<String> q = new PriorityQueue<String>((s1, s2) -> {
            if (fre.get(s1).equals(fre.get(s2))) {
                return s1.compareTo(s2);
            }
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
