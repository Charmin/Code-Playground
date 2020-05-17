package interviews.commoninterview;

public class SubarraySumK {

    public static void main(String[] args) {
        int[] w = {1,1,1};
        int sum = subarraySum(w, 2);
        System.out.println(sum);
        int[] m = {-2, -1};
        int maxSubArraySum = maxSubArray(m);
        System.out.println(maxSubArraySum);
    }

    private static int subarraySum(int[] nums, int k) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int res = 0;

        int sum = nums[0];
        if (sum == k) {res++;}

        for (int q = 1; q < n; q++) {
            sum = nums[q];
            if (sum == k) res++;
            for (int p=q-1; p>=0; p--) {
                sum = sum + nums[p];
                if (sum == k) {res++;}
            }
        }

        return res;
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] cumSum = new int[nums.length];
        cumSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cumSum[i] = cumSum[i-1] + nums[i];
        }

        int s = -1;
        int e = -1;
        int maxSum = cumSum[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = cumSum[i] - cumSum[j];
                if (temp > maxSum) {
                    s = nums[j] < 0 ? j+1: j;
                    e = i;
                    maxSum = temp;
                }
            }
        }

        return maxSum;
    }
}
