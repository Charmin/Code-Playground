package problem_solving;

import java.util.Random;

/**
 * Created by chaitra.kr on 01/11/18.
 * <p>
 * <p>
 * When you get into a tight place and everything goes against you, till it seems you could not hang on a minute longer,
 * never give up then, for that is just the place and time that the tide will turn...
 */
public class KthLargest {

    static int[] arr = {3, 2, 1, 5, 4, 6};

    public static void main(String[] args) {

        int k = 2;
        int result = findKthLargest(k, arr);
        System.out.println(result);
    }

    private static int findKthLargest(int k, int[] arr) {
        Random random = new Random();
        //int rand = random.nextInt(arr.length);
        int rand = 2;
        System.out.println("rand:" + rand);
        int low = 0;
        int high = arr.length - 1;
        int len = arr.length;
        int kInverse = len - k;

        while (low <= high) {
            int index = rearrangeAndFit(low, rand);
            System.out.println("Index: "+index);
            if (index < kInverse) {
                low = index + 1;
                rand = random.nextInt(high - low + 1) + low;
                System.out.println("rand:" + (high - low + 1) + " " + rand);

            } else if (index > kInverse) {
                high = index - 1;
                rand = random.nextInt(high - low + 1) + low;
                System.out.println("rand:" + (high - low + 1) + " " + rand);

            } else {
                return arr[index]; // index == (len - k)
            }
        }
        return -1; //not found
    }

    private static int rearrangeAndFit(int low, int rand) {
        int i = low;
        int j = arr.length - 1;
        int pivotIndex = rand;
        int resultIndex = pivotIndex;

        while (i <= j) {
            if (arr[i] < arr[pivotIndex]) {
                i++;
            }

            if (arr[j] > arr[pivotIndex]) {
                j--;
            }

            if (arr[i] > arr[pivotIndex] && arr[j] < arr[pivotIndex] && i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
                j--;
            }

            if (arr[i] == arr[pivotIndex] && arr[j] > arr[i]) {
                j--;
            } else if (arr[j] == arr[pivotIndex] && arr[j] < arr[i]) {
                i++;
            } else if ((arr[i] == arr[pivotIndex] || arr[j] == arr[pivotIndex]) && arr[j] <= arr[i] && i <= j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                if (arr[i] == arr[pivotIndex]) {
                    i++;
                    resultIndex = j;
                } else {
                    j--;
                    resultIndex = i;
                }
                return resultIndex;
            }
        }

        return resultIndex;
    }
}
