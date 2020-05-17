package interviews.commoninterview;

import java.util.*;

/**
 * 1. Can directly use the LinkedHashMap
 * 2. Implement a custom double linked list along with HashMap
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache l = new LRUCache(1);
        l.put(2, 1);
        l.put(3, 2);
        System.out.println(l.get(3));
        System.out.println(l.get(2));
        l.put(4, 3);
        System.out.println(l.get(2));
        System.out.println(l.get(3));
        System.out.println(l.get(4));
        System.out.println(l.cache);
    }

    Map<Integer, DoubleLinkNode> cache = new HashMap<>();
    final static DLinkedList dList = new DLinkedList();
    private int maxCap;

    public LRUCache(int capacity) {
        this.maxCap = capacity;
    }

    public int get(int key) {
        DoubleLinkNode resNode = cache.get(key);
        int res = resNode != null ? resNode.val : -1;
        if (res >= 0) {
            dList.remove(resNode);
            dList.insert(resNode);
        }
        return res;
    }

    public void put(int key, int value) {
        if (cache.size() == maxCap) {
            if (!cache.containsKey(key)) { //new value
                DoubleLinkNode lru = dList.head;
                dList.remove(lru);
                cache.remove(lru.key);
            }
        }

        DoubleLinkNode node = new DoubleLinkNode(key, value);
        dList.insert(node);
        cache.put(key, node);
    }
}

class DoubleLinkNode {
    int key;
    int val;
    DoubleLinkNode prev;
    DoubleLinkNode next;

    public DoubleLinkNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

}

class DLinkedList {
    static DoubleLinkNode head;
    static DoubleLinkNode tail;

    public DoubleLinkNode insert(int key, int val) {
        DoubleLinkNode dn;
        if (this.head == null) {
            this.head = new DoubleLinkNode(key, val);
            this.tail = this.head;
        } else {
            dn = new DoubleLinkNode(key, val);
            this.tail.next = dn;
            dn.prev = tail;
            this.tail = dn;
        }
        return this.head;
    }

    public void remove(DoubleLinkNode node) {
        if (node.prev != null && node.next != null) { //neither head nor tail
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } else {  //one of them, or both are null
            if (node.prev != null) { //this is tail
                node.prev.next = null;
                this.tail = node.prev;
            } else if (node.next != null) { //this is head
                this.head = node.next;
                node.next.prev = null;
            } else { //both are null
                this.head = null;
                this.tail = head;
            }
        }
        node.prev = null;
        node.next = null;
    }

    public void insert(DoubleLinkNode val) {
        if (this.head == null) {
            this.head = val;
            this.tail = this.head;
        } else {
            this.tail.next = val;
            val.prev = this.tail;
            this.tail = val;
        }
    }

}
