package datascale;

/**
 * @author wanghu
 * @discrption： 数组所有子序列中累加和%m之后的最大值
 * @create 2022-07-20 15:21
 */
public class SumModM2 {

    //m 不大，数组每个元素巨大

    public static int max3(int[] arr, int m) {
        int N = arr.length;
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j];
                int cur = arr[i] % m;
                if (cur <= j) {
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        for (int i = m-1; i >= 0; i--) {
            if (dp[N - 1][i]) {
                return i;
            }
        }
        return -1;
    }

}
