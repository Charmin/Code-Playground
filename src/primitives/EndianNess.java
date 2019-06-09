package primitives;

import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chaitra.kr on 09/05/16.
 */
public class EndianNess {

    public static void main(String[] args) {

        ByteOrder byteOrder = ByteOrder.nativeOrder();
        if(byteOrder.equals(ByteOrder.BIG_ENDIAN))
            System.out.println("Big Endian");
        else
            System.out.println("Little Endian");


        Map<Long, Long> mp = new HashMap<>();

        if(mp!=null)
            System.out.println("mp");

        System.out.println(mp.size());

        for(Long heys : mp.keySet()){
            System.out.println(heys);
        }

    }
}
