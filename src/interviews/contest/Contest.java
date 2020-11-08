package interviews.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contest {

    public static void main(String[] args) {
        int[][] mat = {{56216}, {63251}, {75772}, {1945}, {27014}};
        List<Integer> luck = luckyNumbers(mat);
        //System.out.println("done");
        String c = "xy";
        System.out.println(c.substring(1));
        System.out.println("er*/et".substring(0,0).isEmpty());
        System.out.println((int) Math.ceil((double) 88 / 26));
        CustomStack obj = new CustomStack(3);
        obj.push(2);
        obj.push(4);
        obj.push(3);
        obj.push(5);
        int param_2 = obj.pop();
        obj.increment(3,100);
        //System.out.println();
    }

    public static List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> colMax = new HashMap<>();
        Map<Integer, Integer> rowMax = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                colMax.put(j, Math.max(colMax.get(j) != null ? colMax.get(j) : Integer.MIN_VALUE, matrix[i][j]));
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[i].length; j++) {
                rowMax.put(i, Math.min(rowMax.get(i) != null ? rowMax.get(i) : min, matrix[i][j]));
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (rowMax.get(i).equals(colMax.get(j)) && matrix[i][j] == colMax.get(j)) {
                    res.add(rowMax.get(i));
                }
            }
        }

        return res;

    }

    static class CustomStack {
        private int[] bag;
        private int top;
        private int kp;

        public CustomStack(int maxSize) {
            bag = new int[maxSize];
            top = -1;
            kp = 0;
        }

        public void push(int x) {
            if (top + 1 < bag.length) {
                bag[top + 1] = x;
                top++;
            }
        }

        public int pop() {
            if (top >= 0) {
                return bag[top--];
            }
            return top;
        }

        public void increment(int k, int val) {
            if (top >= 0) {
                while (kp < k) {
                    bag[kp] = bag[kp] + val;
                    kp++;
                }
            }
            kp = 0;
        }
    }
}
