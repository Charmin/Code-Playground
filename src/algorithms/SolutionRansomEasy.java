package algorithms;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * Created by chaitra.kr on 18/09/17.
 */
public class SolutionRansomEasy {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        String magazine[] = new String[m];
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            magazine[magazine_i] = in.next();
        }
        String ransom[] = new String[n];
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            ransom[ransom_i] = in.next();
        }
        if(isReplicaPossibleRecur(ransom,magazine))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    private static boolean isReplicaPossibleRecur(String[] ransom, String[] magazine) {
        boolean isPresent = true;
        List<String> ransomSet = Arrays.asList(ransom);
        List<String> magazineSet = Arrays.asList(magazine);
        Map<String,Integer> groupedSetForMag = new HashMap<>();
        Map<String,Integer> groupedSetForRansom = new HashMap<>();
        List<String> replica = new ArrayList<>();
        if(magazine.length >= ransom.length) {
            groupedSetForMag = magazineSet.stream().collect(groupingBy(Function.identity(), summingInt(s->1)));
            groupedSetForRansom = ransomSet.stream().collect(groupingBy(Function.identity(), summingInt(s->1)));
            if(!compareMap(groupedSetForMag,groupedSetForRansom)) {
                isPresent = false;
            }
        } else {
            isPresent = false;
        }
        return isPresent;
    }

    private static boolean compareMap(Map<String, Integer> groupedSetForMag, Map<String, Integer> groupedSetForRansom) {
        boolean isSame = true;
        for(Map.Entry<String,Integer> entry : groupedSetForRansom.entrySet()) {
            if(!(groupedSetForMag.containsKey(entry.getKey()) && (groupedSetForMag.get(entry.getKey()).equals(entry.getValue()) || groupedSetForMag.get(entry.getKey()).intValue() > (entry.getValue()).intValue()))) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }

}
