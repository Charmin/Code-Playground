package sorting;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

/**
 * Created by chaitra.kr on 27/09/17.
 */
public class QuickSort {
    static List<Integer> elements = new ArrayList<Integer>();

    public static void main(String[] args) {
        elements.add(10);
        elements.add(5);
        elements.add(7);
        elements.add(8);
        elements.add(3);
        elements.add(4);
        elements.add(6);
        elements.stream().forEach(a-> System.out.print("--"+a));
        System.out.println("\n");
        quickSort(0,elements.size()-1);
        elements.stream().forEach(a-> System.out.print("--"+a));
    }

    private static void quickSort(int leftIndex, int rightIndex) {
        if(leftIndex>=rightIndex)
            return;
        int p = partition(leftIndex, rightIndex);
        quickSort(leftIndex, p-1);
        quickSort(p+1, rightIndex);
    }

    private static int partition(int leftIndex, int rightIndex) {
        int pivot = leftIndex;
        int i = leftIndex+1;
        int j = rightIndex;
        while(i < j) {
            if (elements.get(i) < elements.get(pivot)) {
                i++;
            }
            if (elements.get(j) > elements.get(pivot)) {
                j--;
            }
            if(elements.get(i) > elements.get(pivot) && elements.get(j) < elements.get(pivot) && i<j) {
                int temp = elements.get(j);
                elements.set(j,elements.get(i));
                elements.set(i,temp);
            }
        }
        if((j == i) && elements.get(j) > elements.get(pivot)) {
            j--;
            int temp = elements.get(pivot);
            elements.set(pivot, elements.get(j));
            elements.set(j, temp);
        } else {
            int temp = elements.get(pivot);
            elements.set(pivot, elements.get(j));
            elements.set(j, temp);
        }

        return j;
    }
}
