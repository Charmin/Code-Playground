package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 05/07/18.
 */
public class CountingInversions {

    private static final Scanner scanner = new Scanner(System.in);
    private static long result;
    private static long[] sorted;
    private static long[] tempArray;

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(scanner.nextLine());
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(scanner.nextLine());
            //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            tempArray = new long[n];
            sorted = new long[n];
            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
                tempArray[i] = arrItem;
            }

            doInversions(0, arr.length - 1, arr);
            System.out.println(result);
            //bufferedWriter.write(String.valueOf(result));
            //bufferedWriter.newLine();
        }
        //bufferedWriter.close();
        scanner.close();
    }

    private static void doInversions(int low, int high, int[] arr) {
        if (low < high) {
            int mid = (low + high) / 2;
            doInversions(low, mid, arr);
            doInversions(mid + 1, high, arr);
            result = result + countInversionsNow(low, mid, high, arr);
        }
    }

    private static int countInversionsNow(int low, int mid, int high, int[] arr) {
        int i = low;
        int j = mid + 1;
        int count = 0;

        int[] left = new int[mid + 1 - low];
        int[] right = new int[high - mid];
        int k = 0;
        while (i < mid + 1) {
            left[k] = arr[i];
            k++;
            i++;
        }
        int size1 = k;
        k = 0;
        while (j <= high) {
            right[k] = arr[j];
            k++;
            j++;
        }
        int size2 = k;

        i = 0;
        j = 0;
        k = low;

        while (i < size1 && j < size2) {
            if (left[i] <= right[j]) {
                sorted[k] = left[i];
                i++;
                k++;
            } else {
                count = count + ((mid + 1 + j) - k);
                sorted[k] = right[j];
                j++;
                k++;
            }
        }

        while (i < size1) {
            sorted[k] = left[i];
            k++;
            i++;
        }

        while (j < size2) {
            sorted[k] = right[j];
            k++;
            j++;
        }
        int s = 0;
        while (s < k) {
            arr[s] = (int) sorted[s];
            s++;
        }
        return count;
    }


//    private static void countInversions(int low, int mid, int high) {
//        for (int p = 0; p < ; p++) {
//            tempArray[p] = sorted.get(p);
//        }
//        int i = low;
//        int j = mid + 1;
//        int k = low;
//        while (i <= mid && j <= high) {
//            if (tempArray[i] <= tempArray[j]) {
//                addIfNotPresent(sorted, k, tempArray[i]);
//                i++;
//            } else {
//                addIfNotPresent(sorted, k, tempArray[j]);
//                j++;
//                result = result + mid + 1 - i;
//            }
//            k++;
//        }
//        while (i <= mid) {
//            addIfNotPresent(sorted, k, tempArray[i]);
//            k++;
//            i++;
//        }
//
//        while (j <= high) {
//            addIfNotPresent(sorted, k, tempArray[j]);
//            k++;
//            j++;
//        }
//
//    }

    private static void addIfNotPresent(List<Long> sorted, int k, long i) {
        if (sorted.size() > k && sorted.get(k) != 0) {
            sorted.set(k, i);
        } else {
            sorted.add(k, i);
        }
    }

    // Complete the countInversions function below.
    static int[] countInversionsNew(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        int i = 0;
        while (i < arr.length - 1 && arr[i] <= arr[i + 1]) {
            i++;
        }
        if (i != arr.length - 1) {
            int k = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = k;
            result++;
            System.out.println(i);
            countInversionsNew(arr);
        }

        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        int k = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = k;
    }


}
