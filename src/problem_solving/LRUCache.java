package problem_solving;

import java.util.*;

/**
 * Created by chaitra.kr on 04/05/18.
 */
public class LRUCache<K,V> {

    private int capacity;
    private static int used = 0;

    public LRUCache(int capacity) {
        cacheItems = new LinkedHashMap<>();
        this.capacity = capacity;
    }

    private LinkedHashMap<K, V> cacheItems;

    public void insert(K key, V value) {
        if (cacheItems.size() < capacity) {
            cacheItems.put(key, value);
        } else {
           Map.Entry<K,V> entry = getFirst((LinkedHashMap<K, V>) cacheItems);
           cacheItems.remove(entry.getKey());
           cacheItems.put(key, value);
        }
    }

    public V get(K key) {
        V value = null;
        if (cacheItems.containsKey(key)) {
            value = cacheItems.get(key);
            cacheItems.remove(key);
            cacheItems.put(key, value);
        }
        return value;
    }

    public <K,V> Map.Entry<K,V> getFirst(LinkedHashMap<K,V> linkedHashMap) {
        if (linkedHashMap.isEmpty()) {
            return null;
        } else {
            return linkedHashMap.entrySet().iterator().next();
        }
    }
}

