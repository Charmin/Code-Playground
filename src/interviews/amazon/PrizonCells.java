package interviews.amazon;

public class PrizonCells {

    public static void main(String[] args) {
        int[] c = {1,0,0,1,0,0,0,1};
        int[] res = prisonAfterNDays(c, 826);
        for (int i = 0; i < 8; i++) {
            System.out.print(res[i]+" ");
        }
    }

    public static int[] prisonAfterNDays(int[] cells, int N) {
        int[] res = new int[cells.length];
        int[] med = new int[cells.length];
        int k = 0;
        for (int p : cells) {
            med[k] = p;
            k++;
        }

        int N1 = N % 14 == 0 ? 14 : N % 14;
        for (int i = 1; i <= N1; i++) {
            for (int c = 1; c < 7; c++) {
                res[c] = (med[c-1] == med[c+1]) ? 1 : 0;
            }
            System.out.print(i+": ");
            for (int j = 0; j < 8; j++) {
                med[j] = res[j];
                System.out.print(med[j]+" ");
            }
            System.out.println();
        }
        return N == 0 ? cells : res;
    }
}
