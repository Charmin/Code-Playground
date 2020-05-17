package interviews.microsoft.exp1;


// 1 2 3 4 5 6  t = 5
// 5 6 1 2 3 4  t = 1
// 5 6 7 8 1 2  t = 7
// 1 2 3 4 5 6  t =
// 5 6 1 2 3 4  t = 6  ignore right (maX)
// 5 6 7 8 1 2  t = 2  ignore left  (min)

public class SearchRotatedArray {

    private int getIndex(int start, int end, int target, int[] nums) {
        int targetIndex = -1;

        while (start <= end) {
            int mid = (end + start) / 2;
            int val = -1;
            //mid and target lie on the same side of nums[0]
            if ((nums[mid] < nums[0]) == (target < nums[0])) { //this is not same as a &&
                val = nums[mid];
            } else { //if the mid is greater than target
                val = target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;;
            }                                //rot on right,     //rot on left
                                             //target on right   //target on left

            if (val < target) {
                start = mid+1;
            } else if (val > target) {
                end = mid-1;
            } else {
                return mid;
            }
        }

        return targetIndex;
    }
}
