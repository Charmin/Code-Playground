package datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by chaitra.kr on 30/10/18.
 */

/* This can be done using merge sort as well, but has space complexity O(n) even when k << n */
public class MergeKSortetArrays {

    public static void main(String[] args) {
        List<Integer> arr1 = IntStream.of(3, 4, 8).boxed().collect(Collectors.toList());
        List<Integer> arr2 = IntStream.of(6, 7, 10, 11).boxed().collect(Collectors.toList());
        List<Integer> arr3 = IntStream.of(9, 13).boxed().collect(Collectors.toList());

        List<List<Integer>> kSortedArrays = Stream.of(arr1, arr2, arr3).collect(Collectors.toList());
        List<Integer> merged = mergeSorted(kSortedArrays);
        merged.forEach(m -> System.out.print(m+ " "));
        Set<Integer> ints = new HashSet<>();
        System.out.println(ints.contains(null));
    }

    private static List<Integer> mergeSorted(List<List<Integer>> kSortedArrays) {

        List<Integer> sorted = new ArrayList<>();
        int k = kSortedArrays.size();
        int index = 0;
        int kIndex = 0;
        Heap minHeap = new Heap();
        int size = kSortedArrays.stream().mapToInt(l -> l.size()).max().getAsInt();
        while (index < size) {
            if (index < kSortedArrays.get(kIndex).size()) {
                minHeap.put(kSortedArrays.get(kIndex).get(index));
            }
            kIndex++;
            if (kIndex == k) {
                kIndex = 0; //reset
                index++;
                if (!minHeap.isEmpty()) {
                    sorted.add(minHeap.getMin());
                    minHeap.remove(minHeap.getMin());
                }
            }
        }

        while (!minHeap.isEmpty()) {
            sorted.add(minHeap.getMin());
            minHeap.remove(minHeap.getMin());
        }
        return sorted;
    }
}
