package datastructures;

/**
 * Created by chaitra.kr on 16/10/18.
 */
public class Stack {

    int[] store;
    int top;
    int[] maxSoFar;
    int capacity;

    private boolean Empty() {
        return maxSoFar.length == 0;
    }

    public Stack() {
        this.capacity = Integer.MAX_VALUE;
    }

    public Stack(int capacity) {
        this.capacity = capacity;
        store = new int[capacity];
        top = 0;
        maxSoFar = new int[capacity];
    }

    public void push(int d) {
        if (top == 0) {
            maxSoFar[top] = d;
            store[top++] = d;
        } else if (top < capacity) {
            store[top] = d;
            maxSoFar[top] = d > maxSoFar[top - 1] ? d : maxSoFar[top - 1];
            top++;
        } else {
            System.out.println("Stack full!!");
        }
    }

    public int pop() {
        if (top == 0) {
            System.out.println("Stack empty!!");
        }
        top--;
        maxSoFar[top] = -1;
        int data = store[top];
        return data;
    }

    public int getMax() {
        int index = top - 1;
        return maxSoFar[index];
    }
}
