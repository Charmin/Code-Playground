package algorithms;

import java.util.Scanner;

/**
 * Created by chaitra.kr on 07/05/17.
 */
public class LCS {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int no_of_q  = in.nextInt();
        int len[] = new int[no_of_q];
        for(int i=0; i < no_of_q; i++){
            int size1 = in.nextInt();
            int size2 = in.nextInt();
            String s = in.next();
            String t = in.next();
            len[i] = getLCS(s,t);
        }

        for(int i=0; i < no_of_q; i++) {
            System.out.println(len[i]);
        }

    }

    private static int getLCS(String t1, String t2) {

        int[][] lcsLen = new int[t1.length()+1][t2.length()+1];

        int len=0;

        for(int k=0;k<=t2.length();k++) {
            lcsLen[0][k]=0;
        }

        for(int p=0;p<=t1.length();p++) {
            lcsLen[p][0]=0;
        }

        for(int i=0; i<t1.length(); i++) {
            for(int j=0;j<t2.length();j++) {
                if(t1.charAt(i)==t2.charAt(j)) {
                    lcsLen[i+1][j+1]=1+lcsLen[i][j];
                } else {
                    lcsLen[i+1][j+1] = max(lcsLen[i][j+1], lcsLen[i+1][j]);
                }
            }
        }

        for(int m=0;m<=t1.length();m++) {
            for(int n=0;n<=t2.length();n++) {
                System.out.print(lcsLen[m][n]+" ");
            }
            System.out.println("");
        }

        System.out.println("algorithms.LCS:"+lcsLen[t1.length()][t2.length()]);
        return lcsLen[t1.length()][t2.length()];
    }

    private static int max(int a, int b) {
        return a>b?a:b;
    }
}
