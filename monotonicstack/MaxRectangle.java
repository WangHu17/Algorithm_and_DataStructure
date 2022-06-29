package monotonicstack;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-28 18:23
 */
public class MaxRectangle {

    public int maxRectangle(int[][] arr) {
        int N = arr[0].length;
        int[] height = new int[N];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < N; j++) {
                height[j] = arr[i][j] == 0 ? 0 : height[j] + 1;
            }
            max = Math.max(max, LargestRectangleArea.largestRectangleArea(height));
        }
        return max;
    }

}
