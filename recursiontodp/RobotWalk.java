package recursiontodp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wanghu
 * @discrption： 假设有排成一行的N个位置记为1到N，N一定大于等于2，开始时机器人在其中的M位置上，M一定是1到N中的一个，
 * 如果机器人来到1位置那么下一步只能往右来到2位置，如果机器人来到N位置那么下一步只能往左来到N-1的位置，中间位置可往左可往右，
 * 求机器人在start位置上，走K步来到aim位置的所有走法数。
 * @create 2022-06-16 9:24
 */
public class RobotWalk {

    // 暴力递归
    public int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(N, aim, start, K);
    }

    public int process1(int N, int aim, int cur, int rest) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(N, aim, cur + 1, rest - 1);
        }
        if (cur == N) {
            return process1(N, aim, cur - 1, rest - 1);
        }
        return process1(N, aim, cur - 1, rest - 1) + process1(N, aim, cur + 1, rest - 1);
    }

    // 加缓存表
    public int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process2(N, aim, start, K, dp);
    }

    public int process2(int N, int aim, int cur, int rest, int[][] dp) {
        if (dp[cur][rest] != -1)
            return dp[cur][rest];

        int ans = 0;
        if (rest == 0)
            ans = cur == aim ? 1 : 0;
        else if (cur == 1)
            ans = process2(N, aim, cur + 1, rest - 1, dp);
        else if (cur == N)
            ans = process2(N, aim, cur - 1, rest - 1, dp);
        else
            ans = process2(N, aim, cur - 1, rest - 1, dp) + process2(N, aim, cur + 1, rest - 1, dp);

        dp[cur][rest] = ans;
        return ans;
    }

    // 动态规划
    public int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int i=1; i<K+1; i++){
            dp[1][i] = dp[2][i-1];
            for (int j=2; j<N; j++){
                dp[j][i] = dp[j-1][i-1] + dp[j+1][i-1];
            }
            dp[N][i] = dp[N-1][i-1];
        }
        return dp[start][K];
    }

    @Test
    public void test() {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
