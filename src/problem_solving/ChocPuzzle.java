package problem_solving;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Created by chaitra.kr on 12/03/17.
 */
public class ChocPuzzle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] squares = new int[n];
        for(int squares_i=0; squares_i < n; squares_i++){
            squares[squares_i] = in.nextInt();
        }
        int d = in.nextInt();
        int m = in.nextInt();

        int possibilities = getPossibleChocolatePieces(n,d,m,squares);
        System.out.println(possibilities);
        // your code goes here
    }

    public static int getPossibleChocolatePieces(int len, int withSum, int chunks, int[] bar) {

        int indexCount = 0;
        int result = 0;
        int startIndex = 0;
        int curIndex = startIndex;
        int sum = 0;

        while (true) {

            sum += bar[curIndex];
            indexCount++;
            curIndex++;

            if (indexCount == chunks) {
                if (sum == withSum) {
                    result += 1;
                }
                startIndex += 1;
                curIndex = startIndex;
                sum = 0;
                indexCount = 0;
            }

            if (curIndex == len) {
                break;
            }
        }

        return result;
    }
}


