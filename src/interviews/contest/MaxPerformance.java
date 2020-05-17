package interviews.contest;

import java.util.*;


/**
 * Sort array by efficiency. Since k engineers are to be chosen, use min heap.
 * Traverse through the sorted array, keep pushing the sppeds to min heap. Keep replacing the minimum speed element from the queue of size (k) as you traverse.
 * Keep track of the performance when the
 */
public class MaxPerformance {

    public static void main(String[] args) {
        int n = 3;
        int[] speed = {2,8,2};
        int[] efficiency = {2,7,1};
        int k = 2;

        System.out.println(maxPerformance(n, speed, efficiency, k));
    }

    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<int[]> speff = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] sp = new int[] {speed[i], efficiency[i]};
            speff.add(sp);
        }

        speff.sort((a, b) -> b[1] - a[1]);
        PriorityQueue<int[]> speedOp = new PriorityQueue<>((a,b) -> b[1] - a[1]);

        int maxPm = 0;
        int j = 0;
        int speedSum = 0;

        for (int[] sp : speff) {
            speedSum += sp[0];
            speedOp.add(sp);
            j++;
            maxPm = Math.max(maxPm, sp[1] * speedSum);
            if (j > k) {  //the pq has k elements so far, the effiency would also be the minimum so far.
               int[] smin = speedOp.remove();
               speedSum -= smin[0];
               j--;
            }
        }

        return (int) (maxPm % (Math.pow(10,9) + 7));
    }

}
