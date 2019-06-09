package java_stuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaitra.kr on 02/05/16.
 */
public class MyLabda {

    public static void main(String[] args) {

        List<Integer> sample = new ArrayList<>();
        sample.add(3);
        sample.add(4);
        sample.add(5);
        sample.add(6);
        sample.add(7);
        sample.add(8);

        Converter<String,Integer> converter = (stringx)-> Integer.valueOf(stringx);

        Integer  x = converter.convert("1234");
        System.out.println(x);



    }
}

@FunctionalInterface
interface Converter<F,T>{
    T convert(F from);
}


