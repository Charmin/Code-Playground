package arrays;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JustifyText {

    public static void main(String[] args) {
        //["This", "is", "an", "example", "of", "text", "justification."
        List<String> input = Arrays.asList("This", "is", "an", "example", "of", "text", "justification.");
        Pattern p = Pattern.compile("^[aeiouAEIOU]*");
        String ex = "ahsvdf";
        //boolean g = ex.matches("[aeiouAEIOU](.*)");
        boolean g = ex.matches("^[ah][a-z]+]");
        System.out.println("Matches " + g);
        char[] arr = ex.toCharArray();
        arr[0] = 'k';
        LinkedList<String> j = new LinkedList<>();
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

        int[] a1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] a2 = {2,1,4,3,9,6};
        int[] result = relativeSortArray(a1, a2);
        for (int h : result) {
            System.out.println(h);
        }

        int[] kun = {3,4,4,8};
        int ksn = unboundedKnapsack(9, kun);
        System.out.println("KnapSack unbounded: "+ksn);
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        LinkedList<Integer> lin = Arrays.stream(arr2).boxed().collect(Collectors.toCollection(LinkedList::new));
        Map<Integer, Integer> counts = new HashMap<>();

        for (int i = 0 ; i < arr1.length; i++) {
            if (counts.containsKey(arr1[i])) {
                int v = counts.get(arr1[i])+1;
                counts.put(arr1[i], v);
            } else {
                counts.put(arr1[i], 1);
            }
        }

        for (int j = 0; j < arr2.length; j++) {
            if (counts.get(arr2[j]) != null && counts.get(arr2[j]) > 1) {
                int c = counts.get(arr2[j]);
                while (c > 1) {
                    int index  = lin.indexOf(arr2[j]);
                    lin.add(index, arr2[j]);
                    c--;
                }
            }
            counts.remove(arr2[j]);
        }

        List<Integer> remains = new ArrayList<>();
        for (Map.Entry<Integer, Integer> m : counts.entrySet()) {
            for (int k = 0 ; k < m.getValue(); k++) {
                remains.add(m.getKey());
            }
        }
        Collections.sort(remains);
        lin.addAll(remains);


        int[] res = lin.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).toArray();
        return res;
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

    static int unboundedKnapsack(int k, int[] arr) {
        List<Integer> arrs = IntStream.of(arr).boxed().collect(Collectors.toList());
        Collections.sort(arrs, (a,b) -> { return b-a;});
        int[] sorted = arrs.stream().mapToInt(Integer::intValue).toArray();
        int res = knap(k, sorted, 0);
        return res;
    }

    private static int knap(int k, int[] arr, int maxSoFar) {
        for (int i = 0; i < arr.length; i++) {
//            if (maxSoFar == k) {
//                return maxSoFar;
//            }
            maxSoFar += arr[i];
            if (maxSoFar <= k) {
                maxSoFar = Math.max(maxSoFar, knap(k, arr, maxSoFar));
                if (maxSoFar == k) {
                    return maxSoFar;
                }
            }
            maxSoFar -= arr[i];

        }
        return maxSoFar;
    }
}
