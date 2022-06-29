package recursiontodp;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-17 14:17
 */
public class LongestCommonSubsequence {

    // 暴力递归
    public int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return 0;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    private int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0)
            return str1[i] == str2[j] ? 1 : 0;
        else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else
                return process(str1, str2, i, j - 1);
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else
                return process(str1, str2, i - 1, j);
        } else {
            int p1 = process(str1, str2, i - 1, j);
            int p2 = process(str1, str2, i, j - 1);
            int p3 = str1[i] == str2[j] ? (1 + process(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    // 动态规划
    public int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return 0;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < len2; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < len1; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[len1-1][len2-1];
    }

}
