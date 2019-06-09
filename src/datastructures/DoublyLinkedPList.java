package datastructures;


/**
 * Created by chaitra.kr on 14/10/18.
 */
public class DoublyLinkedPList<T> {

    DNode<T> head;

    public void insert(T data) {
        DNode<T> head = new DNode();
        DNode<T> node = new DNode();
        node.setData(data);
        if (head.getNext() != null) {
            DNode<T> cur = head;
            while (cur.getNext() != null) {
                cur = cur.getNext();
            }
            cur.setNext(node);
            node.setPrev(cur);
        } else { //first node
            head.setNext(node);
            node.setPrev(head);
        }
    }

}
