package datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chaitra.kr on 28/10/18.
 */

/* Min Heap Impl */
public class Heap {

    private List<Integer> pqueue;
    private Map<Integer, Integer> elementToIndex;

    public Heap(int capacity) {
        pqueue = new ArrayList<>(capacity);
        elementToIndex = new HashMap<>();
    }

    public Heap() {
        //this.capacity = 10;
        pqueue = new ArrayList<>();
        elementToIndex = new HashMap<>();
    }

    public void put(int element) {
        if (pqueue.isEmpty()) {
            pqueue.add(0, element);
            elementToIndex.put(element, 0);
        } else {
            int pos = pqueue.size();
            pqueue.add(pos, -1);//temporary setting
            //heapify
            while (pos >= 1 && element < pqueue.get(pos / 2)) {
                pqueue.set(pos, pqueue.get(pos / 2));
                elementToIndex.put(pqueue.get(pos / 2), pos);
                pos = pos / 2;
            }
            pqueue.set(pos, element);
            elementToIndex.put(element, pos);
        }
    }

    //always remove the highest priority (max or min)
    public void remove() {
        if (pqueue.size() == 0) {
            System.out.println("Queue is empty!!");
        }
        pqueue.set(0, pqueue.get(pqueue.size() - 1));
        pqueue.remove(pqueue.size() - 1);
        int root = pqueue.get(0);
        int i = 0;
        while (root > pqueue.get((2 * i) + 1) || root > pqueue.get((2 * i) + 2)) {
            int left = pqueue.get((2 * i) + 1);
            int right = pqueue.get((2 * i) + 2);
            int temp = pqueue.get(i);
            if (pqueue.get(left) < pqueue.get(right)) {
                pqueue.set(i, pqueue.get(left));
                pqueue.set((2 * i) + 1, temp);
                root = left;
            } else {
                pqueue.set(i, pqueue.get(right));
                pqueue.set((2 * i) + 2, temp);
                root = right;
            }
        }
    }

    public void remove(int ele) {
        if (pqueue.size() == 0) {
            System.out.println("Queue is empty!!");
            return;
        }
        int index = elementToIndex.get(ele);
        if (pqueue.size() == 1) {
            if (elementToIndex.get(ele) != null) {
                pqueue.remove(index);
            }
        } else if (pqueue.size() == 2) {
            if (index == 1) {
                pqueue.remove(1);
            } else {
                pqueue.set(0, pqueue.get(1));
                elementToIndex.put(pqueue.get(1), 0);
                pqueue.remove(1);
            }
        } else { //size > 2
            int left = (2 * index) + 1;
            int right = (2 * index) + 2;
            int lastElem = pqueue.get(pqueue.size() - 1);
            pqueue.set(index, lastElem);
            elementToIndex.put(lastElem, index);
            pqueue.remove(pqueue.size() - 1);

            //heapify
            while (left < pqueue.size() || right < pqueue.size()) { //non leaf
                if (left < pqueue.size() && right < pqueue.size() && pqueue.get(index) > pqueue.get(left) && pqueue.get(index) > pqueue.get(right)) {
                    if (pqueue.get(left) < pqueue.get(right)) {
                        swap(index, left, pqueue, elementToIndex);
                        index = left;
                        left = (2 * index) + 1;
                        right = (2 * index) + 2;
                    } else {
                        swap(index, right, pqueue, elementToIndex);
                        index = right;
                        right = (2 * index) + 2;
                        left = (2 * index) + 1;
                    }
                } else if (left < pqueue.size() && pqueue.get(index) > pqueue.get(left)) {
                    swap(index, left, pqueue, elementToIndex);
                    index = left;
                    left = (2 * index) + 1;
                } else if (right < pqueue.size() && pqueue.get(index) > pqueue.get(right)) {
                    swap(index, right, pqueue, elementToIndex);
                    index = right;
                    right = (2 * index) + 2;
                } else {
                    //heapify not needed, break out
                    left = pqueue.size();
                    right = pqueue.size();
                }
            }
        }
    }

    public int getMin() { //since its a min heap
        return pqueue.size() > 0 ? pqueue.get(0) : -1;
    }

    public void swap(int a, int b, List<Integer> arr, Map<Integer, Integer> elementToIndex) {
        int temp = arr.get(a);
        arr.set(a, arr.get(b));
        elementToIndex.put(arr.get(b), a);
        arr.set(b, temp);
        elementToIndex.put(temp, b);
    }

    public void display() {
        System.out.println();
        pqueue.stream().forEach(p -> System.out.println(p));
    }

    public boolean isEmpty() {
        return pqueue.isEmpty();
    }
}
