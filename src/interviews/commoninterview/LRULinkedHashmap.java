package interviews.commoninterview;

import java.util.LinkedHashMap;

public class LRULinkedHashmap {
    public static void main(String[] args) {

    }

    private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>(16, (float) 0.75, true);
    int maxCap;

    public LRULinkedHashmap(int capacity) {
        this.maxCap = capacity;
    }

//    public int get(int key) {
//        int res = resNode != null ? resNode.val : -1;
//        if (res >= 0) {
//            dList.remove(resNode);
//            dList.insert(key, resNode.val);
//        }
//        return res;
//    }
//
//    public void put(int key, int value) {
//        if (cache.size() == maxCap) {
//            cache.
//            dList.remove(lru);
//            cache.remove(lru.key);
//        }
//        cache.put(key,  dList.insert(key, value));
//    }
}
