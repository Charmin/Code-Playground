package interviews.contest;

/**
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours:
 * Red, Yellow or Green while making sure that no two adjacent cells have the same colour
 * (i.e no two cells that share vertical or horizontal sides have the same colour).
 */
public class PaintGridWaysTrick {

    public static void main(String[] args) {
        int ans = numOfWays(2);
        System.out.println(ans);
    }

    public static int numOfWays(int n) {
        int a121 = 6;
        int a123 = 6;
        double mod = (Math.pow(10,9)) + 7;
        /* for each row(after the first row), get the next count of a121s and a123s */
        for (int i = 1; i < n; i++) {
            int a121ways = 3 * a121 + 2 * a123;
            int a123ways = 2 * a121 + 2 * a123;
            a121 = (int) (a121ways % mod);
            a123 = (int) (a123ways % mod);
        }

        return (int) ((a121 + a123) % mod);
    }
}
