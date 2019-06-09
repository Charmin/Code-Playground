package problem_solving;

/**
 * Created by chaitra.kr on 13/10/18.
 */
public class SpreadSheetEncoder {

    public static void main(String[] args) {
        String cols = "AA";

        int encoded = spreadSheetEncoded(cols);
        System.out.println("Value of " + cols + ":" + encoded);
    }

    private static int spreadSheetEncoded(String cols) {
        int val = 0;
        char[] car = cols.toCharArray();
        for (int j = 0; j < car.length; j++) {
            val = (int) (val + ((Math.pow(26, car.length - 1 - j)) * (car[j] - 64)));
        }
        return val;
    }
}
