package interviews.microsoft;

public class MinMoves {

    //Min number of moved required to have no 3 consecutive characters, in String having only a and b.
    //"baaaaa" => 1
    //"baaabbaabbba" =>

    public static void main(String[] args) {
        int res = getMoves("baaaaa");
        int res2 = getMoves("baabab");
        int res3 = getMoves("baaabbaabbba");
        System.out.println(res);
        System.out.println(res2);
        System.out.println(res3);
    }

    private static int getMoves(String s) {
        int moves = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = 1;
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                len++;
                i++;
            }
            moves += (len / 3);
        }

        return moves;
    }

}
