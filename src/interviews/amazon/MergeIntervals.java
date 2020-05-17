package interviews.amazon;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MergeIntervals {
    public static void main(String[] args) {
        merge(new int[][] {{1,3},{2,6},{8,10},{15,18}});
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        LinkedList<int[]> mint = new LinkedList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int[] val : intervals) {
            q.add(val);
        }

        while (!q.isEmpty()) {
            int[] sec = q.poll();
            //first time, or when there is not overlap.
            if (mint.isEmpty() || mint.getLast()[1] < sec[0]) {
                mint.add(sec);
            } else {  //overlap the start points are anyway sorted. check the end points alone
                mint.getLast()[1] = Math.max(mint.getLast()[1], sec[1]);
            }
        }

        return mint.toArray(new int[mint.size()][]);
    }
}
