package dp;

/**
 * @author wanghu
 * @discrption： 一个打印机，一次作业只能打印一种字符，每次在指定范围打印，例如字符”111222111“，第一次打印成”111111111“，
 * 第二次把中间的重新打印成2，即”111222111“。给定一个字符串，问最少打印几次可以打印出。
 * @create 2022-07-28 10:29
 */
public class StrangePrinter {

    // 暴力递归
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    private int process(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        int ans = R - L + 1;
        for (int i = L + 1; i <= R; i++) {
            ans = Math.min(ans, process(str, L, i - 1) + process(str, i, R) - (str[i] == str[L] ? 1 : 0));
        }
        return ans;
    }

    // 动态规划
    public int strangePrinter2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 1 : 2;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = R - L + 1;
                for (int i = L + 1; i <= R; i++) {
                    dp[L][R] = Math.min(dp[L][R], dp[L][i - 1] + dp[i][R] - (str[i] == str[L] ? 1 : 0));
                }
            }
        }
        return dp[0][N-1];
    }

}
