package datastructures;

/**
 * Created by chaitra.kr on 14/10/18.
 */
public class ReverseLinkedList {

    public static Node<Integer> head;

    public static void main(String[] args) {
        LinkedPList<Integer> singleList = new LinkedPList();
        singleList.insert(3);
        singleList.insert(4);
        singleList.insert(5);
        singleList.insert(6);
        singleList.insert(8);
        singleList.insert(9);
        System.out.println("Before Reverse");
        singleList.display();
        System.out.println();
//        Node<Integer> newHead = reverIterative(singleList.head);
//        LinkedPList<Integer> revered = new LinkedPList<>();
//        revered.head = newHead;
//        revered.display();
        Node<Integer> subHead = reverseSublist(singleList, 2, 3);
        LinkedPList<Integer> revered = new LinkedPList<>();
        revered.head = subHead;
        revered.display();
    }


    private static Node<Integer> reverIterative(Node<Integer> current) {
        Node oldHead = current;
        Node prev = null, curDup = null;
        if (oldHead.getNext() == null) {
            return oldHead;
        }
        current = current.getNext();
        while (current.getNext() != null) {
            curDup = current; //
            current = current.getNext();
            curDup.setNext(prev);
            prev = curDup; //prev is old current
        }
        curDup = current;
        curDup.setNext(prev);
        Node newHead = new Node();
        newHead.setNext(curDup);
        return newHead;
    }

    private static Node<Integer> reverseSublist(LinkedPList<Integer> list, int start, int end) {
        Node<Integer> cur = list.head;
        Node<Integer> curShadow = null;
        Node<Integer> next = null;
        Node<Integer> startNode = null;
        Node<Integer> prevStart = null;
        int count = 0;
        while (count != end) {
            curShadow = cur;
            cur = cur.getNext();
            count++;
            if (count >= start) {
                if (count == start) {
                    prevStart = curShadow;
                    startNode = cur;
                } else {
                    curShadow.setNext(next); //will be set again later
                    next = curShadow;
                }
            }
        }
        if (prevStart != null) {
            prevStart.setNext(cur); //endNode
        }
        if (cur.getNext() != null) {
            startNode.setNext(cur.getNext());
        }
        if (curShadow != null) {
            cur.setNext(curShadow);
        }
        return list.head;
    }

    private static Node reverse_k_nodes(
            Node head,
            int k) {
        Node hop = head;
        Node cur = head;
        int i = 0;
        while (hop != null && cur.getNext() != null) {
            cur = cur.getNext();
            i++;
            if (i == k-1) {
                reverse(hop, cur);
                hop = cur.getNext();
                i = 0;
            }
        }
        return head;
    }

    public static void reverse(Node start, Node end) {

        Node cur = start.getNext();
        Node prev = start;
        Node curNext = cur.getNext();

        while (cur.getData() != end.getData()) {
            cur.setNext(prev);
            prev = cur;
            cur = curNext;
            curNext.setNext(cur);
        }
    }
}
