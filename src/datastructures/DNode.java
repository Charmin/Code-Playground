package datastructures;

/**
 * Created by chaitra.kr on 14/10/18.
 */
public class DNode<T> {

    private T data;
    private DNode prev;
    private DNode next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DNode getPrev() {
        return prev;
    }

    public void setPrev(DNode prev) {
        this.prev = prev;
    }

    public DNode getNext() {
        return next;
    }

    public void setNext(DNode next) {
        this.next = next;
    }
}
