package datastructures;

/**
 * Created by chaitra.kr on 13/10/18.
 */

class Node<T> {

    private T data;
    private Node next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}

