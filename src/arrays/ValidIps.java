package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidIps {


    public static void main(String[] args) {
        String in = "0100100";
        List<String> ips = restoreIpAddresses(in);
        System.out.println(ips);
    }

    public static ArrayList<String> restoreIpAddresses(String A) {

        int len = A.length();
        if (len > 12 || len < 4) {
            return new ArrayList<>();
        }
        ArrayList<String> validIps = getValidIps(A);
        return validIps;
    }

    private static ArrayList<String> getValidIps(String ip) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 1; i < ip.length()-2; i++) {
            for (int j = i + 1; j < ip.length()-1; j++) {
                for (int k = j + 1; k < ip.length(); k++) {
                    String s = ip;
                    s = s.substring(0,k)+"."+s.substring(k,s.length());
                    s = s.substring(0,j)+"."+s.substring(j,s.length());
                    s = s.substring(0,i)+"."+s.substring(i,s.length());if (isValid(s)) {
                        res.add(s);
                    }
                }
            }
        }

        Collections.sort(res, (a, p) -> {
            String[] s1 = a.split("\\.");
            String[] s2 = p.split("\\.");

            for (int i = 0; i < 4; i++) {
                int a1 = Integer.parseInt(s1[i]);
                int a2 = Integer.parseInt(s2[i]);
                if (a1 > a2) {
                    return 1;
                } else if (a1 < a2) {
                    return -1;
                }
            }
            return 0;
        });

        return res;
    }

    private static boolean isValid(String a) {
        String[] v = a.split("\\.");
        for (int i = 0; i < 4; i++) {
            String b = v[i];
            if (b.length() > 3) {
                return false;
            }
            if (b.length() > 1 && (b.charAt(0) == '0')) {
                return false;
            }
            if (b.length() == 3 && Integer.parseInt(b) > 255) {
                return false;
            }
        }
        return true;
    }
}
