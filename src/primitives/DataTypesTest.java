package primitives;

/**
 * Created by chaitra.kr on 09/05/16.
 */
public class DataTypesTest {

    public static void main(String[] args) {


        byte b1 = (byte) 130;
        System.out.println(b1);

        Double object = new Double("2.4");
        int a = object.intValue();
        byte b = object.byteValue();
        float d = object.floatValue();
        double c = object.doubleValue();

        System.out.println(a + b + c + d );


        float fl = 123.4F;
        short sh = 23;

        if(fl==sh)
            System.out.println("equal");
        else
            System.out.println("not equal");

    }
}
