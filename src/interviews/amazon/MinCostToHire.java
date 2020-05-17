package interviews.amazon;

import java.util.*;

public class MinCostToHire {

    public static void main(String[] args) {
        int[] quality = {4,4,4,5};
        int[] wage = {13,12,13,12};
        int K = 2;

        System.out.println(mincostToHireWorkers(quality,wage, K));
        System.out.println("done");
    }


    public static double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        List<double[]> qualArray = new ArrayList<>();
        for (int i = 0; i < wage.length; i++) {
            double[] dl = { (double) wage[i]/quality[i], quality[i]};
            qualArray.add(dl);
        }

        qualArray.sort(Comparator.comparingDouble(a -> a[0]));

        PriorityQueue<double[]> p = new PriorityQueue<>((a,b) -> (int) (b[1] - a[1]));

        double totalCost = Double.MAX_VALUE;
        double sumQ = 0.0;
        for (double[] q : qualArray) {
            p.add(q);
            sumQ += q[1];
            if (p.size() == K) {
                double[] maxQ = p.remove();
                totalCost = Math.min(totalCost, q[0] * sumQ);
                sumQ -= maxQ[1];
            }
        }
        return totalCost;
    }

    private static double getMinWage(List<double[]> temp, double[] maxWage) {
        double minWage = Integer.MIN_VALUE;
        for (double[] t : temp) {
            double min = t[0]/maxWage[0] * maxWage[1];
            if (min < t[1]) { //if the relative salary is lesser than my demand
                min =  ((maxWage[0] * t[1]) / t[0]) - maxWage[1] + maxWage[1];
            }
            minWage = Math.max(min, minWage);

        }
        return minWage;
    }
}
