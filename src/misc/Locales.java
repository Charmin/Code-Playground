package misc;

import java.util.Locale;

/**
 * Created by chaitra.kr on 08/05/16.
 */
public class Locales {

    public static void main(String[] args) {

        Locale  locale = Locale.getDefault();
        System.out.println("Locale : "+locale);

        Locale[] allLocales = Locale.getAvailableLocales();
        //all locales in this JVM
        for(Locale aLocale : allLocales){

            System.out.println("Name of Locale : "+aLocale.getDisplayName());
            System.out.println("Language Code : "+ aLocale.getLanguage()+" Language "+aLocale.getDisplayLanguage());
            System.out.println("Country Code :"+ aLocale.getCountry()+" Country "+aLocale.getDisplayCountry());
            System.out.println("Script Code "+aLocale.getScript()+ " Script "+aLocale.getDisplayScript());
            System.out.println("Variant Code "+aLocale.getVariant()+ " Variant "+aLocale.getDisplayVariant());
            System.out.println("\n-------------------------------------------");

        }

    }
}
