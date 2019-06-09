package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chaitra.kr on 01/05/16.
 */
public class CatComparisons{

    public static void main(String[] args) {

        Cat c1 = new Cat(5.3, 12, 2);
        Cat c2 = new Cat(6.5, 13, 4);
        Cat c3 = new Cat(10, 15, 20);
        Cat c4 = new Cat(11,8,5);

        Cat c5 = new Cat(12,8,3);
        Cat c6 = new Cat(13,8,6);
        Cat c7 = new Cat(14,8,11);
        Cat c8 = new Cat(15,8,9);
        Cat c9 = new Cat(16,8,30);

        List<Cat> catList = new ArrayList<Cat>();
        catList.add(c1);
        catList.add(c2);
        catList.add(c3);
        catList.add(c4);
        catList.add(c5);
        catList.add(c6);
        catList.add(c7);
        catList.add(c8);
        catList.add(c9);



        for(Cat c : catList)
            System.out.println("Unsorted: "+c);

        Collections.sort(catList, new CatComparator());
        for(Cat c : catList)
            System.out.println(c);
    }

}
