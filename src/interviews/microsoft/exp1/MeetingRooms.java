package interviews.microsoft.exp1;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 */
public class MeetingRooms {
    public static void main(String[] args) {
        int[][] bookings = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        int rooms = minMeetingRooms(bookings);
        System.out.println(rooms);
    }

    private static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;

        PriorityQueue<int[]> bookQ = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
        for (int[] b : intervals) {
            bookQ.offer(b);
        }

        PriorityQueue<Integer> ends = new PriorityQueue<>();

        int[] meet1 = bookQ.poll();
        ends.offer(meet1[1]);
        while (!bookQ.isEmpty()) {
            int[] meet2 = bookQ.poll();
            if (meet2[0] >= ends.peek()) {
                ends.poll();
            }
            ends.offer(meet2[1]);
        }
        return ends.size();
    }


}
