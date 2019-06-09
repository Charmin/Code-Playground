package problem_solving;

import java.util.*;

/**
 * Created by chaitra.kr on 02/05/18.
 */
public class LookAndSay {

    private static Map<Integer, Integer> numToCount = new HashMap<>();
    private static List<Integer> input = new ArrayList<>();

    public static void main(String[] args) {
        initialise(numToCount);
        Scanner scanner = new Scanner(System.in);
        String start = scanner.nextLine();
        String n = scanner.nextLine();
        String result = lookAndSayWithR(start, n);
        System.out.println(result);
    }

    private static void initialise(Map<Integer, Integer> numToCount) {
        for (int i = 0; i < 10; i++) {
            numToCount.put(i, 0);
        }
    }

    private static String lookAndSayWithR(String s, String count) {
        int c = 0;
        String result = null;
        while (c < Integer.valueOf(count)) {
            result = lookAndSay(s);
            s = result;
            c++;
        }
        return result;
    }

    private static String lookAndSay(String s) {
        int number = Integer.valueOf(s);
        reset(numToCount);
        input.clear();
        if (number >= 10) {
            do {
                int remainder = number % 10;
                incrementCount(remainder);
                input.add(remainder);
                number = number / 10;
            } while (number >= 10);
        }
        input.add(number);
        incrementCount(number);

        String result = readResult();
        return result;
    }

    private static void reset(Map<Integer, Integer> numToCount) {
        for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
            entry.setValue(0);
        }
    }

    private static String readResult() {
        StringBuilder builder = new StringBuilder();
        if (input.size() > 1) {
            for (int end = input.size() - 1; end >= 0; end--) {
                builder.append(numToCount.get(input.get(end)));
                builder.append(input.get(end));
            }
        }
        return builder.toString();
    }

    public static void incrementCount(int num) {
        int count = numToCount.get(num);
        count++;
        numToCount.put(num, count);
    }
}
