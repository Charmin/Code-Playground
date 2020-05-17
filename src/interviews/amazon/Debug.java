package interviews.amazon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Debug {
    static int res = -1;

    public static void main(String[] args) {
        int k = 2;
        int[] in = {1, 3, 4};
        List<Integer> p = new ArrayList<>();

        int[][] grid = {{2, 1, 0, 2}};

        String[] ins = {"day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        System.out.println(topKFrequent(ins, 2));
        int[] kl = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(shipWithinDays(kl, 5));

        String word = "aaaaaa";
        String word1 = "pineapplepenapple";
        System.out.println(word1);
        List<String> breaks = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa");
        List<String> breaks1 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak(word1, breaks1));

        System.out.println(numTilePossibilities("SEE"));


        int[] m = {3, 3, -1, 2};
        int[] t = {0, 0, 162, 914};
        System.out.println("Nums:" + numOfMinutes(4, 2, m, t));

        int[] l = {2, 1, 3, 5, 4};
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        Character.hashCode(board[0][1]);
        System.out.println(exist(board, "SEE"));

        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
        countAndSay(4);

        int[] color = {1, 2, 2, 2, 2, 0, 0, 0, 1, 1};
        sortColors(color);


        String[] ban = {"a"};
        System.out.println(mostCommonWord("a, a, a, a, b,b,b,c, c", ban));

        int[][] fore = {
                {1, 2, 3},
                {0, 0, 4},
                {7, 6, 5}
        };

        List<List<Integer>> input = new ArrayList<>();
        for (int[] f : fore) {
            List<Integer> i = IntStream.of(f).boxed().collect(Collectors.toList());
            input.add(i);
        }
        System.out.println(rotatedDigits(10));

        System.out.println(maxDistToClosest(new int[]{1, 0, 0, 0}));

        System.out.println(divide1(100, 4));
    }

    public static int divide1(int dividend, int divisor) {
        if (dividend == 0)
            return dividend;
        // sign 1 for negative, 0 for positive
        int sign = ((dividend ^ divisor)) >>> 31;

        long long_dividend = Math.abs((long)dividend);
        long long_divisor = Math.abs((long)divisor);

        long count = 0;
        long power = 1;

        // Multiply by two will divisor > dividend
        while (long_divisor <= long_dividend) {
            long_divisor <<= 1;
            power <<= 1;
        }

        // we did 1 extra
        long_divisor >>= 1;
        power >>= 1;

        while (long_divisor > 0) {
            if (long_dividend >= long_divisor) {
                count += power;
                long_dividend -= long_divisor;
            }
            long_divisor >>= 1;
            power >>= 1;
        }

        count = sign == 1 ? 0 - count : count;

        if (count >= Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        return (int)count;
    }

    public static int divide(int dividend, int divisor) {
        long dvd = dividend;
        long div = divisor;
        long res = 0;
        int sign = 1;
        if ((dvd < 0 && div > 0) || (dvd > 0 && div < 0)) {
            sign = -1;
        }

        div = Math.abs((long) divisor);
        dvd = Math.abs((long) dividend);

        if (div == 1) {
            //if (dvd == Integer.MIN_VALUE) {
            //     res = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            //} else {
            res = dvd * sign;
            //}
        } else {
            // if (dvd == Integer.MIN_VALUE) {
            //     res = sign == 1 ? 0 : (Integer.MAX_VALUE+1) / 2;
            //     res = res * sign;
            // }
            while (dvd >= div) {
                dvd = dvd - div;
                res++;
            }
            res = res * sign;
        }

        return (int) res;
    }

    public static int maxDistToClosest(int[] seats) {
        int i = 0;
        int j = i + 1;
        int max = 0;

        while (j < seats.length) {
            while (i < seats.length && seats[i] == 0) {
                i++;
            }
            while (j < seats.length && seats[j] == 0) {
                j++;
            }

            if (i == j && j == seats.length-1) { //00001
                return j;
            }
            if (j < seats.length) {
                max = Math.max(max, (j - i - 1) / 2);
                i = j;
                j++;
            }
        }
        return Math.max(max, (seats[j-1] == 1 ? (j-i-1) / 2 : j-i-1));
    }


    public static void cutOffTree(List<List<Integer>> forest) {
        Map<Integer, PriorityQueue<Integer>> adj = new HashMap<>();
        int[][] graph = new int[forest.size()][forest.get(0).size()];

        for (int c = 0; c < forest.size(); c++) {
            for (int c1 = 0; c1 < forest.get(0).size(); c1++) {
                graph[c][c1] = forest.get(c).get(c1);
            }
        }

        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        dfs(0, 0, graph, visited, 0);
    }

    public static int rotatedDigits(int N) {
        Map<Integer, Integer> rotMap = new HashMap<>();
        rotMap.put(0, 0);
        rotMap.put(1, 1);
        rotMap.put(8, 8);
        rotMap.put(2, 5);
        rotMap.put(6, 9);
        rotMap.put(5, 2);
        rotMap.put(9, 6);

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isValid(i, rotMap)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValid(int N, Map<Integer, Integer> rot) {
        int r = 0;
        int res = 0;
        int i = 0;
        int nOrigin = N;
        while (N >= 10) {
            r = N % 10;
            if (rot.containsKey(r)) {
                res = res + rot.get(r) * (int) Math.pow(10, i);
                i++;
            } else {
                return false;
            }
            N = N / 10;
        }

        if (rot.get(N) != null) {
            res = res + rot.get(N) * (int) Math.pow(10, i);
        } else {
            return false;
        }

        return res != nOrigin;

    }

    private static void dfs(int x, int y, int[][] forest, Set<Integer> visited, int level) {
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        PriorityQueue<Elem> q = new PriorityQueue<>();
        for (int[] d : dir) {
            int x1 = x + d[0];
            int y1 = y + d[0];
            int code = forest[0].length * x1 + y1;
            if (x1 >= 0 && y1 >= 0 && x1 < forest.length && y1 < forest[0].length
                    && !visited.contains(forest[x1][y1])) {
                Elem e = new Elem(code, forest[x1][y1]);
                q.add(e);
            }
        }

        while (!q.isEmpty()) {
            Elem tree = q.poll();
            visited.add(tree.code);
            int m = tree.code / forest[0].length;
            int n = tree.code % forest[0].length;
            if (forest[m][n] > tree.el) {
                res = Math.max(res, level + 1);
                dfs(m, n, forest, visited, level + 1);
            } else {
                res = -1;
                return;
            }
        }
    }


    public static String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bans = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        String[] splits = paragraph.split("\\s|,");
        for (String sp : splits) {
            sp = sp.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!sp.isEmpty()) {
                map.putIfAbsent(sp, 0);
                map.put(sp, map.get(sp) + 1);
            }
        }
        int f = 0;
        String res = null;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (!bans.contains(e.getKey()) && e.getValue() > f) {
                f = e.getValue();
                res = e.getKey();
            }
        }
        return res;
    }

    public static void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int least = 2;
        int highest = 0;

        for (int l : nums) {
            least = Math.min(l, least);
            highest = Math.max(l, highest);
        }

        while (left < right) {
            while (left < nums.length && nums[left] == least) {
                left++;
            }

            while (right >= 0 && nums[right] == highest) {
                right--;
            }

            while (left < nums.length && right >= 0 && nums[left] == 1 && nums[right] == 1) {
                right--;
            }

            if (left < nums.length && right >= 0 && left < right) {
                if (nums[left] != least || nums[right] != highest) {
                    swap(nums, left, right);
                }
                while (left > 0) {
                    left--;
                }
                while (right < nums.length - 1) {
                    right++;
                }
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

    public static void countAndSay(int n) {
        StringBuilder s = new StringBuilder();
        if (n == 1) System.out.println("1");

        s.append("11");//for n = 2
        for (int i = 3; i <= n; i++) {
            s = cs(s);
        }
        System.out.println(s.toString());
    }

    private static StringBuilder cs(StringBuilder ls) {
        StringBuilder b = new StringBuilder();
        int c = 1;
        int i = 1;
        if (ls.length() > 1) {
            while (i < ls.length()) {
                if (ls.charAt(i) == ls.charAt(i - 1)) {
                    c++;
                } else {
                    b.append(c);
                    b.append(ls.charAt(i - 1));
                    c = 1;
                }
                i++;
            }
            b.append(c);
            b.append(ls.charAt(i - 1));
        }
        return b;

    }

    public int smallestDivisor(int[] nums, int threshold) {
        int h = nums[nums.length - 1];
        int l = 1;
        int[] ranges = new int[h];
        int div = search(ranges, 1, h, nums, threshold);
        return div;
    }

    private int search(int[] ranges, int l, int h, int[] nums, int t) {
        int mid = (h + l) / 2;

        int sum = 0;
        for (int n : nums) {
            int d = (n % mid) > 0 ? (n / mid) + 1 : n / mid;
            sum += d;
        }

        if (sum > t) {
            mid = search(ranges, mid + 1, h, nums, t);
        } else {
            mid = search(ranges, 1, mid - 1, nums, t);
        }
        return mid;
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();

        for (String w : words) {
            if (!counts.containsKey(w)) {
                counts.put(w, 0);
            }
            counts.put(w, counts.get(w) + 1);
        }

        PriorityQueue<El> q = new PriorityQueue<>((a, b) -> {
            if (a.f == b.f) {
                return a.word.compareTo(b.word);
            } else {
                return b.f - a.f;
            }
        });

        for (Map.Entry<String, Integer> m : counts.entrySet()) {
            q.add(new El(m.getKey(), m.getValue()));
        }

        List<String> result = new ArrayList<>();
        while (!q.isEmpty() && k > 0) {
            El e = q.poll();
            result.add(e.word);
            k--;
        }

        return result;
    }

    public static int shipWithinDays(int[] weights, int D) {
        int low = 0;
        int high = 0;

        for (int i = 0; i < weights.length; i++) {
            low = Math.max(low, weights[i]); // the minimum capacity is the max weight
            high += weights[i]; // the max capacity is the sum of all weights;
        }

        //the ans lies between low and high
        while (low < high) {
            int mid = (low + high) / 2;
            if (canDivideInParts(weights, D, mid)) {
                high = mid; //try smaller capacity
            } else {
                low = mid + 1;
            }
        }

        return low;
    }


    //can the array be divided into D parts, of size "size" each
    private static boolean canDivideInParts(int[] weights, int D, int size) {
        int sum = 0;
        int parts = 1;
        int i = 0;

        while (i < weights.length) {
            int total = sum + weights[i];
            if (total > size) {
                if (sum == 0) {
                    return false;
                }
                parts++;
                sum = weights[i];
            } else {
                sum = total;
            }

            i++;
        }

        return parts <= D;
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        LinkedList<String> temp = new LinkedList<>();
        Set<String> dictSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        result = getSent(s, 0, dictSet, memo);
        return result;
    }

    private static List<String> getSent(String s, int i, Set<String> dict, Map<Integer, List<String>> memo) {

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        List<String> res = new ArrayList<>();

        if (i == s.length()) {
            res.add("");
            return res;
        }

        for (int j = i + 1; j <= s.length(); j++) {
            String u = s.substring(i, j);
            if (dict.contains(u)) {
                List<String> temp = getSent(s, j, dict, memo);
                for (String suffix : temp) {
                    if (suffix.equals("")) {
                        res.add(u + "");
                    } else {
                        res.add(u + " " + suffix);
                    }
                }
            }
        }

        memo.put(i, res);
        return res;
    }


    private static List<String> getSentMine(String s, int i, Set<String> dict, Map<Integer, List<String>> memo) {

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        List<String> res = new ArrayList<>();

        for (int j = i + 1; j <= s.length(); j++) {
            String u = s.substring(i, j);
            if (dict.contains(u)) {

                if (j == s.length()) {
                    res.add(u + "");
                } else {
                    List<String> t = getSent(s, j, dict, memo);
                    res.add(u + " " + t.get(0));
                }
            }
        }

        memo.put(i, res);
        return memo.get(i);
    }

    private static String makeSentence(List<String> bag) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < bag.size() - 1; i++) {
            b.append(bag.get(i)).append(" ");
        }
        b.append(bag.get(bag.size() - 1));
        return b.toString();
    }


    public static int numTilePossibilities(String tiles) {
        List<List<Character>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        //getSeq(tiles, 0, new HashSet<>(), new LinkedList<>(), res);
        Set<String> see = new HashSet<>();
        tileDFS(tiles, "", see);
        for (String c : see) {
            System.out.println(c);
        }
        return see.size();
    }

    private static void tileDFS(String tiles, String pre, Set<String> result) {
        if (tiles.length() == 0) {
            if (pre.length() != 0) {
                result.add(pre);
            }
            return;
        }

        for (int i = 0; i < tiles.length(); i++) {
            char c = tiles.charAt(i);

            String before = tiles.substring(0, i);
            String after = tiles.substring(i + 1);

            String comb = before + after;
            tileDFS(comb, pre, result);
            tileDFS(comb, pre + c, result);
        }
    }

    private static void getSeq(String tiles, int i, Set<
            String> visited, LinkedList<Character> temp, List<List<Character>> list) {
        if (i > tiles.length()) {
            return;
        }


        if (!temp.isEmpty()) {
            List<Character> copy = new ArrayList<>(temp);
            list.add(copy);
        }


        for (int j = i; j < tiles.length(); j++) {
            //if (!visited.contains(String.valueOf(tiles.charAt(j)))) {
            visited.add(String.valueOf(tiles.charAt(j)));
            temp.add(tiles.charAt(j));
            getSeq(tiles, j + 1, visited, temp, list);
            temp.removeLast();
            visited.remove(String.valueOf(tiles.charAt(j)));
            // }
        }
    }

    private static String swap(String t, int i, int j) {
        char[] res = t.toCharArray();
        char c = t.charAt(i);
        res[i] = res[j];
        res[j] = c;
        return String.valueOf(res);
    }


    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> subMap = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            subMap.computeIfAbsent(manager[i], k -> new ArrayList<>());
            subMap.get(manager[i]).add(i);
        }
        return getMins(headID, subMap, informTime, visited);
    }

    private static int getMins(int index, Map<Integer, List<Integer>> subs, int[] informTime, Set<
            Integer> visited) {
        int time = 0;
        Emp emp = new Emp(index, time);
        Queue<Emp> q = new ArrayDeque<>();
        q.add(emp);
        visited.add(emp.id);
        while (!q.isEmpty()) {
            Emp p = q.poll();
            if (subs.get(p.id) != null) {
                List<Integer> s = subs.get(p.id);
                for (Integer integer : s) {
                    if (!visited.contains(integer)) {
                        q.add(new Emp(integer, p.delay + informTime[p.id]));
                        time = Math.max(time, p.delay + informTime[p.id]);
                        visited.add(integer);
                    }
                }
            }
        }
        return time;
    }


    public static int numTimesAllBlue(int[] light) {

        Map<Integer, Character> state = new HashMap<>();
        for (int i : light) {
            state.put(i, 'O');
        }
        return getTime(state, light);
    }

    private static int getTime(Map<Integer, Character> state, int[] light) {

        int times = 0;
        for (int l : light) {
            state.put(l, 'O');
        }

        List<Integer> ybulbs = new LinkedList<>();
        for (int i1 : light) { //i represents the moment
            if (i1 == 1) {
                state.put(1, 'B');
            } else if (state.get(i1 - 1) == 'Y' || state.get(i1 - 1) == 'B') {
                state.put(i1, 'B');
                if (i1 < light.length && state.get(i1 + 1) == 'Y') {
                    state.put(i1 + 1, 'B');
                    ybulbs.remove(i1 + 1);
                }
            } else {
                state.put(i1, 'Y');
                ybulbs.add(i1);
                if (i1 < light.length && state.get(i1 + 1) == 'Y') {
                    state.put(i1 + 1, 'B');
                    ybulbs.remove(i1 + 1);
                }
            }

            for (int j = 0; j < ybulbs.size(); j++) {
                if (state.get(ybulbs.get(j) - 1) == 'Y' || state.get(ybulbs.get(j) - 1) == 'B') {
                    state.put(ybulbs.get(j), 'B');
                    ybulbs.remove(i1 + 1);
                }
            }

            if (ybulbs.size() == 0) {
                times++;
            }
        }
        return times;
    }


    public static boolean exist(char[][] board, String word) {
        boolean res = false;
        int len = board.length;
        int cols = board[0].length;

        if (word == null || word.isEmpty()) return false;
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, 1, cols * i + j, visited)) return true;
                    visited.clear();
                }
            }
        }
        double max = (int) Math.pow(2, 1);
        int a = 1;
        int b = 2;
        while ((a & b) == 1) {

        }
        return res;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;

        int max = (int) Math.pow(2, n);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            List<Integer> r = new ArrayList<>();
            int k = i;
            int j = 0;
            while (k > 0 && j < nums.length) {
                if ((k & 1) == 1)
                    r.add(nums[j]);
                k = k >> 1;
                j++;
            }
            res.add(r);
        }
        return res;
    }

    private static boolean dfs(char[][] board, String w, int dep, int pos, Set<Integer> visited) {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(pos);
        visited.add(pos);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int cols = board[0].length;
        int rows = board.length;

        int ro = pos / cols;
        int co = pos % cols;

        LinkedList<Character> ch = new LinkedList<>();
        ch.add(board[ro][co]);

        for (int[] d : dirs) {
            int r = ro + d[0];
            int c = co + d[1];

            if (dep == w.length()) {
                return true;
            }

            int posNew = cols * r + c;
            if (r >= 0 && c >= 0 && r < rows && c < cols && !visited.contains(posNew)) {
                if (board[r][c] == w.charAt(dep)) {
                    visited.add(posNew);
                    dep++;
                    if (dfs(board, w, dep, posNew, visited)) return true;
                    dep--;
                    visited.remove(posNew);
                }
            }
        }
        return false;
    }

}

class Emp {
    int id;
    int delay;

    public Emp(int i, int d) {
        this.id = i;
        this.delay = d;
    }
}

class El {

    String word;
    int f;

    public El(String w, int g) {
        this.word = w;
        this.f = g;
    }
}

class Elem {
    int code;
    int el;

    Elem(int code, int el) {
        this.code = code;
        this.el = el;
    }
}





