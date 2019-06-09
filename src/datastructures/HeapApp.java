package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 28/10/18.
 */
public class HeapApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ops = scanner.nextInt();

        Heap heap = new Heap(100);
        for (int i = 0; i < ops; i++) {
            int op = scanner.nextInt();
            if (op != 3) {
                int element = scanner.nextInt();
                if (op == 1) {
                    heap.put(element);
                } else if (op == 2) {
                    heap.remove(element);
                }
            } else {
                System.out.println(heap.getMin());
            }
        }

    }
}
