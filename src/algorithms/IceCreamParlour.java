package algorithms;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 05/07/18.
 */
public class IceCreamParlour {

    // Complete the whatFlavors function below.
    private static Map<Integer, Integer> creamMap = new HashMap<>();
    private static List<Integer> sorted = new ArrayList<>();
    private static int k = 0;

    static void whatFlavor(int[] cost, int money) {
        List<Integer> costs = Arrays.stream(cost).boxed().collect(Collectors.toList());
        int index = 1;
        for (Integer c : costs) {
            creamMap.put(c, index);
            index++;
        }
        sorted = costs.stream().collect(Collectors.toList());
        sortCost(0, costs.size() - 1);
        String result;
        Pair<Integer, Integer> creamIndex = getFlavors(sorted, money);
        if (creamIndex.getSecond() == creamIndex.getFirst()) {
            int first = costs.indexOf(creamIndex.getFirst()) + 1;
            int last = costs.lastIndexOf(creamIndex.getSecond()) + 1;
            result = first + " " + last;
        } else if (creamMap.get(creamIndex.getFirst()) <= creamMap.get(creamIndex.getSecond())) {
            result = creamMap.get(creamIndex.getFirst()) + " " + creamMap.get(creamIndex.getSecond());
        } else {
            result = creamMap.get(creamIndex.getSecond()) + " " + creamMap.get(creamIndex.getFirst());
        }
        System.out.println(result);
    }

    private static Pair<Integer, Integer> getFlavors(List<Integer> sorted, int money) {
        int i = 0;
        int index = -1;
        while (i < sorted.size() && index == -1) {
            if (sorted.get(i) < money) {
                int balance = money - sorted.get(i);
                index = search(balance, sorted.subList(i + 1, sorted.size()), 0, sorted.size() - (i + 2));
                if (index != -1) {
                    index += (i + 1);
                }
            }
            i++;
        }
        return new Pair<>(sorted.get(i - 1), sorted.get(index));
    }

    private static int search(int balance, List<Integer> integers, int low, int high) {

        int index = -1;
        if (integers.size() == 1) {
            if (integers.get(0) == balance) {
                return 0;
            }
        }

        if (low < high) {
            int mid = (low + high) / 2;
            if (balance < integers.get(mid)) {
                index = search(balance, integers, low, mid);
            } else if (balance > integers.get(mid)) {
                index = search(balance, integers, mid + 1, high);
            } else if (balance == integers.get(mid)) {
                return mid;
            }
        }
        return index;
    }

    private static void sortCost(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            sortCost(low, mid);
            sortCost(mid + 1, high);
            merge(low, mid, high);
        }

    }

    private static void merge(int low, int mid, int high) {
        List<Integer> temp = sorted.stream().collect(Collectors.toList());

        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (temp.get(i) <= temp.get(j)) {
                sorted.set(k, temp.get(i));
                i++;
                k++;
            } else {
                sorted.set(k, temp.get(j));
                j++;
                k++;
            }
        }

        while (i <= mid) {
            sorted.set(k, temp.get(i));
            i++;
            k++;
        }
        while (j <= high) {
            sorted.set(k, temp.get(j));
            j++;
            k++;
        }
    }

    private static Collection<? extends Integer> merge(List<Integer> firstHalf, List<Integer> secondHalf) {
        List<Integer> sorted = new ArrayList<>();
        if (firstHalf.size() == 0)
            return secondHalf;
        if (secondHalf.size() == 0)
            return firstHalf;
        int i = 0;
        int j = 0;
        while (i < firstHalf.size() && j < secondHalf.size()) {
            if (firstHalf.get(i) <= secondHalf.get(j)) {
                sorted.add(firstHalf.get(i));
                i++;
            } else {
                sorted.add(secondHalf.get(j));
                j++;
            }
        }
        while (i < firstHalf.size()) {
            sorted.add(firstHalf.get(i));
            i++;
        }
        while (j < secondHalf.size()) {
            sorted.add(secondHalf.get(j));
            j++;
        }
        return sorted;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }

    public static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }
    }

    public static void whatFlavors(int[] cost, int money) {
        List<Integer> costs = Arrays.stream(cost).boxed().collect(Collectors.toList());
        int first = -1;
        int last = -1;
        int index = 1;
        for (Integer c : costs) {
            creamMap.put(c, index);
            index++;
        }

        for (Map.Entry<Integer, Integer> entry : creamMap.entrySet()) {
            int f = entry.getKey();
            if (f < money) {
                int balance = money - f;
                if (balance == f) {
                    first = costs.indexOf(f) + 1;
                    last = costs.lastIndexOf(f) + 1;
                } else {
                    first = entry.getValue();
                    if (creamMap.get(balance) != null) {
                        last = creamMap.get(balance);
                    }
                }
            }
            if (first != -1 && last != -1) {
                break;
            }
        }

        if (first < last) {
            System.out.println(first + " " + last);
        } else {
            System.out.println(last + " " + first);
        }
    }
}
