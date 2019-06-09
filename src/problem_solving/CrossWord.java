package problem_solving;

import java.util.Scanner;

public class CrossWord {


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] crossword = new String[10];

        for (int i = 0; i < 10; i++) {
            String crosswordItem = scanner.nextLine();
            crossword[i] = crosswordItem;
        }
        String words = scanner.nextLine();
        String[] wordSplits = words.split(";");
        //String[] result = crosswordPuzzle(crossword, wordSplits, 0);
    }

//    private static String[] crosswordPuzzle(String[] crossword, String[] words, int wordIndex) {
//
//        if (wordIndex == words.length -1) {
//
//        }

//        for (int i = wordIndex; i < words.length; i++) {
//            //fit all words horizontally where possible,in turn
//            // fill the remain verticaly, if not fit, backtrack, try the next word
//            for (int r = 0; r < 10; r++) {
//                int sIndex = fitsInRow(crossword, words[i], r);
//                if (sIndex != -1) {
//                    fillRow(words[i], r, sIndex, crossword);
//                    crosswordPuzzle(crossword, words, wordIndex+1);
//                    //unfillRow(words[i], r, sIndex, crossword);
//                }
//            }
//
//            for (int c = 0; c < 10 ; c++) {
//                int sIndex = fitsInColumn(crossword, words[i], c);
//                if (sIndex != -1) {
//                    fillColumn(words[i], c, sIndex, crossword);
//                    crosswordPuzzle(crossword, words, wordIndex+1);
//                }
//            }
//        }
//    }
//
//    private static void unfillRow(String word, int row, int col, String[] crossword) {
//    }
//
//    private static void fillRow(String word, int row, int col, String[] crossword) {
//    }
}
