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
        //String n = "";
        System.out.println(s);
        byte[] b = s.getBytes("UTF8");
        String i = new String(b, "US-ASCII");
        //b = s.getBytes("US-ASCII");
        //t = new String(b, "UTF8");
        System.out.println(t);
    }

    public static void printBox() {
        for (int i = 0x2500; i <= 0x257F; i++) {
            System.out.printf("0x%x : %c\n", i, (char) i);
        }
    }


}
