package dp;

/**
 * @author wanghu
 * @discrption： 给定一个字符串，字符串中数量大于1连续且相同的字符可以消掉，求如何消除字符使得字符串剩余的字符最少，返回最少的字符数。
 * @create 2022-07-27 15:14
 */
public class RemoveChars {

    // 暴力递归 + 缓存
    public static int restMin1(String s) {
        if (s == null) return 0;
        if (s.length() < 2) return s.length();
        char[] str = s.toCharArray();
        int N = str.length;
        int[][][] dp = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++)
                    dp[i][j][k] = -1;
            }
        }
        return process(str, 0, N - 1, false, dp);
    }

    public static int process(char[] str, int L, int R, boolean has, int[][][] dp) {
        if (L > R) return 0;
        int k = has ? 1 : 0;
        if (dp[L][R][k] != -1) {
            return dp[L][R][k];
        }
        int ans = 0;
        if (L == R) {
            ans = has ? 0 : 1;
        } else {
            int index = L;
            int k1 = k;
            while (index <= R && str[index] == str[L]) {
                k1++;
                index++;
            }
            int ways1 = (k1 > 1 ? 0 : 1) + process(str, index, R, false, dp);
            int ways2 = Integer.MAX_VALUE;
            for (int i = index; i <= R; i++) {
                if (str[i] == str[L] && str[i] != str[i - 1]) {
                    if (process(str, index, i - 1, false, dp) == 0) {
                        ways2 = Math.min(ways2, process(str, i, R, k1 != 0, dp));
                    }
                }
            }
            ans = Math.min(ways1, ways2);
        }
        dp[L][R][k] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 16;
        int variety = 3;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            String str = randomString(len, variety);
            int ans1 = restMin1(str);
            int ans2 = restMin2(str);
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static String randomString(int len, int variety) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * variety) + 'a');
        }
        return String.valueOf(str);
    }

    // 暴力解
    public static int restMin2(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        int minLen = s.length();
        for (int L = 0; L < s.length(); L++) {
            for (int R = L + 1; R < s.length(); R++) {
                if (canDelete(s.substring(L, R + 1))) {
                    minLen = Math.min(minLen, restMin1(s.substring(0, L) + s.substring(R + 1, s.length())));
                }
            }
        }
        return minLen;
    }

    public static boolean canDelete(String s) {
        char[] str = s.toCharArray();
        for (int i = 1; i < str.length; i++) {
            if (str[i - 1] != str[i]) {
                return false;
            }
        }
        return true;
    }

}
