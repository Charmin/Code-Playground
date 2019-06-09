package datastructures;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by chaitra.kr on 13/02/17.
 */
public class CatHops {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Scanner s = new Scanner(System.in);
        int[] jumpD = new int[100];

        for (int i = 0; i < 100; i++) {
            String input = s.next();
            String algorithm = "SHA-256";
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(input.getBytes("UTF-8"));

            System.out.println(getRowKeyHash(input));
        }

        //int hops = getMinHops(distance,jumpD[0],jumpD[1]);
    }

    private static int getMinHops(int distance, int a, int b) {

        int D = a+ b;
        int d = (a-b)>0?(a-b):(b-a);
        int diff = distance;
        int greater = (a-b)>0?a:b;
        int q = distance/greater;
        int p = distance/D;
        int minHops = 0;
        if(q>(p*2)) {
            int hops = q;
            diff = distance-(greater*q);
            if(diff==0) {
                if(hops<=minHops)
                    minHops = hops;
            }

        }
        return 0;


    }

    public static String getRowKeyHash(String data) {
        //System.out.println(String.format("%03d", hashData % 501) );
        //Integer hashData = Integer.valueOf(data.substring(3));
        Integer hashData = data.hashCode();
        //System.out.println(hashData);
        return String.format("%03d", ((hashData % 501 + 501) % 501)) + hashData;
    }

//    public static String getRowKeyHash(String data) {
//        Integer hashData = data.hashCode();
//        return String.format("%03d", ((hashData % REGION_COUNT + REGION_COUNT) % REGION_COUNT)) + data;
//    }

}
