package misc;


import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by chaitra.kr on 08/05/16.
 */
public class Unicodes {

    public Unicodes() {
    }
    /*UTF-8 is a transmission format for Unicode that is safe for UNIX file systems.*/
    public static void main(String[] args) throws UnsupportedEncodingException {
        //printBox();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new ByteArrayOutputStream());
        System.out.println(outputStreamWriter.getEncoding());

        Charset charset = Charset.forName("US-ASCII");
        Timestamp t = new Timestamp(System.currentTimeMillis());
        t.getTime();

        //String s = "Voltegic ?...";
        //String s = "Voltegic \ufffd...";
        String s = "Voltegic ���...";

        String s1 = "Super very gud camera\n" +
                "Super Battery\n" +
                "Super Display over all super \n" +
                "Valuable money \uD83D\uDC96\uD83D\uDC96\uD83D\uDC96";

         int len = s1.length();
        //U+D800 through U+DB7F
        //Highlight indices : 0,49

        //int end = index.getStart() + index.getOffset();
        String text = "Super very gud camera Super Battery Super Display over all super Valuable money 💖💖💖";
        if (text != null && text.length() >= 84) {
            s1 = new String(s1.getBytes(Charset.forName("UTF-16")), Charset.forName("UTF-16"));
            String p = s1.substring(0,84);
            String p1 = new String(p.getBytes(Charset.forName("UTF-16")), Charset.forName("UTF-16"));
            System.out.println(p1);
        }


        //b = s.getBytes("US-ASCII");
        //t = new String(b, "UTF8");
    }

    public static void printBox() {
        for (int i = 0x2500; i <= 0x257F; i++) {
            System.out.printf("0x%x : %c\n", i, (char) i);
        }
    }


}
