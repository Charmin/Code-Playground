package interviews;

public class Test {
    public static void main(String[] args) {
        int[] k = {7,6,4,3,1};
        System.out.println(maxProfit(k));
        System.out.println(solution(-1,3,3,1));
        System.out.println(solution(2, 2, 2, -3));
    }

    public static int maxProfit(int[] prices) {
        int gmin = Integer.MAX_VALUE;
        int gmax = Integer.MIN_VALUE;

        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            int cur = prices[i];

            gmax = Math.max(gmax, cur);

            if (cur > gmin) {
                maxProfit = Math.max(maxProfit, cur - gmin);
            }

            gmin = Math.min(gmin, cur);

        }
        return maxProfit;
    }

    public static String solution(int AX, int AY, int BX, int BY) {
        double slope = Double.MAX_VALUE;
        if (BX != AX) {
            slope = (double) (BY - AY) / (BX - AX);
        }
        // the slope of the new line
        boolean isClockWise = isClockwise(AX, AY, BX, BY);

        double x1 = isClockWise ? BX - 1 : BX + 1;
        double y1 = slope == Double.MAX_VALUE ? BY : slope * x1;

        int x = (int) x1;
        int y = (int) y1;

        String result = String.format(x+","+y);
        return result;
    }

    private static boolean isClockwise(int AX, int AY, int BX, int BY) {
        int p =  AX * BY - AY * BX;
        return p < 0;
    }

}
