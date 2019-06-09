package InterviewBit.strings;

import java.util.*;
import java.util.regex.Pattern;

public class JustifyText {

    public static void main(String[] args) {
        //["This", "is", "an", "example", "of", "text", "justification."
        //List<String> input = Arrays.asList("This", "is", "an", "example", "of", "text", "justification.");
        Pattern p = Pattern.compile("^[aeiouAEIOU]*");
        String ex = "ahsvdf";
        //boolean g = ex.matches("[aeiouAEIOU](.*)");
        boolean g = ex.matches("^[ah][a-z]+]");
        System.out.println("Matches " + g);
        List<String> input = Arrays.asList("analgar");

        Map<Integer, String> indexToString = new HashMap<>();
        int L = 16;
        for (int i = 0; i < input.size(); i++) {
            if (i == 0) {
                indexToString.put(i, input.get(i));
            } else {
                indexToString.put(i, " " + input.get(i));
            }
        }

        List<String> res = format(input, L, indexToString);
        for (String r : res) {
            r.matches("[aeiouAEIOU]]");
            System.out.println(r);
        }
    }

    private static List<String> format(List<String> input, int l, Map<Integer, String> indexToString) {
        List<String> result = new ArrayList<>();
        int curStart = 0;
        int curEnd = 0;
        int i = 1;
        int length = input.get(0).length();

        if (length > l) {
            return result;
        }


        while (i < input.size()) {
            length += indexToString.get(i).length();
            if (length > l) {
                curEnd = i - 1;
                formatLine(result, l - (length - indexToString.get(i).length()), indexToString, curStart, curEnd, false);
                curStart = i;
                indexToString.put(i, indexToString.get(i).trim());
                length = indexToString.get(i).length();
            }
            if (i == input.size() - 1) { //lastline
                curEnd = i;
                formatLine(result, l - length, indexToString, curStart, curEnd, true);
            }
            i++;
        }
        return result;
    }

    private static void formatLine(List<String> result, int diff, Map<Integer, String> indexToString, int curStart, int curEnd, boolean isLast) {
        StringBuilder r = new StringBuilder();
        if (!isLast) {
            if (diff == 0) {
                for (int i = curStart; i <= curEnd; i++) {
                    r.append(indexToString.get(i));
                }
            } else {
                int i = curStart + 1;
                r.append(indexToString.get(curStart));
                while (diff > 0 && i <= curEnd) {
                    StringBuilder s = new StringBuilder(indexToString.get(i));
                    s.insert(0, " ");
                    indexToString.put(i, s.toString());
                    diff--;
                    i++;
                    if (i > curEnd) i = curStart + 1;
                }
                for (int j = curStart + 1; j <= curEnd; j++) {
                    r.append(indexToString.get(j));
                }
            }
        } else {
            for (int i = curStart; i <= curEnd; i++) {
                r.append(indexToString.get(i));
            }
            while (diff > 0) {
                r.append(" ");
                diff--;
            }
        }
        result.add(r.toString());
    }
}
