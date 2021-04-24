package interviews.amazon.OA2021;

import java.util.Arrays;
import java.util.List;

/**
 * A risk modeling system uses a scaling computing system that implements an autoscale policy depending on the current load or utilization of the computing system.

    The system starts with a number of computing instances given by instances. The system polls the instances every second to see the average utilization at that second, and performs scaling as described below. Once any action is taken, the system will stop polling for 10 seconds.
    During that time, the number of instances does not change.

    Average utilization > 60%: Double the number of instances if the doubled value does not exceed 2 * 10^8. This is an action. If the number of instances exceeds this limit on doubling, perform no action.

    Average utilization < 25%: Halve the number of instances if the number of instances is greater than 1 (take ceil if the number is not an integer). This is also an action. If the number of instances is 1, take no action.

    25% <= Average utilization <= 60%: No action.

    Given an array of the values of the average utilization at each second for this system, determine the number of instances at the end of the time frame.

    For example, the system starts with instances = 2, and average utilization is given as averageUtil = [25, 23, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 76, 80].

    At the first second, utilization is 25, so no action is taken.

    At the second second, averageUtil[1] = 23 < 25, so instances = 2 / 2 = 1. The next 10 seconds, averageUtil[2]..averageUtil[11], no polling is done.

    At averageUtil[11] = 76, 76 > 60 so the number of instances is doubled. There are no more readings to consider and 2 is the final answer.

    Example 1:
    Input: averageUtil=[5, 10, 80], instances = 1
    Output: 2
    Explanation:
    Here instance = 1 and averageUtil = [5, 10, 80]. At the 1st and 2nd seconds of the time period, no action will be taken because the utilization is less than 25%, the number of instance is 1. At the 3rd second, the number of instances will double to 2.

    Constraints:
    1 <= instances <= 10^5
    1 <= n <= 10^5
    1 <= averageUtil[i] <= 100

*/
public class AutoScalePolicyUtilCheck {

    public static void main(String[] args) {
        List<Integer> utils = Arrays.asList(5, 10, 80);
        System.out.println(autoScale(utils, 1));
    }

    public static int autoScale(List<Integer> averageUtils, int numInstances) {

        int curTime = 0;
        int instances = numInstances;

        for (int t = 0; t < averageUtils.size(); t++) {

            if (curTime > t + averageUtils.size()) {
                break;
            }

            int util = averageUtils.get(curTime);

            if (util < 25) {
                int cur = instances;
                instances = scaleDown(instances);
                curTime = cur != instances ? curTime + 10 : curTime + 1;
            }

            if (util > 60) {
                int cur = instances;
                instances = scaleUp(instances);
                curTime = cur != instances ? curTime + 10 : curTime + 1;
            }
        }

        return instances;
    }

    private static int scaleUp(int instances) {
        if ((instances * 2L) >= (2L * 100000000)) {
            return instances;
        }
        return instances * 2;
    }

    private static int scaleDown(int instances) {
        if (instances > 1) {
            instances = (int) Math.ceil(instances / 2.0);
        }
        return instances;
    }


}
