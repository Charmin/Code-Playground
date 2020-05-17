package sorting;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 01/05/16.
 */
public class Comparisons {

    static Map<Integer, List<Integer>> digitArrayMap = new HashMap<>();

    public static void main(String args[]) {
        Integer[] intArray = {3, 1, 7, 5, 2};
        List<Integer> intList = Arrays.asList(intArray);
        Collections.sort(intList);
        System.out.println(intList);

        int p = 2341;
        int size = getSize(p);
        Set<Integer> rotations = getRotations(p, size);
        System.out.println("No of rotations: " + rotations.size());
        rotations.stream().forEach(r -> System.out.println(r));
    }

    private static Set<Integer> getRotations(int p, int size) {
        int original = p;
        Set<Integer> rotations = new HashSet<>();
        do {
            int q = p / 10;
            int r = p % 10;
            r = (int) (r * (Math.pow(10, size - 1)));
            p = r + q;
            if (p > original)
                rotations.add(p);
        } while (p != original);
        return rotations;
    }

    private static int getSize(int p) {
        int c = 1;
        p = p / 10;
        while (p > 10) {
            p = p / 10;
            c++;
        }
        c++;
        return c;
    }


}
