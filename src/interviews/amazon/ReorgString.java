package interviews.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorgString {

    public static void main(String[] args) {
        String s = "aaab";
        char c = 'a' + 1;
        System.out.println(c);
        String res = reorganizeString(s);
        System.out.println(res);
    }

    public static String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.putIfAbsent(S.charAt(i), 0);
            map.put(S.charAt(i), map.get(S.charAt(i)) + 1);
        }

        PriorityQueue<Item> pq = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        for (Map.Entry<Character, Integer> m : map.entrySet()) {
            pq.offer(new Item(m.getKey(), m.getValue()));
        }

        Item cur = pq.poll();
        Item prev;

        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            if (cur.count > 0) {
                builder.append(cur.c);
            }
            int c = cur.count - 1;
            prev = cur;
            cur = pq.poll();
            if (c > 0) {
                if (cur == null) {
                    return "";
                }
                prev.setCount(c);
                pq.offer(prev);
            }
        }
        return builder.toString();
    }
}

class Item {
    char c;
    int count;

    Item(char c, int count) {
        this.c = c;
        this.count = count;
    }

    public void setCount(int c) {
        this.count = c;
    }
}