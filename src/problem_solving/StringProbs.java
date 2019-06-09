package problem_solving;

import java.util.Hashtable;

/**
 * Created by chaitra.kr on 02/11/18.
 */
public class StringProbs {

    public static void main(String[] args) {

        int hash = getHash("haitrac", 50);
        System.out.println(hash);
        String magazine = "This is a special letter to be written anonymously using the letters in the magazine given to you. " +
                "Use any page from the magazine";

        String letter1 = "The letter must contain the following text in it";
        String letter2 = "The letter must be written anonymously";

        System.out.println(anonymousMagazineLetter(letter1, magazine) == true ? "Anonymous letter possible" : "Anonymous letter not possible");
    }



    private static int getHash(String input, int mod) {
        int constI = 997;
        int val = 0;
        for (char c : input.toCharArray()) {
            val = (val * constI + c) % mod;
        }
        return val;
    }


    public static boolean anonymousMagazineLetter(String letter, String magazine) {
        Hashtable<Character, Integer> charCountDict = new Hashtable<>();
        for (char c : letter.toCharArray()) {
            if (charCountDict.containsKey(c)) {
                charCountDict.put(c, charCountDict.get(c) + 1);
            } else {
                charCountDict.put(c, 1);
            }
        }
        for (char c : magazine.toCharArray()) {
            if (charCountDict.containsKey(c)) {
                int count = charCountDict.get(c);
                if (count > 0 ) {
                    count--;
                    charCountDict.put(c, count);
                }
            }
        }

        for (Character key : charCountDict.keySet()) {
            if (charCountDict.get(key) > 0) {
                return false;
            }
        }
        return true;
    }


}
