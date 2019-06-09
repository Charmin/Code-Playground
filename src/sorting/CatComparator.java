package sorting;

import sorting.Cat;

import java.util.Comparator;

/**
 * Created by chaitra.kr on 01/05/16.
 */
class CatComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat c1, Cat c2) {
        if (c1.getAge() % 2 == 0 && c2.getAge() % 2 == 0) {
            return Integer.compare(c1.getAge(), c2.getAge()); //both even
        } else if (c1.getAge() % 2 == 0) //first  even, sec odd
            return -1;
        else if (c1.getAge() % 2 != 0 && c2.getAge() % 2 != 0) {  //both odd
            return Integer.compare(c1.getAge(), c2.getAge());
        } else  //first odd, sec even
            return 1;
    }

}
