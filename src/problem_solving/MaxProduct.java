package problem_solving;

import java.util.LinkedList;

/**
 * Created by chaitra.kr on 04/05/19.
 */
public class MaxProduct {

    public static void main(String[] args) {

        int[] A = { 5, 9, 6, 8, 6, 4, 6, 9, 5, 4, 9};
        int[] leftSpecial = new int[A.length];
        int[] rightSpecial = new int[A.length];

        int i = 0;
        int j = A.length - 1;

        fillLeftSpecial(leftSpecial,A, i);
        fillRightSpecial(rightSpecial,A, j);

        int result = 0;
        for (int k = 0; k < A.length; k++) {
            result = Math.max(result, leftSpecial[k] * rightSpecial[k]);
        }

        System.out.println(result);
    }


    private static void fillLeftSpecial(int[] left, int[] A, int i) {

        LinkedList<Integer> stack = new LinkedList<>();

        int a = 0;
        while (a < A.length) {
            if (stack.isEmpty()) {
                left[a] = 0;
            }
        }

        for (int m = i; m < A.length; m++) {
            if (m == 0) {
                left[m] = 0;
            } else {
                if (A[m - 1] > A[m]) {
                    left[m] = m - 1;
                } else {
                    int h = left[m - 1];
                    while (h > 0) {
                        if (A[h] > A[m]) {
                            left[m] = h;
                            break;
                        }
                        h = left[h];
                    }
                }
            }
        }
    }

    private static void fillRightSpecial(int[] right, int[] A, int i) {
        for (int m = i; m >= 0; m--) {
            if (m < A.length - 1) {
                if (A[m + 1] > A[m]) {
                    right[m] = m + 1;
                } else {
                    int h = right[m + 1];
                    while (h > 0 && h < A.length) {
                        if (A[h] > A[m]) {
                            right[m] = h;
                            break;
                        }
                        h = right[h];
                    }
                }
            }
        }
    }
}
