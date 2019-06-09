package misc;

/**
 * Created by chaitra.kr on 08/05/16.
 */
public class AssKey {

    public static void main(String[] args) {
        System.out.println("Decimal    Ascii    Hex");
        StringBuilder b = new StringBuilder("c");
        b.insert(0, "a");
        System.out.println(b);
        for (int i = 0; i < 128; i++) {
            char c = (char) i;
            String display = " ";
            if (Character.isISOControl(c))
                display = "control";
            else
                display = Character.toString(c);
            System.out.println(i + "      " + display + "     " + Integer.toHexString(i));
        }
    }
}
