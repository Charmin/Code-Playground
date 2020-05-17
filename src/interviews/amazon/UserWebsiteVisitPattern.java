package interviews.amazon;

import java.util.*;

public class UserWebsiteVisitPattern {
    public static void main(String[] args) {
        String[] u = {"h", "eiy", "cq", "h", "cq", "txldsscx", "cq", "txldsscx", "h", "cq", "cq"};
        int[] times = {527896567, 334462937, 517687281, 134127993, 859112386, 159548699, 51100299, 444082139, 926837079, 317455832, 411747930};
        String[] w = {"hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "yljmntrclw", "hibympufi", "yljmntrclw"};

        List<String> res = mostVisitedPattern(u, times, w);
        res.forEach(s -> System.out.println(s));
    }

    public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<String>> map = new HashMap<>();
        Map<String, List<String[]>> unsorted = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            String[] o = new String[2];
            o[0] = website[i];
            o[1] = "" + timestamp[i];
            unsorted.computeIfAbsent(username[i], u -> new ArrayList<>()).add(o);
        }

        for (Map.Entry<String, List<String[]>> m : unsorted.entrySet()) {
            Collections.sort(m.getValue(), (a, b) -> (Integer.parseInt(a[1])) - Integer.parseInt(b[1]));
            for (String[] ms : m.getValue()) {
                map.computeIfAbsent(m.getKey(), u -> new ArrayList<>()).add(ms[0]);
            }
        }

        Map<String, Integer> freqHash = new HashMap<>();
        for (Map.Entry<String, List<String>> m : map.entrySet()) {
            if (m.getValue().size() == 3) { //possible 3 sequence
                String hash = getHash(m.getValue());
                if (freqHash.containsKey(hash)) {
                    freqHash.put(hash, freqHash.get(hash) + 1);
                } else {
                    freqHash.put(hash, 1);
                }
            } else if (m.getValue().size() > 3) {
                List<List<String>> inc3 = new ArrayList<>();
                getInc3Seq(m.getValue(), 0, 3, new LinkedList<>(), inc3);
                Set<String> hashes = new HashSet<>();
                for (List<String> s : inc3) {
                    String hash = s.size() == 3 ? getHash(s) : null;
                    if (hash != null && hashes.add(hash)) {
                        if (freqHash.containsKey(hash)) {
                            freqHash.put(hash, freqHash.get(hash) + 1);
                        } else {
                            freqHash.put(hash, 1);
                        }
                    }
                }
            }
        }
        int freq = 0;
        String h = null;
        for (Map.Entry<String, Integer> f : freqHash.entrySet()) {
            if (f.getValue() > freq) {
                freq = f.getValue();
                h = f.getKey();
            } else if (f.getValue() == freq){
                h = (h != null && h.compareTo(f.getKey()) < 0) ? h : f.getKey();
            }
        }
        List<String> res = new ArrayList<>();
        if (h != null) {
            String[] sp = h.split("-");
            res = Arrays.asList(sp);
        }
        return res;
    }

    private static String getHash(List<String> s) {
        return s.get(0) + "-" + s.get(1) + "-" + s.get(2);
    }


    private static void getInc3Seq(List<String> input, int start, int count, LinkedList<String> seq, List<List<String>> incSeq) {
        if (start == input.size() || count == 0) {
            if (seq.size() == 3) {
                List<String> res = new ArrayList<>();
                res.addAll(seq);
                incSeq.add(res);
            }
            return;
        }


        for (int i = start; i < input.size(); i++) {
            seq.add(input.get(i));
            getInc3Seq(input, i + 1, count - 1, seq, incSeq);
            seq.removeLast();
        }
    }
}
