package monotonicstack;

/**
 * @author wanghu
 * @discrption： 子数组最小值的累加和
 * 给定一个数组arr，返回所有子数组最小值的累加和。
 * @create 2022-06-29 10:56
 */
public class SumSubarrayMins {

    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        long res = 0;
        int[][] nearLess = getNearLess(arr);
        for (int i = 0; i < N; i++) {
            int left = nearLess[i][0];
            int right = nearLess[i][1];
            res += (long)(i - left) * (long)(right - i) * (long)arr[i];
            res %= 1000000007;
        }
        return (int)res;
    }

    public int[][] getNearLess(int[] arr) {
        int N = arr.length;
        int[][] res = new int[N][2];
        int[] stack = new int[N];
        int size = -1;
        for (int i = 0; i < N; i++) {
            while (size != -1 && arr[stack[size]] >= arr[i]) {
                int j = stack[size--];
                int left = size == -1 ? -1 : stack[size];
                res[j][0] = left;
                res[j][1] = i;
            }
            stack[++size] = i;
        }
        while (size != -1) {
            int j = stack[size--];
            int left = size == -1 ? -1 : stack[size];
            res[j][0] = left;
            res[j][1] = N;
        }
        return res;
    }

}
