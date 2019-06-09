package primitives;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chaitra.kr on 09/05/16.
 */
public class DataTypes {

    //byte, short, int, long, float, double, char, boolean ,String/Object
    public static void main(String[] args) {

        short a =  Short.MAX_VALUE; //32767
        short b = 1;
        short c = (short) (a + b);
        System.out.println("The Short Value is : " + c);

        byte a1 = 127;
        byte b1 = 1;
        byte c1 = (byte)(a1+b1);
        System.out.println("The byte Value is : " + c1);


        int a2 = 150;
        int b2 = -200;

        int c2 = a2+b2;
        System.out.println("The int Value is : " + c2);

        long a3 = 1000L;
        long b3 = -2000L;

        long c3 = a3+b3;

        System.out.println("The Long Value is : " + c3);

        float f = 1.0F/0.0F;
        float NaN = 0.0F/0.0F;


        System.out.println("Float:" +f);
        System.out.println("Float naan : "+NaN);
        //Widening vs Narrowing

        Integer i = new Integer(8);
        Number n = i;   // this is widening conversion; no need to cast

        Number n1 = new Integer(5); // again, widening conversion
        Integer i1 = (Integer) n;   // narrowing; here we explicitly cast down to the type we want - in this case an Integer

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);


        //************************************
        byte bi =8;
        short sh = bi;
        int g = (int)34.5;
        byte ct = (byte)g;
        long lk = 100;
        float fl = 12.34f;
        double db = 78.4;
        db=fl;
        db=g;
        db=ct;

        //*************************************

        String[] names = new String[4];

        int[] ar = new int[12];

        int x = -4;
        System.out.println(x>>1);
        int y = 4;
        System.out.println(y>>1);


        String s1="abc";
        String s2=s1;


        System.out.println("s1==abc is"+s1=="abc");

        HashMap<Integer,String> map = new HashMap<>();
        map.put(2,"MK");
        map.put(3,"wer");
        map.put(4,"QW");


        //Collections
        List<String> stringList = Collections.EMPTY_LIST;
        List<String> stringList1 = Collections.emptyList();
        System.out.println(stringList1);



    }
}
