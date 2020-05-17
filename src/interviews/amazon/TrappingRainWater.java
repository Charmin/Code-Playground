package interviews.amazon;

import java.util.LinkedList;

public class TrappingRainWater {

    public int trap(int[] height) {
        LinkedList<Integer> st = new LinkedList<>();
        int res = 0;
        st.push(0);

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[i] > height[st.peek()]) {
                int p = st.pop();
                if (!st.isEmpty()) {
                    int len = i - st.peek() - 1;
                    int boundHeight = (Math.min(height[i], height[st.peek()]) - height[p]);
                    res += len * boundHeight;
                }
            }
            st.push(i);
        }
        return res;
    }
}
