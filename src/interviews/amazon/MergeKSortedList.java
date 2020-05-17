package interviews.amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedList {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(l1 -> l1.val));

        for (ListNode l : lists) {
            if (l!=null) {
                pq.add(l);
            }
        }

        ListNode head = new ListNode(-1);
        ListNode cur = head;

        //O(n * logK)
        while (!pq.isEmpty()) {
            ListNode p = pq.poll();
            cur.next = new ListNode(p.val);
            cur = cur.next;
            p = p.next;
            if (p != null) {
                pq.add(p);
            }
        }

        return head.next;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}