package interviews.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchSuggestionSystem {

    public static void main(String[] args) {
        String[] pr = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        //String[] pr = {"havana"};
        List<List<String>> lo = suggestedProducts(pr, "mouse");
        for (List<String> sl : lo) {
            for (String s : sl) {
                System.out.print(s);
                System.out.print(",");
            }
            System.out.println();
        }
    }

    public static List<List<String>> suggestedProductsNew(String[] products, String searchWord) {
        List<List<String>> w;
        List<String> prs = Stream.of(products).sorted().collect(Collectors.toList());
        w = binSearchNew(searchWord, prs);
        return w;
    }

    //does not work for all test cases
    private static List<List<String>> binSearchNew(String s, List<String> prs) {
        int low = 0;
        int high = prs.size() - 1;
        List<List<String>> w = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            List<String> res = new ArrayList<>();
            if (low <= high) {
                while (low < prs.size() && i < prs.get(low).length() && prs.get(low).charAt(i) != s.charAt(i)) {
                    low++;
                }
                while (high >= 0 && i < prs.get(high).length() && prs.get(high).charAt(i) != s.charAt(i)) {
                    high--;
                }
                for (int k = low; k < Math.min(low + 3, high+1  ); k++) {
                    res.add(prs.get(k));
                }
            }
            w.add(res);
        }

        return w;
    }

    //treeMap solution
    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> w = new ArrayList<>();
        List<String> productsList = Stream.of(products).sorted().collect(Collectors.toList());
        TreeMap<String, Integer> map = new TreeMap<>();

        for (int j = 0; j < productsList.size(); j++) {
            map.put(productsList.get(j), j);
        }

        for (int i = 1; i <= searchWord.length(); i++) {
            String s = searchWord.substring(0, i);
            //find the least word which has this as prefix
            String ceiling = map.ceilingKey(s);
            //find the greatest word (upper bound) of the words with this prefix
            String floor = map.floorKey(s+"~");
            if (ceiling != null && floor != null) {
                w.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
            }

        }
        return w;
    }
}
