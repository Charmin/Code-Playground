package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chaitra.kr on 03/12/18.
 */class Solution {
    public int strStr(String haystack, String needle) {
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> lpsDistance = buildLpsMap(needle);
        int tIndex = 0;
        int patIndex = 0;

        if (haystack.isEmpty() && needle.isEmpty()) {
            return 0;
        }
        if (needle == null || needle.isEmpty()) return 0;

        while (tIndex < haystack.length()) {
            if (haystack.charAt(tIndex) == needle.charAt(patIndex)) {
                tIndex++;
                patIndex++;
            } else {
                if (patIndex == 0) {
                    tIndex++;
                } else {
                    patIndex = lpsDistance.get(patIndex - 1);
                }
            }
            if (patIndex == needle.length()) {
                results.add(tIndex - needle.length());
                patIndex = 0;
            }
        }
        if (results.isEmpty()) {
            return  -1;
        }
        return results.get(0); // return only first occurance;
    }

    private Map<Integer, Integer> buildLpsMap(String needle) {
        Map<Integer, Integer> lpsMap = new HashMap<>();
        int len = 0;
        int i = 1;
        lpsMap.put(0,0);

        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                lpsMap.put(i, len);
                i++;
            } else {
                if (len == 0) {
                    lpsMap.put(i, 0);
                    i++;
                } else {
                    len = lpsMap.get(len-1);
                }

            }
        }
        return lpsMap;
    }
}
public class KMP {

    public static void main(String[] args) {
        //String h = "misissippi";
        //String e = "is";
        String h = "ababababab";
        String e = "abab";
        int res = strStr(h, e);
        System.out.println(res);
    }

    public static int strStr(String haystack, String needle) {
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> lpsDistance = buildLpsMap(needle);
        int tIndex = 0;
        int patIndex = 0;

        if (haystack.isEmpty() && needle.isEmpty()) {
            return 0;
        }
        if (needle == null || needle.isEmpty()) return 0;

        while (tIndex < haystack.length()) {
            if (haystack.charAt(tIndex) == needle.charAt(patIndex)) {
                tIndex++;
                patIndex++;
            } else {
                if (patIndex == 0) {
                    tIndex++;
                } else {
                    patIndex = lpsDistance.get(patIndex - 1);
                }
            }
            if (patIndex == needle.length()) {
                results.add(tIndex - needle.length());
                patIndex = 0;
            }
        }
        if (results.isEmpty()) {
            return -1;
        }
        return results.get(0); // return only first occurance;
    }

    private static Map<Integer, Integer> buildLpsMap(String needle) {
        Map<Integer, Integer> lpsMap = new HashMap<>();
        int len = 0;
        int i = 1;
        lpsMap.put(0, 0);

        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                lpsMap.put(i, len);
                i++;
            } else {
                if (len == 0) {
                    lpsMap.put(i, 0);
                    i++;
                } else {
                    len = lpsMap.get(len - 1);
                }

            }
        }
        return lpsMap;
    }

}
