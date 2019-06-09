package problem_solving;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chaitra.kr on 13/10/18.
 */
public class ReplaceRemove {

    public static void main(String[] args) {
        String input = "aacbbcca";
        String result = replaceRemove(input, 5);
        System.out.println(result);
    }

    private static String replaceRemove(String input, int len) {
        char[] inChar = input.toCharArray();
        List<Character> charArr = input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        //remove all 'b'
        int a_count = 0;
        int write_index = 0;
        for (int i = 0; i < len; i++) {
            if (inChar[i] != 'b') {
                inChar[write_index] = inChar[i];
                write_index++;
            }

            if (inChar[i] == 'a') {
                a_count++;
            }
        }
        write_index--;
        int sizeIndex = write_index + a_count;
        int size = write_index + 1;
        int actualSize = inChar.length;

        int k = write_index;
        while (k >= 0) {
            if (inChar[k] == 'a') {
                inChar[sizeIndex--] = 'd';
                inChar[sizeIndex--] = 'd';
                k--;
            } else {
                inChar[sizeIndex--] = inChar[k];
                k--;
            }
        }

//        if (actualSize > size) {
//            for (int m = size; m < actualSize;m++) {
//                inChar[m] = '\0';
//            }
//        }

        return String.valueOf(inChar);
    }
}
