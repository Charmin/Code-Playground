package datastructures;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chaitra.kr on 13/10/18.
 */
class LinkedPList<T> {
    Node<T> head;

    LinkedPList() {
        head = null;
    }

    public void insert(T d) {
        boolean isExist = false;
        Node n;
        if (head != null) {
            Node<T> cur = head;
            n = cur;
            while (cur.getNext() != null) {
                cur = cur.getNext();
                if (cur.getData() == d) {
                    n = cur;
                    isExist = true;
                }
            }
            if (isExist)
                cur.setNext(n);
            else {
                n = new Node();
                n.setData(d);
                cur.setNext(n);
            }
        } else {
            head = new Node();
            n = new Node();
            n.setData(d);
            head.setNext(n);
        }
    }

    public void display() {
        if (head != null) {
            Node cur = head;
            System.out.print("head->");
            while (cur.getNext() != null) {
                cur = cur.getNext();
                System.out.print(cur.getData() + "->");
            }
            System.out.print("null");
        }
    }

    public boolean hasCycle() {
        boolean isCyclic = false;
        Set<T> traversedSet = new HashSet<>();
        if (head != null) {
            Node<T> cur = head;
            while (cur.getNext() != null) {
                cur = cur.getNext();
                if (!traversedSet.contains(cur.getData()))
                    traversedSet.add(cur.getData());
                else {
                    isCyclic = true;
                    break;
                }
            }
        }
        return isCyclic;
    }

}
