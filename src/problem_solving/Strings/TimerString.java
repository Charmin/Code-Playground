package problem_solving.Strings;

/**
 * Created by chaitra.kr on 08/12/18.
 */
public class TimerString {


    public static void main(String[] args) {
        int[] ip = {0, 4, 0, 0};
        String res = largestTimeFromDigits(ip);
        System.out.println(res);
    }


    public static String largestTimeFromDigits1(int[] A) {

        char[] result = new char[5];
        if (A[0] == 0 && A[1] == 0 && A[2] == 0 && A[3] == 0) {
            result[0] = '0';
            result[1] = '0';
            result[2] = '0';
            result[3] = '0';
        }

        Result r2 = roof(2, A);
        if (r2 != null) {
            result[0] = (char) ('0' + r2.getVal());
            A[r2.getIndex()] = -1;
        } else {
            return "";
        }

        if (((int)result[0] - 48) < 2) {
            Result r3 = roof(9, A);
            if (r3 != null) {
                result[1] = (char) ('0' + r3.getVal());
                A[r3.getIndex()] = -1;
            } else {
                return "";
            }
        } else {
            Result r3 = roof(3, A);
            if (r3 != null) {
                result[1] = (char) ('0' + r3.getVal());
                A[r3.getIndex()] = -1;
            } else {
                return "";
            }
        }

        Result r1 = roof(5, A);
        if (r1 != null) {
            result[2] = (char) ('0' + r1.getVal());
            A[r1.getIndex()] = -1;
        } else {
            return "";
        }

        for (int k = 0; k < 4; k++) {
            if (A[k] >= 0) {
                result[3] = (char) ('0' + A[k]);
            }
        }

        for (int i = 4; i >= 3; i--) {
            result[i] = result[i - 1];
        }
        result[2] = ':';
        return new String(result);
    }


    private static Result roof(int n, int[] input) {
        int diff = n+1;
        int index = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] <= n) {
                int diffN = n - input[i];
                if (diffN < diff) {
                    diff = diffN;
                    index = i;
                }
            }
        }
        if (diff < n+1) {
            Result r = new Result(input[index], index);
            return r;
        } else {
            return null;
        }
    }

    private static Result getResult(int[] timer, int[] input, int k) {
        int diff = Integer.MAX_VALUE;
        int i = 0;
        int resIndex = i;
        while (i < 4) {
            if (input[i] >= 0 && input[i] <= timer[k]) {
                int diffNew = timer[k] - input[i];
                if (diffNew < diff) {
                    diff = diffNew;
                    resIndex = i;
                }
            }
            i++;
        }

        if (diff < Integer.MAX_VALUE) {
            Result r = new Result(input[resIndex], resIndex);
            return r;
        } else {
            return null;
        }
    }

    static class Result {
        private int val;
        private int index;

        public Result(int v, int i) {
            val = v;
            index = i;
        }

        public int getVal() {
            return val;
        }

        public int getIndex() {
            return index;
        }
    }

    public static String largestTimeFromDigits(int[] A) {
        for(int h = 23; h >= 0; h--) {
            for(int m = 59; m >= 0; m--) {

                boolean flag = true;
                int[] count = new int[10];

                count[h < 10 ? 0 : h / 10]++;
                count[h < 10 ? h : h % 10]++;
                count[m < 10 ? 0 : m / 10]++;
                count[m < 10 ? m : m % 10]++;


                for(int e : A) {
                    if(--count[e] < 0) {
                        flag = false;
                        break;
                    }
                }

                if(flag) return String.format("%02d:%02d", h, m);
            }
        }

        return "";
    }
}
