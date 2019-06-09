package algorithms;

/**
 * Created by chaitra.kr on 12/03/17.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Flower {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int c = in.nextInt();

        int rows = 3*r;
        int cols = 5*c;
        char[][] pattern = getFlower(rows,cols);

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++) {
                System.out.print(pattern[i][j]);
            }
            System.out.println();
        }
        // your code goes here
    }

    private static char[][] getFlower(int rows, int cols) {

        char[][] res = new char[rows][cols];

        for (int i=1;i<rows;i+=3) {
            for(int j=2;j<cols;j+=5) {
                res[i][j]='o';
            }
        }
        for (int i=1;i<rows;i+=3) {
            for(int j=0;j<cols;j+=5){
                res[i][j]='O';
            }
        }

        for (int i=1;i<rows;i+=3) {
            for(int j=4;j<cols;j+=5){
                res[i][j]='O';
            }
        }

        for (int i=0;i<rows;i+=3){
            for(int j=2;j<cols;j+=5) {
                res[i][j] = 'O';
            }
        }

        for (int i=2;i<rows;i+=3){
            for(int j=2;j<cols;j+=5) {
                res[i][j] = 'O';
            }
        }

        for (int i=0;i<rows;i++){
            for(int j=0;j<cols;j++) {
                if(res[i][j]=='\u0000')
                    res[i][j]='.';
            }
        }

        return res;
    }
}
