package problem_solving;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 04/05/19.
 */
public class MergeIntervals {

    public static void main(String[] args) {

        List<Interval> intervals = new ArrayList<>();
        //A : [ (54, 75), (56, 60), (61, 86), (22, 43), (56, 87), (32, 53), (14, 81), (64, 65), (9, 42), (12, 33),
        // (22, 58), (84, 90), (27, 59), (41, 48), (43, 47), (22, 29), (16, 23), (41, 72), (15, 87), (20, 59), (45, 84),
        // (14, 77), (72, 93), (20, 58), (47, 53), (25, 88), (5, 89), (34, 97), (14, 47) ]

        intervals.add(new Interval(54, 75));
        intervals.add(new Interval(56, 60));
        intervals.add(new Interval(61, 86));
        intervals.add(new Interval(22, 43));
        intervals.add(new Interval(56, 87));
        intervals.add(new Interval(54, 75));
        intervals.add(new Interval(32, 53));
        intervals.add(new Interval(54, 75));
        intervals.add(new Interval(14, 81));
        intervals.add(new Interval(64, 65));
        intervals.add(new Interval(9, 42));
        intervals.add(new Interval(12, 33));
        intervals.add(new Interval(22, 58));
        intervals.add(new Interval(84, 90));
        intervals.add(new Interval(27, 59));
        intervals.add(new Interval(41, 48));
        intervals.add(new Interval(43, 47));
        intervals.add(new Interval(22, 29));
        intervals.add(new Interval(22, 58));
        intervals.add(new Interval(16, 23));
        intervals.add(new Interval(41, 72));
        intervals.add(new Interval(15, 87));
        intervals.add(new Interval(20, 59));
        intervals.add(new Interval(45, 84));
        intervals.add(new Interval(14, 77));
        intervals.add(new Interval(72, 93));
        intervals.add(new Interval(20, 58));
        intervals.add(new Interval(47, 53));
        intervals.add(new Interval(25, 88));
        intervals.add(new Interval(5, 89));
        intervals.add(new Interval(34, 97));
        intervals.add(new Interval(14, 47));

        intervals.sort((Interval a, Interval b) -> {
            return (a.start - b.start);
        });
        new ArrayList<Integer>(){{add(1); add(2);}};

        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        indexMap.get(2).sort((o1, o2) -> o1 - o2);

        ArrayList<Interval> result = new ArrayList<>();

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            int s1 = intervals.get(i).start;
            int e1 = intervals.get(i).end;

            if (s1 > end) {
                Interval v = new Interval(start, end);
                result.add(v);
                end = e1;
            } else {
                end = Math.max(e1, end);
            }
            start = Math.min(s1, start);
        }

        Interval v = new Interval(start, end);
        result.add(v);

    }

    static class Interval {
        int start;
        int end;

        public Interval(int a, int b) {
            start = a;
            end = b;
        }
    }
}