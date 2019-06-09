package problem_solving;

/**
 * Created by chaitra.kr on 18/11/18.
 */
public class Contests {

    public static void main(String[] args) {
        int A[] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int n = A.length;

        if (n < 3) {
            System.out.println(false);
        }

        boolean isMountain = false;
        for (int i = 1; i < n && A[i] != A[i - 1]; i++) {
            if (A[i] < A[i - 1] && !isMountain && (i - 1) > 0) {
                isMountain = true;
            } else if (isMountain) {
                if (A[i] > A[i - 1]) {
                    System.out.println(false);
                }
            }
        }
        //System.out.println(isMountain);

        int[] A1 = {5,7,3,2,1};
        System.out.println(maxWidthRamp(A1));
    }

    public static int maxWidthRamp(int[] A) {
        if (isDecreasing(A)) {
            return 0;
        }
        if (isIncreasing(A)) {
            return A.length - 1;
        }

        //int capIndex = 0;
        int maxWidth = 0;
        int lastIndex = A.length - 1;
        for (int j = 0; j < A.length; j++) {
            int k = lastIndex;
            while (k > 0 && k - j > maxWidth) {
                if (A[k] >= A[j]) {
                    maxWidth = k - j;
                }
                k--;
            }
        }
        return maxWidth;
    }

    private static boolean isIncreasing(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!(a[i] > a[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDecreasing(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!(a[i] < a[i + 1])) {
                return false;
            }
        }
        return true;
    }
}


