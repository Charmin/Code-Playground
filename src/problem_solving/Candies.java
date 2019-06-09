package problem_solving;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 01/01/19.
 */
public class Candies {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int kids = scanner.nextInt();
        int[] scores = new int[kids];
        for (int i =0; i < kids; i++) {
            scores[i] = scanner.nextInt();
            scanner.nextLine();
        }

        int candies = getCandies(kids, scores);
        System.out.println(candies);
    }

    private static int getCandies(int kids, int[] scores) {
        int[] pass1 = new int[kids];
        int[] pass2 = new int[kids];
        int[] finalPass = new int[kids];

        int k = 0;
        for (int s : scores) {
            pass1[k] = 1;
            pass2[k] = 1;
            k++;
        }
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[i-1]) {
                int prev = pass1[i-1];
                pass1[i]= prev+1;
            }
        }

        for (int i = scores.length-1; i > 0 ; i--) {
            if (scores[i-1] > scores[i]) {
                int prev = pass2[i];
                pass2[i-1]= prev+1;
            }
        }

        for (int i = 0; i < pass1.length; i++) {
            finalPass[i] = Math.max(pass1[i], pass2[i]);
        }

        int sum = Arrays.stream(finalPass).sum();
        return sum;
    }
}
