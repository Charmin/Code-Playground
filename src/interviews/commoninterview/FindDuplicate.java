package interviews.commoninterview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindDuplicate {

    public static void main(String[] args) {
        int[] dups = {7,9,7,4,2,8,7,7,1,5};
        System.out.println(findDuplicate(dups));
    }

    public static int findDuplicate(int[] nums) {
        List<Integer> arr = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        int dup = -1;
        for (int i = 0; i < nums.length; i++) {
            if (arr.get(i) == dup) {
                return arr.get(i);
            }
            dup = arr.get(i);
        }
        return dup;
    }
}
