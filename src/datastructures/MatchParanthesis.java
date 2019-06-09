package datastructures;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by chaitra.kr on 17/10/18.
 */

public class MatchParanthesis {

    public static void main(String[] args) {
        String p = "(()(()))(()()()))))((((()*()*(())())(()))((*()(*((*(*()))()(())*()()))*)*()))()()(())()(()))())))";
        System.out.println(checkValidString(p));
    }


    //Brute Force
//    public static boolean checkValidString(String s) {
//        boolean isValid = true;
//        java.util.LinkedList<Word> stack = new java.util.LinkedList<>();
//        java.util.LinkedList<Word> starStack = new LinkedList<>();
//        for (int i = 0; i < s.length() && isValid; i++) {
//            String sub = String.valueOf(s.charAt(i));
//            if (sub.equals("(")) {
//                stack.push(new Word(sub, i));
//            }
//            if (sub.equals("*")) {
//                starStack.push(new Word(sub, i));
//            }
//            if (sub.equals(")")) {
//                if (stack.peekFirst() == null) {
//                    if (starStack.peekFirst() == null) {
//                        isValid = false;
//                    } else {
//                        starStack.pop();
//                    }
//                } else {
//                    stack.pop();
//                }
//            }
//        }
//
//        while (stack.peekFirst()!=null && starStack.peekFirst()!=null) {
//            if (stack.peekFirst().getWeight() < starStack.peekFirst().getWeight()) {
//                stack.pop();
//                starStack.pop();
//            }
//        }
//
//        if (stack.size() > 0) {
//            isValid = false;
//        }
//
//        return isValid;
//    }
//
//    static class Word {
//        String word;
//        int weight;
//
//        public Word(String word, int weight) {
//            this.word = word;
//            this.weight = weight;
//        }
//
//        public String getWord() {
//            return word;
//        }
//
//        public void setWord(String word) {
//            this.word = word;
//        }
//
//        public int getWeight() {
//            return weight;
//        }
//
//        public void setWeight(int weight) {
//            this.weight = weight;
//        }
//    }

    //smart #1
    public static boolean checkValidString(String s) {
        int high = 0;
        int low = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (high >=0 ) {
                    high++;
                }
                low++;
            } else if (c == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
        }

        if (high < 0) {
            return false;
        }

        return low == 0;
    }
}
