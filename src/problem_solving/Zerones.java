package problem_solving;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by chaitra.kr on 08/02/18.
 */
public class Zerones {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < testCases; i++) {
            int len = Integer.parseInt(in.nextLine());
            int[] inputs = new int[len];
            for (int m = 0; m < len; m++) {
                inputs[m] = in.nextInt();
            }
            quickSort(0, inputs.length - 1, inputs);
            List<Integer> integer = new ArrayList<>();
            for (int k = 0; k < inputs.length; k++) {
                integer.add(inputs[k]);
            }
            results.add(integer);
            in.nextLine();
        }
        display(results);
    }

    private static void display(List<List<Integer>> inputs) {
        System.out.println();
        for (List<Integer> list : inputs) {
            list.stream().forEach(item -> System.out.print(item + " "));
            System.out.println();
        }
    }

    private static void quickSort(int i, int j, int[] inputs) {
        if (i >= j)
            return;
        int pivotIndex = pickPivot(i, j, inputs);
        quickSort(i, pivotIndex - 1, inputs);
        quickSort(pivotIndex + 1, j, inputs);
    }

    private static int pickPivot(int i, int j, int[] inputs) {
        int pivot = inputs[i];
        i = i + 1;
        while (true) {
            while (inputs[i] < pivot) {
                i++;
            }
            while (inputs[j] > pivot) {
                j--;
            }
            if (i < j && inputs[i] > inputs[j]) {
                swap(i, j, inputs);
            }
            if (i >= j)
                return j;
        }
    }

    private static void swap(int i, int j, int[] inputs) {
        int k = inputs[i];
        inputs[i] = inputs[j];
        inputs[j] = k;
    }
}
