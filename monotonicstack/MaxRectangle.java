package monotonicstack;

/**
 * @author wanghu
 * @discrption： 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形，内部有多少个1。
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
