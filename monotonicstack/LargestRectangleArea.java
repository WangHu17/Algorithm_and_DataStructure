package monotonicstack;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-28 17:14
 */
public class LargestRectangleArea {

    public static int largestRectangleArea(int[] height) {
        int N = height.length;
        int[] stack = new int[N];
        int max = 0;
        int size = -1;
        for (int i = 0; i < N; i++) {
            while (size != -1 && height[stack[size]] >= height[i]) {
                int j = stack[size--];
                max = Math.max(max, (size == -1 ? i : (i - 1 - stack[size])) * height[j]);
            }
            stack[++size] = i;
        }
        while (size != -1) {
            int j = stack[size--];
            max = Math.max(max, (size == -1 ? N : (N - 1 - stack[size])) * height[j]);
        }
        return max;
    }

}
