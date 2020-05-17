package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxEvents {

    public static void main(String[] args) {
        int[][] e = new int[][] {{1,10},{2,2},{2,2},{2,2},{2,2}};

        int m = maxEvents(e);
        System.out.println(m);
    }

    public static int maxEvents(int[][] events) {
        List<int[]> sorted = new ArrayList<>();
        if (events.length == 1) {
            return 1;
        }


        for (int[] e : events) {
            sorted.add(e);
        }
        sorted.sort((a,b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int day = 1;
        Map<Integer, int[]> attend = new HashMap<>();
        int count = 0;
        for (int[] eve : sorted) {
            int d = bookAndReturnNextFreeSlot(eve, attend, day);
            day = Math.max(day, d);
            if (d != -1) {
                count++;
            }
        }
        return count;
    }

    private static int bookAndReturnNextFreeSlot(int[] event,  Map<Integer, int[]> attend, int d) {
        if (d >= event[0]) {
            for (int i = d; i <= event[1]; i++) {
                if (!attend.containsKey(i)) {
                    attend.put(i, event);
                    return i + 1;
                }
            }
        }
        return -1;
    }

}
