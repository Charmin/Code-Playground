package problem_solving;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Notebook {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] arr = new int[len];
        Set<Integer> originals = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
            originals.add(arr[i]);
        }

        int k = scanner.nextInt();
        int res = getRevisions(arr, k, originals);

        System.out.println(res);
    }

    private static int getRevisions(int[] arr, int k, Set<Integer> originals) {
        int origMax = Arrays.stream(arr).boxed().max(Integer::compareTo).get();
        int min = 0;
        int[] result = new int[arr.length];
        int[] arr2 = new int[arr.length];
        while (min <= origMax) {
            for (int i = 0;i<arr.length;i++) {
                arr2[i] = arr[i] * k;
                if (originals.contains(arr2[i])) {
                    result[i]++;
                }
            }
            min = Arrays.stream(arr2).boxed().min(Integer::compareTo).get();
            arr = arr2;
            arr2 = new int[arr.length];
        }
        int res = Arrays.stream(result).boxed().max(Integer::compareTo).get();
        res+=1;
        return res;
    }


}
