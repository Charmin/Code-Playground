package problem_solving;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 20/06/18.
 */
public class SortSearch {

    static List<Integer> sorted = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> ints = new ArrayList<>();
        int s = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < s; i++) {
            ints.add(scanner.nextInt());
        }
        //boolean present = binarySearch(ints, 8);
        mergeSort(ints, 0, ints.size());
        sorted.stream().forEach(a -> System.out.println(a));


    }

    private static void mergeSort(List<Integer> ints, int low, int high) {
        if (low < high) {
            int midIndex = (low + high) / 2;
            List<Integer> firstHalf = ints.subList(low, midIndex); //end exclusive
            List<Integer> secondHalf = ints.subList(midIndex, high);
            mergeSort(firstHalf, 0, firstHalf.size());
            mergeSort(secondHalf, 0, secondHalf.size());
            merge(firstHalf, secondHalf);
        }
    }

    private static void merge(List<Integer> firstHalf, List<Integer> secondHalf) {
        List<Integer> merged = new ArrayList<>();
        int n = firstHalf.size() < secondHalf.size() ? firstHalf.size() : secondHalf.size();
        int index = 0;
        int f = index;
        int s = index;
        while (index < n) {
            if (firstHalf.get(f) < secondHalf.get(s)) {
                merged.add(firstHalf.get(f));
                f++;
                index++;
            } else {
                merged.add(secondHalf.get(s));
                s++;
                index++;
            }
        }
        sorted = merged;
    }



}
