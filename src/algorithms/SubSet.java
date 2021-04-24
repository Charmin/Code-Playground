package algorithms;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.SyncFailedException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.pow;

/**
 * Created by chaitra.kr on 11/02/17.
 */
public class SubSet {

    /* IMPORTANT: Multiple classes and nested static classes are supported */
        public static void main(String args[]) throws Exception {

            //Scanner
            Scanner s = new Scanner(System.in);
            int N = s.nextInt();
            List<Integer> inputSet = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                int input = s.nextInt();
                inputSet.add(input);
            }

            List<List<Integer>> listOfSubSets = getAllSubSets(inputSet);
//            listOfSubSets
//                    .stream()
//                    .forEach(System.out::println);

            int dum = productAndSum(listOfSubSets);
            int mod = (int) (Math.pow(10,9));
            //double mod = (Math.pow(10,9));

            System.out.println(mod+7);

            System.out.println(dum % (mod+7));

        }

    private static int productAndSum(List<List<Integer>> listOfSubSets) {

       int count =listOfSubSets.stream()
                .filter(m -> m.size()>0)
                .map(p->p.stream()
                        .reduce(1,(q,r) -> (q*r))
                    )
               .reduce(0,(m,n) -> (m+n));

        return count;

    }

    private static List<List<Integer>> getAllSubSets(List<Integer> inputs) {

            List<List<Integer>> subsets = new ArrayList<>();
            int n = inputs.size();

            for(int i=0;i<(1<<n); i++){
                List<Integer> subset = new ArrayList<>();
                for (int j=0;j<n;j++) {
                    if((i&(1<<j)) > 0) {
                        subset.add(inputs.get(j));
                    }
                }
                subsets.add(subset);
            }
            return subsets;
        }
}


