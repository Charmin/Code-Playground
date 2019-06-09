package datastructures;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by chaitra.kr on 03/11/18.
 */
public class ISBNCache {

    /*
    * insert: if key already present, update the access
     *remove: remove least recently used
      * lookup: return the value, update the access
    * */
    private int capacity;
    private HashMap<Integer, String> cache;
    private java.util.LinkedList<String> pages;

    public ISBNCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        pages = new LinkedList<>();
    }

    public boolean insert(int key, String val) {
        boolean isInserted = false;
        if (!cache.containsKey(key)) {
            if (cache.size() >= capacity) {
                pages.removeLast();
            }
            cache.put(key, val);
            isInserted = true;
        }
        pages.addFirst(val);
        return isInserted;
    }

    public String lookUp(int key) {
        if (cache.containsKey(key)) {
            pages.remove(cache.get(key));
            pages.push(cache.get(key));
            return cache.get(key);
        } else {
            return null;
        }

    }
}
