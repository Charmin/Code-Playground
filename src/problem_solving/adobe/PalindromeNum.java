package problem_solving.adobe;

import java.util.LinkedList;

/**
 * Created by chaitra.kr on 14/04/19.
 */
public class PalindromeNum {

    public static void main(String[] args) {
        int num = 1436;
        int rev = reverseNum(num);
        System.out.println(rev == num);
    }

    private static int reverseNum(int num) {
        LinkedList<Integer> stack = new LinkedList<>();
        while (num > 9) {
            int res = num % 10;
            if (stack.peek() != null) {
               res = res + stack.pop();
            }
            res *= 10;
            stack.push(res);
            num = num / 10;
        }

        if (stack.peek() != null) {
            return num + stack.pop();
        } else {
            return num;
        }
    }
}
