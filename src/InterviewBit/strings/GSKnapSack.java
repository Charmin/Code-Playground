package InterviewBit.strings;

import java.util.*;

public class GSKnapSack {

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(38, 130, 500);
        List<Integer> l2 = Arrays.asList(21, 280, 1800);
        List<Integer> l3 = Arrays.asList(13, 120, 1500);


        List<List<Integer>> b = new ArrayList<>();
        b.add(l1);
        b.add(l2);
        b.add(l3);

        List<Integer> res = findTruckCargo(300, b);
        System.out.println(res);
    }

    static List<Integer> findTruckCargo(int maxCargoWeight, List<List<Integer>> cargoList) {
        LinkedList<Integer> result = new LinkedList<>();
        Map<Integer, ArrayList<Integer>> profitToItems = new HashMap<>();
        //int maxProfit = getResult(maxCargoWeight, cargoList.size()-1, 0, cargoList, result, profitToItems);
        Map<String, Integer> cache = new HashMap<>();
        Set<Integer> results = new HashSet<>();
        int maxProfit = getResult(maxCargoWeight, cargoList.size() - 1, 0, cargoList, cache, results);
        //result.addAll(profitToItems.get(maxProfit));
        result.addLast(maxProfit);
        return result;
    }

    private static Integer getResult(int w, int index, int profit, List<List<Integer>> cargoList, LinkedList<Integer> cargoes, Map<Integer, ArrayList<Integer>> profitToItems) {
        if (w == 0) {
//            ArrayList<Integer> copy = new ArrayList<>();
//            copy.addAll(cargoes);
//            profitToItems.put(profit, copy);
            return profit;
        }
        if (index == cargoList.size()) {
            return profit;
        }
        String key = index + "|" + profit;
        int no = cargoList.get(index).get(0);
        int weight = cargoList.get(index).get(1);
        int price = cargoList.get(index).get(2);
        int withoutItem = -1;
        int withItem = getResult(w - weight, index + 1, profit + price, cargoList, cargoes, profitToItems);
        withoutItem = getResult(w, index + 1, profit, cargoList, cargoes, profitToItems);
        int remx = Math.max(withItem, withoutItem);
        return remx;
    }

    private static Integer getResult(int w, int index, int profit, List<List<Integer>> cargoList, Map<String, Integer> cache, Set<Integer> results) {
        if (w < 0) {
            return Integer.MIN_VALUE;
        }

        if (w == 0 || index < 0) {
            return profit;
        }

        String key = index + "|" + w;

        int weight = cargoList.get(index).get(1);
        int price = cargoList.get(index).get(2);
        List<Integer> includes = new ArrayList<>();
        List<Integer> exclcudes = new ArrayList<>();
        if (cache.get(key) ==  null) {
            includes.add(index);
            int withItem = getResult(w - weight, index - 1, profit + price, cargoList, cache, results);
            exclcudes.add(index);
            int withoutItem = getResult(w, index - 1, profit, cargoList, cache, results);
            exclcudes.addAll(results);
            if (withItem > withoutItem) {
                results.removeAll(exclcudes);
                results.addAll(includes);
                cache.put(key, withItem);
            } else {
                results.removeAll(includes);
                results.addAll(exclcudes);
                cache.put(key, withoutItem);
            }
        }
        return cache.get(key);
    }
}
