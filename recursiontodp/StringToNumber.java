package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 规定1对应A，2对应B，3对应C，依此类推26对应Z，那么一个数字字符串比如111就可以转化为AAA，KA，AK
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
 * @create 2022-06-16 15:21
 */
public class StringToNumber {

    // 暴力递归
    public int stringToNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        return process(s.toCharArray(), 0);
    }

    public int process(char[] str, int i) {
        if (i == str.length)
            return 1;
        if (str[i] == '0')
            return 0;
        int way = process(str, i + 1);
        if (i + 1 < str.length && ((str[i] - '0') * 10 + (str[i + 1] - '0') <= 26)) {
            way += process(str, i + 2);
        }
        return way;
    }

    // 动态规划
    public int stringToNumber1(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i=N-1; i>=0; i--){
            if (str[i] != '0'){
                dp[i] = dp[i+1];
                if (i + 1 < N && (str[i] - '0') * 10 + (str[i + 1] - '0') <= 26)
                    dp[i] += dp[i+2];
            }
        }
        return dp[0];
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    @Test
    public void test() {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = stringToNumber(s);
            int ans1 = stringToNumber1(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
