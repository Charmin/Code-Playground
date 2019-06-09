package problem_solving;

/**
 * Created by chaitra.kr on 09/03/19.
 */
public class window_size {

    public static void main(String[] args) {


        int[] arr = {-8, 2, 3, -6, 10};
        int k = 2;
        int[] res = new int[arr.length];

        int i = 0;
        int j = k - 1;
        boolean found = false;

        for (int h = 0; h < arr.length; h++) {
            if (h <= j && arr[h] < 0 && !found) {
                found = true;
                res[i] = arr[h];
                i++;
            }

            if (h == j) {
                if (found == false) {
                    res[i] = 0;
                    i++;
                }
                found = false;
                j = j + (k - 1);
            }
        }

        for (int l = 0; l < res.length; l++) {
            System.out.println(res[l]);
        }
    }

    private static int getNegatives(int[] arr, int k, int start, int[] result) {
        return 0;
    }

}
