package problem_solving;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by chaitra.kr on 01/01/19.
 */
public class Abbreviations {

    public static void main(String[] args) {

        String a = "VEVVEEVEVVEVEEVVVEVEEEEEEEVVVEVEVEVVEEVEEEVVVEEVEEVVVEVEEVEEVVVVEVEVEVVEEVEEEEVEVVEEEVVVEEEVEEEVVEEVVVVVVEVVVVVEEVEEVVEEEVEVVVVEVEVEVEVVVEVVEEVVVEEEEEEVVIEEVVVVEEVVVEVEVVVEVVVEVIVEVVVVEEEEEVVVEVEEEVEVEEVEVVVEVVVEEEEEEVEVEVEVVEVVVVVEVEVEVEEEEVVEVEEEEVVEEEEEVEVEVEVVVEEVVEVVVVEEEEEVEVVVVVEEEVEEVEEEEVEEVVVEEEVEEVEEEEEEEVVVEVVEEVEVEEEVVVVEVEEVEVEVVVEEVEVVEVVVEVEEEEVVEVEVVEEVVVEEVVVVEVVVVEVVVVVEVEVVVVEVEEEEEEVVVEVVEEVVVEEVEEEEEEEEVVEEVEVEVVEVEIVVEEVEEVVEEVEEEIVVVVIEEEEEVEEEVEEEVVEVVEVVEVVEVEVEVEVVVEVE";
        String b = "evivevvVvevvEeeevVevevVvevEVevEeeEVVveevEvevevEeEeEEeeVevvvVVvevveevevEeVeVvvveeeeeivVveEevvEeveevvevvvVeevVeeeeveebveeVvvvvvEVEvEvvvvEEVVeeeVvEVEvevvevEvveVeEeeeVveVEVvvvvEeeVVvVeEEVvEEeeeveveeevVVeeeevVveeVvevVVevevvEEveVEEVVeeeEeveevevEvvvvevveeeeVEvevvEVvvvVevevvvveVeevVveevVeEevEEeeevVeieeeVvvvevvVeevvveeeevvVevEeevvvevveveevvveeeveeevVivevvevVvVeeevvEveevEEVeeVVEeeeevvveeEeveevvVeeEeevvvveeVvveveeeEveeeEeveeveVeVEveevvvVeevvveeEevVveeeVEeevEveevvVeveeeeVVVVeVEvvEVveveEvVeeeeEeeevvVEvveevvEeevevvVEeeeEvvvevvVvEVEvvvvvVvevEvVvVeevVevvVvEvveeeeeeEeveeVvEvVVvveveEvvVveeevvvViEeEEveeevvVevEveVVVeEeevVeveeEeeeeeveEvvVEeeEeveEvvvvveeVveVeVveeeVveEveeVvEVeEEeveeeVvvveEeveveeeVevevvveveVEEeveveevevveeevVeeeveveeveeveevvvEeeEvVeveevVEVEvvVVeeveVevViEEvVevevvVVEVVvvVeiEvVeevevvvEevvEvvvvevVveeVvvEevEeEEvEeeeeevveveevveveeeeVVeeveevvvveeVEEEveeeveeeEveeVVeeeVvEvvevveevvveVveeievivvVvvevevveeEeVEEeveveeVEEveviEveeivVvvVIEeEEvEveevEEveVvvEEveVeVEVvveveeVvVeEveeVVvveveeveVeveevvevEeVeeveVeEeVve";
        //String a = "VEVVEEVE";
        //String b = "evivevvVvevvEeeevVevevV";

        //String str = Pattern.compile("\\$").matcher(str).replaceAll("#[[\\$]]#");
        String res = abbreviation(a, b);
        System.out.println(res);
        Boolean bo = null;
        boolean bi = false;
        System.out.println("Boo:" + bo);
        StringBuilder bf = new StringBuilder();
        List<Character> s = new LinkedList<>();
        char[] c = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd','!'};
        char[] rev = reverse_words(c);
        System.out.println(rev);
    }

    static String abbreviation(String a, String b) {
        Map<TextPair, Boolean> cache = new HashMap<>();
        if (b.length() < a.length()) {
            return "NO";
        } else {
            if (checkDistance(a, b, cache)) {
                return "YES";
            } else {
                return "NO";
            }
        }

    }

    public static char[] reverse_words(char[] sentence) {
        LinkedList<String> chars = new LinkedList<>();
        StringBuilder a = new StringBuilder();
        for (char c : sentence) {
            if (c != ' ') {
                a.append(c);
            } else {
                chars.push(a.toString());
                a.delete(0, a.length());
            }
        }
        chars.push(a.toString());
        char[] rev = new char[sentence.length];
        a.delete(0, a.length());
        int index = 0;
        for (int k = 0; k < chars.size(); k++) {
            int j = 0;
            while (j < chars.get(k).length()) {
                rev[index] = chars.get(k).charAt(j);
                j++;
                index++;
            }
            if (k < chars.size() -1) {
                rev[index] = ' ';
                index++;
            }
        }
        return rev;
    }

    private static boolean checkDistance(String a, String b, Map<TextPair, Boolean> cache) {
        if (a.isEmpty() && b.isEmpty()) {
            return true;
        }

        if (a == null || a.isEmpty()) {
            if (!b.equals(b.toLowerCase())) { //has upper case characters
                cache.put(new TextPair(a, b), false);
                return false;
            } else {
                return true;
            }
        }

        if (b == null || b.isEmpty()) {
            return false;
        }

        //VEVVEEVE
        //evivevvVvevvEeeevVevevV
        int lenA = a.length();
        int lenB = b.length();

        TextPair p = new TextPair(a, b);
        if (cache.containsKey(p)) {
            return cache.get(p);
        }

        if (a.charAt(lenA - 1) == b.charAt(lenB - 1) || a.charAt(lenA - 1) == Character.toUpperCase(b.charAt(lenB - 1))) {
            boolean c = checkDistance(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1), cache) || checkDistance(a, b.substring(0, b.length() - 1), cache);
            cache.put(new TextPair(a, b), c);
            return c;
        } else if (Character.isLowerCase(b.charAt(lenB - 1))) {
            boolean c = checkDistance(a, b.substring(0, b.length() - 1), cache); //delete
            cache.put(new TextPair(a, b), c);
            return c;
        } else {
            cache.put(new TextPair(a, b), false);
            return false;
        }
    }
}

class TextPair {
    String a;
    String b;

    public TextPair(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextPair textPair = (TextPair) o;

        if (!a.equals(textPair.a)) return false;
        return b.equals(textPair.b);
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }

}