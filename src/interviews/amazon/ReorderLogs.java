package interviews.amazon;

import java.util.ArrayList;
import java.util.List;

public class ReorderLogs {

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] res = reorderLogFiles(logs);
        for(String r : res) {
            System.out.println(r);
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        List<String> dLogs = new ArrayList<>();
        for (String s : logs) {
            if (getType(s).equals("let")) {
                dLogs.add(s);
            }
        }
        dLogs.sort((o1, o2) -> {
            String k1 = o1.split("\\W+")[0];
            String v1 = o1.substring(k1.length() + 1);
            String k2 = o2.split("\\W+")[0];
            String v2 = o2.substring(k2.length() + 1);
            if (v1.equals(v2)) {
                return k1.compareTo(k2);
            }
            return v1.compareTo(v2);
        });

        for (String s : logs) {
            if (getType(s).equals("dig")) {
                dLogs.add(s);
            }
        }
        String[] res = new String[logs.length];
        int i = 0;
        for (String g : dLogs) {
            res[i] = g;
            i++;
        }
        return res;
    }

    private static String getType(String log) {
        String v = log.split("\\W+")[1];
        if (v.matches("\\d+")) {
            return "dig";
        } else {
            return "let";
        }
    }
}
