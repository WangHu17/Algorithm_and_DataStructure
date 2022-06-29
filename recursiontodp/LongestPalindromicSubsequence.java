package recursiontodp;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-17 14:59
 */
public class LongestPalindromicSubsequence {

    // 暴力递归
    public int longestPalindromicSubsequence1(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    private int process(char[] str, int l, int r) {
        if (l == r) return 1;
        if (l == r - 1) return str[l] == str[r] ? 2 : 1;
        int p1 = process(str, l + 1, r - 1);
        int p2 = process(str, l + 1, r);
        int p3 = process(str, l, r - 1);
        int p4 = str[l] == str[r] ? (2 + process(str, l + 1, r - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // 动态规划
    public int longestPalindromicSubsequence2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int l = N - 3; l >= 0; l--) {
            for (int r = l + 2; r < N; r++) {
                dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                if (str[l] == str[r])
                    dp[l][r] = Math.max(2 + dp[l + 1][r - 1], dp[l][r]);
            }
        }
        return dp[0][N-1];
    }

}
