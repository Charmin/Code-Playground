package algorithms.graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidPathCircles {

    public static void main(String[] args) {
        int A = 41;
        int B = 67;
        int C = 5;
        int D = 0;

        List<Integer> E = Arrays.asList(5,17, 16, 12, 0, 40);
        List<Integer> F = Arrays.asList(5, 52, 61, 61, 25, 31);
        Set<String> unReachable = new HashSet<>();

        for (int i = 0; i <= A; i++) {
            for (int j = 0; j <= B; j++) {
                for (int k = 0; k < C; k++) {
                    System.out.println("i:"+i+"j:"+j+"k:"+k+" dis: "+Math.sqrt(Math.pow(j - F.get(k), 2) + Math.pow(i - E.get(k), 2)));
                    if (Math.sqrt(Math.pow(j - F.get(k), 2) + Math.pow(i - E.get(k), 2)) <= D) {
                        unReachable.add(i + "_" + j);
                    }
                }
            }
        }

        System.out.println(possible(0, 0, A, B, unReachable, new HashSet<>()) ? "YES" : "NO");
    }


    public static boolean possible(int startX, int startY, int endX, int endY, Set<String> unReachableX, Set<String> visited) {
        if (startX == endX && startY == endY) {
            return true;
        }

        if (!visited.contains(startX+"_"+startY)) {
            if (unReachableX.contains(startX+"_"+startY) || startX < 0
                    || startX > endX || startY < 0 || startY > endY) {
                return false;
            }
            visited.add(startX+"_"+startY);
            return (possible(startX + 1, startY, endX, endY, unReachableX, visited) ||
                    possible(startX + 1, startY + 1, endX, endY, unReachableX, visited) ||
                    possible(startX, startY + 1, endX, endY, unReachableX, visited) ||
                    possible(startX - 1, startY + 1, endX, endY, unReachableX, visited) ||
                    possible(startX - 1, startY, endX, endY, unReachableX, visited) ||
                    possible(startX - 1, startY - 1, endX, endY, unReachableX, visited) ||
                    possible(startX, startY - 1, endX, endY, unReachableX, visited) ||
                    possible(startX + 1, startY - 1, endX, endY, unReachableX, visited));
        }
        return false;
    }
}
