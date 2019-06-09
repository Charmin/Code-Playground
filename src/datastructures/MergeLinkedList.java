package datastructures;

/**
 * Created by chaitra.kr on 13/10/18.
 */
public class MergeLinkedList {

    public static void main(String[] args) {

        LinkedPList listOne = new LinkedPList();
        listOne.insert(2);
        listOne.insert(5);
        listOne.insert(7);
        LinkedPList listTwo = new LinkedPList();
        listTwo.insert(3);
        listTwo.insert(11);
        LinkedPList merged = mergeList(listOne, listTwo);
        merged.display();
    }

    private static LinkedPList mergeList(LinkedPList listOne, LinkedPList listTwo) {
        Node<Integer> n1 = listOne.head.getNext();
        Node<Integer> n2 = listTwo.head.getNext();
        LinkedPList<Integer> merged = new LinkedPList();
        merged.head = new Node();
        Node<Integer> m = merged.head;
        while (n1 != null && n2 != null) {
            if (n1.getData() < n2.getData()) {
                Node cur = n1;
                m.setNext(cur);
                n1 = n1.getNext();
                m = m.getNext();
            } else {
                Node cur = n2;
                m.setNext(cur);
                n2 = n2.getNext();
                m = m.getNext();
            }
        }
        drainNodes(n2, m);
        drainNodes(n1, m);
        return merged;
    }

    private static void drainNodes(Node from, Node to) {
        while (from != null) {
            Node cur = from;
            to.setNext(cur);
            from = from.getNext();
        }
    }
}
