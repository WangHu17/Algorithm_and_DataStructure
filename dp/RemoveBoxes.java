package dp;

/**
 * @author wanghu
 * @discrption： 给定一个数组，数组中连续且相等的值可以消掉，并得到 (消掉的个数 * 消掉的个数) 的分值，求消掉数组所有值能得到的最大分值。
 * @create 2022-07-26 14:39
 */
public class RemoveBoxes {

    // 递归 + 缓存
    public int removeBoxes(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process(boxes, 0, N - 1, 0, dp);
    }

    public int process(int[] boxes, int L, int R, int k, int[][][] dp) {
        if (L > R) return 0;
        if (dp[L][R][k] != 0) return dp[L][R][k];
        int ans = process(boxes, L + 1, R, 0, dp) + (k + 1) * (k + 1);
        for (int i = L + 1; i <= R; i++) {
            if (boxes[i] == boxes[L]) {
                int p = process(boxes, L + 1, i - 1, 0, dp) + process(boxes, i, R, k + 1, dp);
                ans = Math.max(ans, p);
            }
        }
        dp[L][R][k] = ans;
        return ans;
    }

    // 优化
    public int removeBoxes1(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process1(boxes, 0, N - 1, 0, dp);
    }

    public int process1(int[] boxes, int L, int R, int k, int[][][] dp) {
        if (L > R) return 0;
        if (dp[L][R][k] != 0) return dp[L][R][k];
        int last = L;
        while (last + 1 <= R && boxes[last + 1] == boxes[L]) {
            last++;
        }
        int pre = k + last - L + 1;
        int ans = process1(boxes, last + 1, R, 0, dp) + pre * pre;
        for (int i = last + 2; i <= R; i++) {
            if (boxes[i] == boxes[L] && boxes[i-1] != boxes[L]) {
                int p = process1(boxes, last + 1, i - 1, 0, dp) + process1(boxes, i, R, pre, dp);
                ans = Math.max(ans, p);
            }
        }
        dp[L][R][k] = ans;
        return ans;
    }

}
