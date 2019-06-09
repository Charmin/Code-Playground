package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 14/05/18.
 */
public class KnapSack {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        int noOfTestCases = Integer.parseInt(scanner.nextLine());
//
//        for (int l = 0; l < noOfTestCases; l++) {
//
//            int noOfItems = Integer.parseInt(scanner.next());
//            int maxWeight = Integer.parseInt(scanner.next());
//            List<Integer> values = new ArrayList<>();
//            List<Integer> weights = new ArrayList<>();
//            List<KnapSackItem> items = new ArrayList<>();
//
//            for (int i = 0; i < noOfItems; i++) {
//                int n = scanner.nextInt();
//                values.add(n);
//            }
//
//            scanner.nextLine();
//            for (int j = 0; j < noOfItems; j++) {
//                weights.add(scanner.nextInt());
//            }
//
//            for (int k = 0; k < noOfItems; k++) {
//                KnapSackItem knapSack = new KnapSackItem(values.get(k), weights.get(k));
//                items.add(knapSack);
//            }
//
//            int maxVal = solveKnapSack(maxWeight, items, 0);
//            System.out.println(maxVal);
//        }

        List<KnapSackItem> items = new ArrayList<>();
        items.add(new KnapSackItem(10, 5));
        items.add(new KnapSackItem(40, 4));
        items.add(new KnapSackItem(30, 6));
        items.add(new KnapSackItem(50, 3));

        int[][] sol = new int[4][19];
        int p = knapSack(0, 0, 0, 0, 10, items, sol);
        System.out.println(p);
    }

    private static int solveKnapSack(int maxWeight, List<KnapSackItem> items, int profit) {
        if (maxWeight == 0) {
            return profit;
        }
        if (items.size() == 0) {
            return profit;
        }
        KnapSackItem item = items.get(0);
        List<KnapSackItem> subItems = items.subList(1, items.size());
        if (item.getWeight() > maxWeight) {
            profit = solveKnapSack(maxWeight, subItems, profit);
        } else {
            profit = Math.max(solveKnapSack(maxWeight, subItems, profit), solveKnapSack(maxWeight - item.getWeight(), subItems, profit + item.getValue()));
        }
        return profit;
    }

    public static int knapSack(int itemCount, int itemIndex, int weights, int values, int maxW, List<KnapSackItem> items, int[][] solution) {

        if (maxW == 0) {
            return 0;
        }

        if (itemIndex == items.size()) {
            return solution[itemCount][weights];
        }

        if (solution[itemCount][weights] > 0) {
            return solution[itemCount][weights];
        }
        if (items.get(itemCount).getWeight() <= maxW) {
            int withItem = knapSack(itemCount + 1, itemIndex + 1, weights + items.get(itemIndex).getWeight(),
                    values + items.get(itemIndex).getValue(), maxW - items.get(itemIndex).getWeight(), items, solution);
            int withOutItem = knapSack(itemCount, itemIndex + 1, weights, values, maxW, items, solution);
            solution[itemCount][weights] = Math.max(withItem, withOutItem);
            System.out.println("sol:" + solution[itemCount][weights]);
        }
        return solution[itemCount][weights];
    }


}

class KnapSackItem {

    private int value;
    private int weight;

    public KnapSackItem(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
