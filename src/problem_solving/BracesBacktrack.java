package problem_solving;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chaitra.kr on 24/02/19.
 */
public class BracesBacktrack {

    public static void main(String[] args) {

        List<String> output = new ArrayList<String>();
        LinkedList<Character> res = new LinkedList<Character>();
        res.add('{');
        getBraces(3, 1, 0, 0, res, output);
        System.out.println(output);
    }

    static void getBraces(int n, int open, int close, int index, LinkedList<Character> result,
                          List<String> output) {
        if (n == 1) {
            output.add("{}");
            return;
        }

        if (index == ((2*n)-1)) {
            StringBuilder s = new StringBuilder();
            for (Character c : result) {
                s.append(c);
            }
            output.add(s.toString());
            return;
        }

        if (result.size() == 0) {
            return;
        }

        if (open >= close) { //valid
            if (open < n) {
                result.add('{');
                getBraces(n, open+1, close, index + 1, result, output);
                result.removeLast();
            }
            if (close < n) {
                result.add('}');
                getBraces(n, open, close+1 , index + 1, result, output);
                result.removeLast();
            }
        }
    }

    static void remove_white_spaces (char[] s) {
        int r = 0;
        int w = 0;
        for (int i = 0; i < s.length; i++) {
            s[w]=s[r];
            if ((s[i] == ' ') || (s[i] == '\t')) {
                r++;
                w++;
            }
            r++;
        }
    }
}
