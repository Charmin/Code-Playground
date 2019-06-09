package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by chaitra.kr on 04/03/19.
 */
public class MinLengthRope {

    public static void main(String[] args) {
        int[] ropes = new int[] {4, 3, 2, 6};

        int minLength = getMinLength(ropes);
        System.out.println(minLength);

        PriorityQueue<Integer> test = new PriorityQueue<>(1000, Comparator.reverseOrder());
        test.add(1);
        test.add(4);
        test.add(5);
        System.out.println(test.remove());
        StringBuilder b = new StringBuilder("");
        Integer.valueOf(b.charAt(0));


    }

    private static int getMinLength(int[] ropes) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ropes.length);
        for (int l : ropes) {
            priorityQueue.add(l);
        }
        int minLength = 0;
        while (priorityQueue.size() > 1) {
            int min1 = (int) priorityQueue.remove();
            int min2 = (int) priorityQueue.remove();
            int sum = min1 + min2;
            minLength+= min1+min2;
            priorityQueue.add(sum);

        }
        return minLength;
    }
}
