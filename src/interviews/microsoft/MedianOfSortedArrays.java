package interviews.microsoft;

import javafx.util.Pair;

public class MedianOfSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        //find the left chunk size for the union
        int leftChunk = (m + n + 1)/2;

        //find the split points for nums1;
        //this is to ensure m <= n,and you have 0 indexed binary search
        if (m > n) {
            int[] tmp = nums1; nums1 = nums2; nums2 = tmp;
            int temp = m; m = n; n = temp;
        }

        int l = 0;
        int r = m;

        //binary search for the correct split point
        while (l <= r) {
           int mid = l + ((r - l) / 2);
           int other = leftChunk - mid;
           if (mid > 0 && other < nums2.length
                   && nums1[mid-1] > nums2[other]) {
               r = mid-1;
           } else if (mid < nums1.length && other > 0
                   && nums2[other-1] > nums1[mid]) {
               l = mid+1;
           } else {
               int maxLeft = 0;
               if (mid == 0) {
                   //all of nums2
                   maxLeft =  nums2[other-1];
               }
               if (mid == m) {
                   //all of num1
                   maxLeft = nums1[mid-1];
               }
               if ((m + n) % 2 == 1) {
                   return maxLeft;
               } else {
                   //get the min from the other sides
                   int minRight = Math.min(nums1[mid], nums2[other]);
                   return (maxLeft + minRight) / 2.0;
               }
           }
        }
        return 0.0;
    }
}
