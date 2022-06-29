package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-21 11:13
 */
public class MonsterDie {

    // 暴力递归：
    public double monsterDie1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) return 0;
        long all = (long) Math.pow(M + 1, K);
        long kill = process(M, K, N);
        return (double) kill / (double) all;
    }

    public long process(int M, int times, int rest) {
        if (times == 0)
            return rest <= 0 ? 1 : 0;
        if (rest <= 0)
            return (long) Math.pow(M + 1, times);
        long res = 0;
        for (int i = 0; i <= M; i++) {
            res += process(M, times - 1, rest - i);
        }
        return res;
    }

    // 动态规划：
    public double monsterDie2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) return 0;
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int rest = 1; rest <= N; rest++) {
                //优化前
//                long res = 0;
//                for (int i = 0; i <= M; i++) {
//                    if (rest - i >= 0) {
//                        res += dp[times - 1][rest - i];
//                    } else
//                        res += (long) Math.pow(M + 1, times - 1);
//                }
//                dp[times][rest] = res;
                // 优化后
                dp[times][rest] += dp[times][rest - 1] + dp[times - 1][rest];
                if (rest - 1 - M >= 0) {
                    dp[times][rest] -= dp[times - 1][rest - 1 - M];
                } else
                    dp[times][rest] -= Math.pow(M + 1, times - 1);
            }
        }
        return (double) dp[K][N] / Math.pow(M + 1, K);
    }

    @Test
    public void test() {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = monsterDie1(N, M, K);
            double ans2 = monsterDie2(N, M, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
