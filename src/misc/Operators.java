package misc;

/**
 * Created by chaitra.kr on 10/05/16.
 */
public class Operators {

    public static void main(String[] args) {

        int[] a = new int[3];
        int[] b = new int[3];

        int i=0;
        a[i++]+=2;
        for(int k=0;k<a.length;k++)
            System.out.println("a["+k+"] = "+a[k]);

        i=0;
        b[i++] = b[i++]+2;
        for(int k=0;k<b.length;k++)
            System.out.println("b["+k+"] = "+b[k]);


        int g = + + 10;
        System.out.println("g = "+ g);

    }
}


