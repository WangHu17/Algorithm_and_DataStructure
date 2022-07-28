package dp;

/**
 * @author wanghu
 * @discrption： 给定一个数组，表示每个气球的分值，打爆一个气球得到的分值=它的分值*它左边没爆的分值*它右边没爆的分值，如果左边或右边没有气球了，就乘1。求按不同的顺序打爆所有气球，能得到的最大分值。
 * @create 2022-07-26 9:54
 */
public class BurstBalloons {

    // 暴力递归
    public int maxV(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        if (arr.length == 1) return arr[0];
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 1; i < N + 1; i++) {
            help[i] = arr[i - 1];
        }
        return process(help, 1, N);
    }

    public int process(int[] help, int L, int R) {
        if (L == R) {
            return help[L] * help[L - 1] * help[R + 1];
        }
        int max = help[L - 1] * help[L] * help[R + 1] + process(help, L + 1, R);
        max = Math.max(max, help[L - 1] * help[R] * help[R + 1] + process(help, L, R - 1));
        for (int i = L + 1; i < R; i++) {
            int left = process(help, L, i - 1);
            int right = process(help, i + 1, R);
            int mid = help[L - 1] * help[i] * help[R + 1];
            max = Math.max(max, left + mid + right);
        }
        return max;
    }

    // 动态规划
    public int maxV2(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        if (arr.length == 1) return arr[0];
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 1; i < N + 1; i++) {
            help[i] = arr[i - 1];
        }
        int[][] dp = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
        }
        for (int L = N; L >= 1; L--) {
            for (int R = L + 1; R <= N; R++) {
                int ans = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
                ans = Math.max(ans, help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1]);
                for (int i = L + 1; i < R; i++) {
                    ans = Math.max(ans, dp[L][i - 1] + dp[i + 1][R] + help[L - 1] * help[i] * help[R + 1]);
                }
                dp[L][R] = ans;
            }
        }
        return dp[1][N];
    }

}
