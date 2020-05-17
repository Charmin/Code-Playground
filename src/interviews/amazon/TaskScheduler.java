package interviews.amazon;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 */
public class TaskScheduler {

    public static void main(String[] args) {
        char[] ta = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        int res = leastInterval(ta, n);
        System.out.println(res);
    }

    public static int leastInterval(char[] tasks, int n) {
        int[] ch = new int[26];
        for (char c : tasks) {
            ch[c - 'A'] = ch[c - 'A'] + 1;
        }

        PriorityQueue<Task> pq = new PriorityQueue<>((p, q) -> {
            int c = q.count - p.count;
            if (c == 0) {
                return p.rank - q.rank;
            }
            return c;
        });

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] > 0) {
                char ca = (char) ('A' + i);
                pq.offer(new Task(ca, ch[i]));
            }
        }

        int res = 0;
        while (!pq.isEmpty()) {
            ArrayList<Task> q = new ArrayList<>();
            int i = 0;
            while (i <= n) {
                if (!pq.isEmpty()) {
                    if (pq.peek().count > 1) {
                        Task temp = pq.poll();
                        q.add(new Task(temp.t, temp.count - 1)); //add for later, the next round of n's
                    } else {
                        pq.poll();
                    }
                }
                res++;
                if (pq.isEmpty() && q.isEmpty()) { //This ensures the idle times are added
                    break;
                }
                i++;
            }
            pq.addAll(q);
        }

        return res;
    }
}

class Task {
    char t;
    int count;
    int rank;

    public Task(char t, int count) {
        this.t = t;
        this.count = count;
        rank = 0;
    }

    public Task(char t, int count, int rank) {
        this.t = t;
        this.count = count;
        this.rank = rank;
    }

    public void setCount(int t) {
        this.count = t;
    }

}
