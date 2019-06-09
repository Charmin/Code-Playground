package problem_solving;



import java.util.*;
import java.util.LinkedList;

/**
 * Created by chaitra.kr on 12/03/17.
 */
public class Tshirts {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        int[] satisfiedShirts = new int[q];
        for(int a0 = 0; a0 < q; a0++){
            List<Integer> sizesList = new LinkedList<>();
            int n = in.nextInt();
            int satCount = 0;
            int[] sizes = new int[n];
            for(int sizes_i=0; sizes_i < n; sizes_i++){
                sizes[sizes_i] = in.nextInt();
                sizesList.add(sizes[sizes_i]);
            }
            int v = in.nextInt();
            for(int a1 = 0; a1 < v; a1++){
                int smallest = in.nextInt();
                int largest = in.nextInt();

                for(Iterator<Integer> it = sizesList.iterator(); it.hasNext();) {
                    boolean isFits = false;
                    isFits = checkIfitFits(smallest, largest, it.next());
                    if(isFits) {
                        satCount++;
                        it.remove();
                    }
                }
            }
            satisfiedShirts[a0] = satCount;
            System.out.println(satisfiedShirts[a0]);
        }

    }

    private static boolean checkIfitFits(int smallest, int largest, int size) {
        if(size>=smallest && size<=largest) return true;
        return false;
    }

}
