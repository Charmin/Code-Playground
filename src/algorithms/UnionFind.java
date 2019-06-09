package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chaitra.kr on 12/02/19.
 */
public class UnionFind {

    public boolean equationsPossible(String[] equations) {
        Map<Integer, Integer> unionMap = new HashMap<>();
        boolean valid = true;

        for (int i = 0; i <= 26; i++) {
            unionMap.put(i, i);
        }

        for (String exp : equations) {
            if (exp.charAt(1) == '=') {
                int parent = 'a' - exp.charAt(0);
                int child = 'a' - exp.charAt(3);
                unionMap.put(child, parent);
            }
        }

        for (String exp : equations) {
            if (exp.charAt(1) == '!') {
                int parent = 'a' - exp.charAt(0);
                int child = 'a' - exp.charAt(3);
                if (parent == child) {
                    return false;
                }
                int p1 = find(child, unionMap);
                int p2 = find(parent, unionMap);
                if (p1 == p2) {
                    return false;
                }
            }
        }
        return valid;
    }


    public int find(int c, Map<Integer, Integer> map) {
        int res = c;
        while (map.get(c) != c) {
            c = map.get(c);
            res = c;
        }
        return res;
    }
}