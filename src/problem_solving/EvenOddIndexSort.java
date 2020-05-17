package problem_solving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by chaitra.kr on 19/10/18.
 */
public class EvenOddIndexSort {

    public static void main(String[] args) {
        int A[] = {4, 2, 5, 7};
        int B[] = sortArrayByParityII(A);
        int res = hammingDistance(1, 4);
        System.out.println("Hamming" + res);
        Arrays.stream(B).forEach(e -> System.out.println(e));
    }

    public static int hammingDistance(int x, int y) {
        if (x == y) {
            return 0;
        }
        int count = 0;
        while (x > 0 && y > 0) {
            int m = x & 1;
            int n = y & 1;
            if ((m & n) == 0) {
                count++;
            }
            x = x >> 1;
            y = y >> 1;
        }

        if (x > 0) {
            count += countOnes(x);
        }

        if (y > 0) {
            count += countOnes(y);
        }
        return count;
    }

    private static int countOnes(int x) {
        int c = 0;
        while (x > 0) {
            c = ((x & 1) == 1) ? c+1 : c;
            x = x >> 1;
        }
        return c;
    }

    public static int[] sortArrayByParityII(int[] A) {
        List<IndexPack> evenMisMatch = new ArrayList<>();
        List<IndexPack> oddMisMatch = new ArrayList<>();

        for (int i = 0; i < A.length; i += 2) {
            if (A[i] % 2 != 0) {
                IndexPack p = new IndexPack(i, A[i]);
                evenMisMatch.add(p);
            }
        }

        for (int i = 1; i < A.length; i += 2) {
            if (A[i] % 2 == 0) {
                IndexPack p = new IndexPack(i, A[i]);
                oddMisMatch.add(p);
            }
        }

        //evenMisMatch.size() = oddMisMatch.size()
        int e = 0;
        while (e < evenMisMatch.size()) {
            A[evenMisMatch.get(e).getIndex()] = oddMisMatch.get(e).getValue();
            A[oddMisMatch.get(e).getIndex()] = evenMisMatch.get(e).getValue();
            e++;
        }

        return A;
    }

    private static void swap(int i) {
    }

    static class IndexPack {
        int index;
        int value;

        public IndexPack(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    class Solution {
        public int minAddToMakeValid(String S) {
            int open = 0;
            int close = 0;
            int total = 0;
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                if (c == '(') {
                    open++;
                } else if (c == ')') {
                    if (open > 0) {
                        open--;
                    } else {
                        close--;
                    }
                }
            }

            total += open;
            if (close < 0) {
                close = -(close);
                total += close;
            }
            return total;
        }
    }

}
