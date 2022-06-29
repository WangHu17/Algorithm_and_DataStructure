package monotonicstack;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-29 9:35
 */
public class NumOfRectangle {

    public int numOfRectangle(char[][] arr) {
        if (arr == null || arr.length == 0) return 0;
        int row = arr.length;
        int col = arr[0].length;
        int[] height = new int[col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                height[j] = arr[i][j] == '0' ? 0 : height[j] + 1;
            }
            res += getNumOfRectangle(height);
        }
        return res;
    }

    private int getNumOfRectangle(int[] height) {
        if (height == null || height.length == 0)return 0;
        int N = height.length;
        int[] stack = new int[N];
        int size = -1;
        int res = 0;
        for (int i=0; i<N; i++){
            while (size != -1 && height[stack[size]] >= height[i]){
                int j = stack[size--];
                int left = size == -1 ? -1 : stack[size];
                int width = i-1-left;
                int down = Math.max(left == -1 ? 0 : height[left], height[i]);
                res += (height[j] - down) * (width*(width+1)/2);
            }
            stack[++size] = i;
        }
        while (size != -1){
            int j = stack[size--];
            int left = size == -1 ? -1 : stack[size];
            int width = N-1-left;
            int down = left == -1 ? 0 : height[left];
            res += (height[j] - down) * (width*(width+1)/2);
        }
        return res;
    }

}
