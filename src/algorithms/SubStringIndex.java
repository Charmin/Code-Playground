package algorithms;

import java.util.*;

import static java.lang.Integer.max;

/**
 * Created by chaitra.kr on 03/05/18.
 * <p>
 * Uses Boyer Moore
 */
public class SubStringIndex {

    private static Map<Integer, Integer> lastIndexMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queryOne = Integer.valueOf(scanner.nextLine());
        List<Integer> arrayOne = new ArrayList<>();
        List<Integer> arrayTwo = new ArrayList<>();
        for (int i = 0; i < queryOne; i++) {
            int n = Integer.valueOf(scanner.nextLine());
            arrayOne.add(n);
        }
        int queryTwo = Integer.valueOf(scanner.nextLine());

        for (int i = 0; i < queryTwo; i++) {
            int n = Integer.valueOf(scanner.nextLine());
            arrayTwo.add(n);
        }

        preComputeLastOccurrance(arrayTwo);
        int index = getStartIndex(arrayOne, arrayTwo);
        System.out.println(index);
    }

    private static void preComputeLastOccurrance(List<Integer> arrayTwo) {
        for (int i = 0; i < 10; i++) {
            lastIndexMap.put(i, -1);
        }
        for (int i = 0; i < arrayTwo.size(); i++) {
            lastIndexMap.put(arrayTwo.get(i), i);
        }
    }

    private static int getStartIndex(List<Integer> input, List<Integer> pattern) {
        int patternEnd = pattern.size() - 1;
        int sizeDiff = input.size() - pattern.size();
        int startIndex = -1;
        int endIndex = 0;
        int leap = 0;
        for (int k = 0; k < sizeDiff; k = +leap) {
            leap = 0;
            for (int m = patternEnd; m >= 0; m--) {
                startIndex = k + m;
                if (pattern.get(m) != input.get(k + m)) {
                    leap = max(1, m - lastIndexMap.get(input.get(k + m)));
                    break;
                }
            }
            if (leap == 0) {
                break;
            }
        }
        return startIndex;
    }
}
