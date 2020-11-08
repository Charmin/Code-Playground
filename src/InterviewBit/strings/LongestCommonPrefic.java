package InterviewBit.strings;

import java.util.*;

public class LongestCommonPrefic {

    public static void main(String[] args) {
        String a = null;
        int i=2;
        char c = (char)('a'+i);
        char[] ca = {'2', 'a'};

        System.out.println(String.valueOf(ca));
    }


    class WordPair {
        String a;
        String b;
        public WordPair(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }


    public static class IsValidNum {

        public static void main(String[] args) {
            String n = "11234456785";
            StringBuilder s = new StringBuilder(n);
            s.insert(1,'.');
            s.insert(2,'.');
            s.insert(3,'.');
            System.out.println(s);
            System.out.println(isNumber(n));
            //  [-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
        }


        public static int isNumber(final String A) {

            if (A == null) {
                return 0;
            }
            String B = A.trim();
            if (B.isEmpty()) return 0;
            if (B.length() == 1) {
                if (!(B.charAt(0) >= '0' && B.charAt(0) <= '9')) {
                    return 0;
                }
                return 1;
            }

            Map<Character, Integer> countMap = new HashMap<>();
            Map<Character, Set<Character>> nextMap = new HashMap<>();
            countMap.put('+',2);
            countMap.put('-',2);
            countMap.put('.',1);
            countMap.put('e',1);


            for (int i = 0; i < 10; i++) {
                char c = (char) ('.'+48);
                nextMap.put(c, new HashSet(){{
                    add('e');
                    add('.');
                    add('0');
                    add('1');
                    add('2');
                    add('3');
                    add('4');
                    add('5');
                    add('6');
                    add('7');
                    add('8');
                    add('9');
                }});
            }

            boolean isValid = true;
            for (int i = 1; i < B.length(); i++) {
                char prev = B.charAt(i-1);
                char next = B.charAt(i);
                if (i == 1) { //first char
                    if (prev == '.' || (prev >= '0' && prev <= '9') || prev == '+' || prev == '-') {
                        isValid = isValid && getValid(prev, next, countMap, nextMap,i);
                    } else {
                        return 0;
                    }
                } else {
                    isValid = isValid && getValid(prev, next, countMap, nextMap,i);
                }
            }

            return isValid == true ? 1 : 0;
        }

        public static boolean getValid(char prev, char next, Map<Character, Integer> countMap,
                                Map<Character, Set<Character>> nextMap, int iter) {
            boolean isValid = false;
            if (prev >= '0' && prev <= '9') {
                if (nextMap.get(prev).contains(next)) {
                    isValid = true;
                }
            }

            if (prev == '+') {
                if (countMap.get('+') <= 0) {
                    return false;
                }
                if (iter == 1) {
                    countMap.put('+', countMap.get('+')-1);
                } else {
                    countMap.put('+', countMap.get('+')-2);
                }
                if (nextMap.get('+').contains(next)) {
                    isValid = true;
                }
            }

            if (prev == '-') {
                if (countMap.get('-') <= 0) {
                    return false;
                }
                if (iter == 1) {
                    countMap.put('-', countMap.get('-')-1);
                } else {
                    countMap.put('-', countMap.get('-')-2);
                }
                if (nextMap.get('-').contains(next)) {
                    isValid = true;
                }
            }

            if (prev == '.') {
                if (countMap.get('.') <= 0) {
                    return false;
                }
                countMap.put('.', countMap.get('.')-1);
                if (nextMap.get('.').contains(next)) {
                    isValid = true;
                }
            }

            if (prev == 'e') {
                if (countMap.get('e') <= 0) {
                    return false;
                }
                countMap.put('e', countMap.get('e')-1);
                if (nextMap.get('e').contains(next)) {
                    isValid = true;
                }
            }

            return isValid;
        }
    }
}
