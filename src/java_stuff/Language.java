package java_stuff;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by chaitra.kr on 04/09/17.
 */
public class Language {
        public static void main(String[] args) throws UnsupportedEncodingException {

            byte [] bArray = {'w'};

            Locale[] listLocales = Locale.getAvailableLocales();
            List<String> listInstalledLanguages = new ArrayList<String>();

            for(int i=0;i<listLocales.length;i++){
                String lang = listLocales[i].getLanguage();
                if(lang.equals("en") && !(listInstalledLanguages.contains("en")))
                    listInstalledLanguages.add(lang);
                if(lang.equals("hi") && !(listInstalledLanguages.contains("hi")))
                    listInstalledLanguages.add(lang);
            }

            String t = "lol";
            byte[] b = t.getBytes(Charset.forName("UTF-8"));
            String ori = new String(b,"UTF-8");
            System.out.println("Orginal1 : "+ori);

            String t1 = "दिस मैसेज नंबर मिलेगा या";
            byte[] b1 = t1.getBytes(Charset.forName("UTF-8"));
            String ori1 = new String(b,"UTF-8");
            System.out.println("Orginal2 :"+ori1);

            System.out.println("list: "+listInstalledLanguages.get(0)+", "+listInstalledLanguages.get(1));
            String result = "दिस मैसेज नंबर मिलेगा या";
            System.out.println("Result: "+result);
            InputStream is = new ByteArrayInputStream(bArray);
            InputStreamReader reader = new InputStreamReader(is);
            String defaultCharacterEncoding = reader.getEncoding();
            System.out.println("Input StreamReader encoding: "+defaultCharacterEncoding);
            System.out.println(System.getProperty("file.encoding"));
            System.out.println("दिस मैसेज नंबर मिलेगा या%   ");
            System.out.println(String.format("file.encoding: %s", System.getProperty("file.encoding")));
            System.out.println(String.format("defaultCharset: %s", Charset.defaultCharset().name()));

    }
}
