package algorithms;

import java.util.*;

/**
 * Created by chaitra.kr on 11/11/18.
 */
public class LongestNonDecreasingSubSequence {

    public static void main(String[] args) {

        //int[] input = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        int[] input = {10,9,2,5,3,4};
        Arrays.sort(input);
        int n = input.length - 1;
        List<LinkedList<Integer>> lis = new ArrayList<>();
        List<Integer> lisFirst = new LinkedList<>();
        lisFirst.add(input[0]);
        lis.add((LinkedList<Integer>) lisFirst);
        //int max = getMaxLIS(input, lis);
        int m = getSimpleLis(input);
        Boolean b = (Boolean) null;
        System.out.println(b);
        System.out.println(m);
    }

    public static int getMaxLIS(int[] input, List<LinkedList<Integer>> lis) {
        for (int j = 1; j < input.length; j++) {
            int i = j - 1;
            TreeMap<Integer, LinkedList<Integer>> sizeToList = new TreeMap<>();
            while (i >= 0) {
                if (lis.get(i) != null) {
                    LinkedList<Integer> localLis = lis.get(i);
                    if (localLis.size() > 0) {
                        if (localLis.getLast() <= input[j]) {
                            sizeToList.put(localLis.size(), localLis);
                        }
                    }
                }
                i--;
            }
            LinkedList<Integer> localLisNew = new LinkedList<>();
            if (!sizeToList.isEmpty())
                localLisNew.addAll(sizeToList.get(sizeToList.lastKey()));
            localLisNew.add(input[j]);
            lis.add(localLisNew);
        }

        int max = 0;
        for (List<Integer> list : lis)
        {
            if (list.size() > max) {
                max = list.size();
            }
        }

        return max;
    }

    public static int getSimpleLis(int input[]) {
        int[] lisLength = new int[input.length];
        lisLength[0] = 1;
        for (int i = 1; i < input.length; i++) {
            lisLength[i] = 1;
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                        lisLength[i] = Math.max(lisLength[i], lisLength[j] +1);
                    }
                }
        }
        int max = 0;
        for (int i =0;i<lisLength.length;i++) {
            if(lisLength[i] > max) {
                max = lisLength[i];
            }
        }
        return max;
    }

}
