package java_stuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chaitra.kr on 23/05/18.
 */
public class GCTest {

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.getTest();
        GCTest t1 = new GCTest();
        System.out.println("t1 "+t1);
        GCTest t2 = m1(t1); // line 6
        //GCTest t2 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("t2 "+t2);
        GCTest t3 = new GCTest();
        System.out.println("t3 "+t3);
        t2 = t3; // line 8
        System.out.println("t1 now "+ t1);
        System.out.println("t2 now "+t2);
        System.gc();
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("sds","fasdf");
        Map<String, String> stringMapCopy = new HashMap<>();
        stringMap.putAll(stringMapCopy);
        Integer i = 126;
        Integer r = 128;
        System.out.println("IR "+ (i==r));
        Thread.sleep(3000);

//        GCTest t5 = new GCTest();
//        GCTest t4 = new GCTest();
//
//        // Nullifying the reference variable
//        t1 = null;
//
//        // requesting JVM for running Garbage Collector
//        System.gc();
//
//        // Nullifying the reference variable
//        t2 = null;

        // requesting JVM for running Garbage Collector
        Runtime.getRuntime().gc();


    }

    static GCTest m1(GCTest temp) {
        temp = new GCTest();
        System.out.println("Called "+temp);
        return temp;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("GC bein g called on "+this);
    }
}

class Test {
    List<String> test;

    int te;

    String one;

    public List<String> getTest() {
        test = new ArrayList<>();
        one = "One "+te;
        test.add(one);
        return test;
    }

}
