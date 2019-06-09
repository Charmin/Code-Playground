package algorithms;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by chaitra.kr on 27/09/17.
 */
public class RunningMedian {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        List<Integer> intList = new LinkedList<>();
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
            intList.add(a[a_i]);
        }

        long st = System.currentTimeMillis();
        List<Double> medianList = getRunninMedian(intList);
        long en = System.currentTimeMillis();
        System.out.println("Time "+(en-st));
        for(Double d: medianList) {
            System.out.println(String.format("%.2f",d));
        }
    }

    private static List<Double> getRunninMedian(List<Integer> ints) {
        List<Double> medianList = new LinkedList<>();
        for(int i=1;i<=ints.size();i++) {
            medianList.add(getMedian(ints.subList(0,i)));
        }
        return medianList;
    }

    private static double getMedian(List<Integer> integers) {
        double median;
        Collections.sort(integers);
        if(integers.size()==1) { median = integers.get(0);}
        else if(integers.size()%2==0) {
            int index_n = integers.size()/2;
            int index_n1 = index_n - 1;
            median = (double)(integers.get(index_n)+integers.get(index_n1))/2;
        } else {
            int index_n = integers.size()/2;
            median = integers.get(index_n);
        }
        return median;
    }
}
