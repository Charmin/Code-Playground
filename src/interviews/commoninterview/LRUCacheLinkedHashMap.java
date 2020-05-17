package interviews.commoninterview;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {

    private int capacity;
    public LRUCacheLinkedHashMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.get(key);
    }

    public void put(int key, int value) {
        capacity++;
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size() > capacity;
    }
}

