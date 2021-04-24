package problem_solving;


import java.util.*;
import java.util.stream.Collectors;

public class AlternateSum {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] list = new int[n];
//        for (int i = 0; i < n; i++) {
//            list[i] = scanner.nextInt();
//        }
//        int[] list = {3, 7, 4, 6, 5};
//        Map<Integer, Integer> startToSum = new HashMap<>();
//        int res = getMaxAltSum(list, 0, startToSum);
//        System.out.println(res);
        int[] searchPairInput = {1, 4, 5, 3, 2};
        int pairs = pairs(2, searchPairInput);
        List<String> s = new ArrayList<>(Arrays.asList("a", "b", "c", "a"));
        List<String> d = new ArrayList<>();
        d.addAll(s);
        s.add("s");
        //s.clear();
        System.out.println("D:" + d);
        System.out.println("S:" + s);
        Set<String> set = new HashSet<>(s);
        Object s1 = null;
        String s2 = (String) s1;
        String t = "cat";
        String f = "bat";
        System.out.println("Substring: " + t.substring(0, 1));
        System.out.println(s2);
    }

    private static int getMaxAltSum(int[] input, int startIndex, Map<Integer, Integer> starToSum) {
        if (startIndex == input.length - 1) {
            return input[startIndex];
        }
        if (startIndex == input.length - 2) {
            return input[startIndex] > input[startIndex + 1] ? input[startIndex] : input[startIndex + 1];
        }

        if (starToSum.containsKey(startIndex)) {
            return starToSum.get(startIndex);
        }

        int alt = startIndex + 2;
        int next = startIndex + 1;
        int altNext = startIndex + 3;
        if (altNext < input.length) {
            int r = Math.max(input[startIndex], input[startIndex] + getMaxAltSum(input, alt, starToSum));
            int r2 = getMaxAltSum(input, next, starToSum);
            if (r > r2) {
                starToSum.put(startIndex, r);
                return r;
            } else {
                starToSum.put(next, r2);
                return r2;
            }
        } else {
            int r0 = Math.max(input[startIndex], input[startIndex] + getMaxAltSum(input, alt, starToSum));
            int r1 = Math.max(input[alt], r0);
            int r = r1;
            int r2 = input[next];
            if (r > r2) {
                starToSum.put(startIndex, r);
                return r;
            } else {
                starToSum.put(next, r2);
                return r2;
            }
        }
    }

    static int pairs(int k, int[] arr) {
        List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int pairs = 0;
        Collections.sort(arrList);
        for (int i = 0; i < arr.length - 1; i++) {
            if (searchPair(arrList, i + 1, arr.length - 1, arrList.get(i) + k)) {
                pairs += 1;
            }
        }
        return pairs;
    }

    private static boolean searchPair(List<Integer> arrList, int startIndex, int endIndex, int key) {
        int mid = (startIndex + endIndex) / 2;
        if (startIndex <= endIndex) {
            if (arrList.get(mid) == key) {
                return true;
            } else if (key < arrList.get(mid)) {
                return searchPair(arrList, startIndex, mid - 1, key);
            } else if (key > arrList.get(mid)) {
                return searchPair(arrList, mid + 1, endIndex, key);
            } else {
                return false;
            }
        }
        return false;
    }
}
