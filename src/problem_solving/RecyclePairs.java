package problem_solving;



import algorithms.IceCreamParlour;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 11/03/18.
 */
public class RecyclePairs {

    /*
     * Complete the uniqueRecycledPairs function below.
     */
    static long uniqueRecycledPairs(int[] A) {
        /*
         * Return the number of unique recycled pairs present in the array.
         */
        List<Integer> inputArray = Arrays.stream(A).boxed().collect(Collectors.toList());
        Map<Long, List<Inputs>> sizedInputMap = getSizedMap(inputArray);
        long uniquePairs = processMap(sizedInputMap);
        return uniquePairs;
    }

    private static long processMap(Map<Long, List<Inputs>> sizedInputMap) {
        long pairsCount = 0;
        for (Map.Entry<Long, List<Inputs>> entry : sizedInputMap.entrySet()) {
            Set<IceCreamParlour.Pair<Long, Long>> pairs = processInputs(entry.getKey(), entry.getValue());
            pairsCount = pairsCount + pairs.size();
        }
        return pairsCount;
    }

    private static Set<IceCreamParlour.Pair<Long, Long>> processInputs(Long size, List<Inputs> values) {
        //Map<Long, problem_solving.Inputs> intSet = values.stream().collect(Collectors.toMap(i -> i.getValue(), i -> i));
        Map<Long, Inputs> intSet = new HashMap<>();
        for(Inputs input : values) {
            intSet.put(input.getValue(), input);
        }
        Set<IceCreamParlour.Pair<Long, Long>> pairIns = new HashSet<>();
        Set<Long> rotations;
        for (Inputs input : values) {
            if (input.getRightHighRotations().isEmpty()) {
                rotations = getRotations(input.getValue(), size);
                input.setRightHighRotations(rotations);
            } else {
                rotations = input.getRightHighRotations();
            }
            for (Long rotation : rotations) {
                if (intSet.containsKey(rotation)) {
                    IceCreamParlour.Pair<Long, Long> pair = new IceCreamParlour.Pair<>(input.getValue(), intSet.get(rotation).getValue());
                    pairIns.add(pair);
                    populateRotations(intSet.get(rotation), rotations);
                }
            }
        }
        return pairIns;
    }

    private static void populateRotations(Inputs inputs, Set<Long> rotations) {
        Set<Long> highRotations = new HashSet<>();
        for (Long rotation : rotations) {
            if(rotation > inputs.getValue()) {
                highRotations.add(rotation);
            }
        }
        inputs.setRightHighRotations(highRotations);
    }

    private static Map<Long, List<Inputs>> getSizedMap(List<Integer> inputArray) {
        Map<Long, List<Inputs>> sizedMap = new HashMap<>();
        for (Integer input : inputArray) {
            long size = getSize(input);
            Inputs inputs = new Inputs();
            inputs.setValue(input);
            inputs.setRightHighRotations(new HashSet<>());
            if (sizedMap.containsKey(size)) {
                sizedMap.get(size).add(inputs);
            } else {
                List<Inputs> newList = new ArrayList<>();
                newList.add(inputs);
                sizedMap.put(size, newList);
            }
        }
        return sizedMap;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int n = Integer.parseInt(scan.nextLine().trim());
        int[] A = new int[n];
        String[] AItems = scan.nextLine().split(" ");
        for (int AItr = 0; AItr < n; AItr++) {
            int AItem = Integer.parseInt(AItems[AItr].trim());
            A[AItr] = AItem;
        }
        long result = uniqueRecycledPairs(A);
        System.out.println(result);
        //bw.write(String.valueOf(result));
        //bw.newLine();
        //bw.close();
    }

    private static long getSize(long p) {
        int c = 1;
        if(p<10) {
            return 1;
        } else {
            p = p / 10;
            while (p >= 10) {
                p = p / 10;
                c++;
            }
            c++;
            return c;
        }
    }

    private static Set<Long> getRotations(long p, long size) {
        long original = p;
        Set<Long> rotations = new HashSet<>();
        do {
            long q = p / 10;
            long r = p % 10;
            r = (int) (r * (Math.pow(10, size - 1)));
            p = r + q;
            if (p > original)
                rotations.add(p);
        } while (p != original);
        return rotations;
    }
}

class Inputs {
    long value;
    Set<Long> rightHighRotations;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Set<Long> getRightHighRotations() {
        return rightHighRotations;
    }

    public void setRightHighRotations(Set<Long> rightHighRotations) {
        this.rightHighRotations = rightHighRotations;
    }
}
