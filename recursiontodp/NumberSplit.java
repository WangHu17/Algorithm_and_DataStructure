package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 数字分裂问题
 * 给定一个整数n，将它分裂成多个数，这些数的和等于n，要求分裂的数后面的不能小于前面的。
 * 例：3的分裂方法有三种，{1、1、1}、{1、2}、{3}。
 * 求给定的数有多少种裂开的方法。
 * @create 2022-06-22 9:36
 */
public class NumberSplit {

    // 暴力递归：
    public int numberSplit1(int N) {
        if (N < 0) return 0;
        if (N == 1) return 1;
        return process(1, N);
    }

    public int process(int pre, int rest) {
        if (rest < 0) return 0;
        if (rest == 0) return 1;
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }

    // 动态规划：
    public int numberSplit2(int N) {
        if (N < 0) return 0;
        if (N == 1) return 1;
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = N - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= N; rest++) {
                // 优化前
//                int ways = 0;
//                for (int first = pre; first <= rest; first++) {
//                    ways += dp[first][rest - first];
//                }
//                dp[pre][rest] = ways;
                // 优化后
                dp[pre][rest] = dp[pre+1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][N];
    }

    @Test
    public void test(){
        int N = 30;
        System.out.println(numberSplit1(N));
        System.out.println(numberSplit2(N));
    }

}
