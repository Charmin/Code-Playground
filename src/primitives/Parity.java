package primitives;

/**
 * Created by chaitra.kr on 23/09/18.
 */
public class Parity {

    public static void main(String[] args) {

        int n = 60;
        String binary = getBinary(n);
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toString(n, 2));
        System.out.println(Integer.toString(n, 16));
        System.out.println(binary);
        short parity = getParityTwo(n);
        System.out.println("Parity: "+parity);
    }

    private static short getParity(int n) {
        short result = 0;
        while (n > 0) {
            result = (short) (result + (n & 1));
            System.out.println("n: " + n);
            System.out.println("Result: (n & 1) :" + (n & 1));
            n = n >> 1;
        }
        return result;
    }

    private static short getParityTwo(int n) {
        short result = 0;
        while (n > 0) {
            result = (short) (result + 1);
            System.out.println("Result: (result ^ 1) :" + (result ^ 1));
            System.out.println("n: " + n);
            n &= (n-1);// unsets each bit by bit
        }
        return result;
    }


    private static String getBinary(int n) {
        StringBuilder s = new StringBuilder();
        int r = 0;
        while (n >= 2) {
            r = n % 2;
            s.append(String.valueOf(r));
            n = n / 2;
        }
        s.append(r);
        s.reverse();
        return s.toString();
    }
}
