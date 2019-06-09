package datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.SyncFailedException;

/**
 * Created by chaitra.kr on 25/01/17.
 */
public class EvenOddLinkedList {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.insert(6);
        linkedList.insert(1);
        linkedList.insert(13);
        linkedList.insert(4);
        linkedList.insert(9);
        linkedList.insert(12);
        linkedList.insert(2);

        System.out.print("Input:");
        linkedList.display();

        int i =0;
        while (args.length!=0) {
            linkedList.insert(Integer.valueOf(args[i]));
            i++;
        }
        Node start = linkedList.head;
        Node evenPointer = start;
        Node realStart = start;
        evenPointer = getFirstEvenPointer(start);
        while(evenPointer!=null) {
            linkedList.shiftEvenToStart(evenPointer, start);
            if(evenPointer == start) start = evenPointer.getNext();
            evenPointer = getFirstEvenPointer(start);
        }

        System.out.print("Output:");
        linkedList.display();
        System.out.println("No more evens in the list");

    }

    private static Node getFirstEvenPointer(Node<Integer> start) {

        Node<Integer> evenPointer = null;
        while(start!=null){
            if(start.getData()%2==0) {
                evenPointer = start;
                break;
            }
            start = start.getNext();
        }

        return evenPointer;
    }

}

class LinkedList {

    Node head;

    public LinkedList() {
        head = null;
    }

    public void insert(int data) {

        Node Node = new Node();
        Node.setNext(null);
        Node.setData(data);

        if(head == null) {
            head = Node;
        }
        else {
            Node cur = head;
            while(cur.getNext()!=null) {
                cur = cur.getNext();
            }
            cur.setNext(Node);
        }
    }

    public void display() {
        Node cur = head;
        while (cur!=null) {
            System.out.print(cur.getData()+"-->");
            cur = cur.getNext();
        }
        System.out.println("null");
    }

    public void shiftEvenToStart(Node evenPointer,Node start) {

        Node<Integer> preEven = start;
        Node<Integer> realStart = head;

        if (evenPointer == start) {
            start = evenPointer.getNext();
            return;
        }

        while (preEven.getNext() != evenPointer) {
            preEven = preEven.getNext();
        }
        preEven.setNext(evenPointer.getNext());

        while(realStart!=start && realStart.getNext()!=start) {
            realStart = realStart.getNext();
        }

        if(realStart.getData()%2!=0) {
            evenPointer.setNext(realStart);
            head = evenPointer;
        } else {
            evenPointer.setNext(realStart.getNext());
            realStart.setNext(evenPointer);
        }

    }
}