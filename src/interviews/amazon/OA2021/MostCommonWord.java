package interviews.amazon.OA2021;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Find the most frequently used word, which is not listed in ignored_keywords, within a paragraph.
 * A paragraph is a single line of words that may contain punctuation marks, mixed with uppercase and lowercase letters.
 * The word comparison should not be case sensitive, and the output word is expected to be in lowercase.
 *
 * Examples
 * Example 1:
 * Input:
 * paragraph = "If this book was written today in the midst of the slew of dystopian novels that come out,it may not have stood out.
 * But, this book was way ahead of its time." ignored_keywords = ["of", "was", "the"]
 *
 * Output: "book"
 * Explanation:
 * "of" appears three times and "was", "the" appear twice, but they are in the ignored_keywords list.
 * The next most common word is "book", which appears twice.
 */
public class MostCommonWord {

    static Pattern pattern = Pattern.compile("\\W+");

    public static void main(String[] args) {

        String sent = "If this book was written in the midst of the slew of dystopian novels that come out,it may not have stood out. " +
                "But, this Book was way time ahead of its time. today";

        List<String> exclusions = Arrays.asList("of", "was", "the");
        System.out.println(mostCommonWord(sent, exclusions));
    }

    private static String mostCommonWord(String s, List<String> banned) {

        String[] splits = pattern.split(s);

        Set<String> exclusions = new HashSet<>(banned);

        Map<String, Integer> countsMap = new HashMap<>();

        for (String split : splits) {
            String lower = split.toLowerCase();
            if (!exclusions.contains(split)) {
                countsMap.put(lower, countsMap.getOrDefault(lower, 0) + 1);
            }
        }

        String mostCommon = null;
        int maxVal = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> vals : countsMap.entrySet()) {
            if (vals.getValue() > maxVal) {
                maxVal = vals.getValue();
                mostCommon = vals.getKey();
            }
        }

        return mostCommon;

    }

}
