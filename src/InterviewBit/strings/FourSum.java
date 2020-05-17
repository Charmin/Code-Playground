package InterviewBit.strings;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FourSum {

    public static void main(String[] args) {
        int i = 0;
        int j = i + 1;
        int k = 4;
        ArrayList<Integer> A = (ArrayList<Integer>) Stream.of(1, 0, -1, 0, -2, 2).collect(Collectors.toList());

        A.sort((a,b) -> a-b);
        ArrayList<ArrayList<Integer>> sol = findSum(A, 4, 0, 0);
        System.out.println("done");
    }


    private static ArrayList<ArrayList<Integer>> findSum(ArrayList<Integer> A, int k, int j, int B){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (k < 2 || j > A.size()-1) { return result;}
        if (k == 2) { //2 sum
            int m = j;
            int n = A.size()-1;
            while (m < n) {
                int s = A.get(m) + A.get(n);
                if (s < B) {
                    m++;
                } else if (s > B) {
                    n--;
                } else {
                    while (m < n && A.get(m).equals(A.get(m + 1))) {
                        m++;
                    }
                    while (m < n && A.get(n).equals(A.get(n - 1))) {
                        n--;
                    }
                    m++;
                    n--;
                    ArrayList<Integer> inter = new ArrayList<>();
                    inter.add(A.get(m));
                    inter.add(A.get(n));
                    result.add(inter);
                }
            }
        } else {
            for (int i = j; i < A.size()-k+1; i++) {
                ArrayList<ArrayList<Integer>> res = findSum(A, k-1, i+1, B-A.get(i));
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(A.get(i));
                if (res != null) {
                    for (ArrayList<Integer> t : res) {
                        temp.addAll(t);
                    }
                    result.add(temp);
                    //return result;
                }
            }
            while (j+1 < A.size() && A.get(j).equals(A.get(j + 1))) {
                j++;
            }
        }

        return result;
    }
}
