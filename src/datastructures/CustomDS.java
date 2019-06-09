package datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chaitra.kr on 08/03/19.
 */
public class CustomDS {

    public static void main(String[] args) {


    }

    class MyDS<T> {

        private ArrayList<T> ar;
        private Map<T, Integer> map;

        public MyDS() {
            ar = new ArrayList<>();
            map = new HashMap<>();
        }

        public void insert(T el) {
            int index = ar.size();
            if (map.get(el)!=null) {
                ar.add(el);
                map.put(el, index);
            }
        }
    }
}
