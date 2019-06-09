package problem_solving;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MinuteToWin {

    static int minuteToWinIt(int[] a, int k) {
        int minutes = 0;
        List<Integer> inputs = Arrays.stream(a).boxed().collect(Collectors.toList());
        Map<Integer, Integer> diffMap = getDiffArray(inputs, k);

        if(diffMap.size() > 0) {
            minutes = processDiff(diffMap, inputs, k);
        }
        return minutes;
    }

    private static int processDiff(Map<Integer, Integer> diffMap, List<Integer> inputs, int k) {
        int minutes = 0;
        int diffSize = diffMap.size();
        int start = 0;
        while (diffSize > 0) {
            Iterator iterator = diffMap.entrySet().iterator();
            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
            int index = entry.getKey();
            int diffVal = entry.getValue();
            int diffWithK = k - diffVal;
            if (diffVal > 2) {
                //inputs.get(index-1) - 0;
            }
        }
        return 0;
    }

    private static Map<Integer,Integer> getDiffArray(List<Integer> inputs, int k) {
        Map<Integer, Integer> diffMap = new LinkedHashMap<>();
        for (int i = 1; i < inputs.size(); i++) {
            int diff = inputs.get(i) - inputs.get(i-1);
            if (diff != k) {
                diffMap.put(i, diff);
            }
        }
        return diffMap;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = minuteToWinIt(a, k);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(result);
        scanner.close();
    }
}
