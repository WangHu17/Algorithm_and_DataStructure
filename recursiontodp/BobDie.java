package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 醉汉死亡问题
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置，Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位，
 * 任何时候Bob只要离开NM的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 * @create 2022-06-20 18:40
 */
public class BobDie {

    // 暴力递归：
    public double livePosibility1(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    public long process(int row, int col, int k, int N, int M) {
        if (row < 0 || row >= N || col < 0 || col >= M)
            return 0;
        if (k == 0) return 1;
        long up = process(row - 1, col, k - 1, N, M);
        long down = process(row + 1, col, k - 1, N, M);
        long left = process(row, col - 1, k - 1, N, M);
        long right = process(row, col + 1, k - 1, N, M);
        return up + down + left + right;
    }

    // 动态规划：
    public double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int m = 1; m <= k; m++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][m] += get(dp, i - 1, j, N, M, m - 1);
                    dp[i][j][m] += get(dp, i + 1, j, N, M, m - 1);
                    dp[i][j][m] += get(dp, i, j - 1, N, M, m - 1);
                    dp[i][j][m] += get(dp, i, j + 1, N, M, m - 1);

                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    private long get(long[][][] dp, int i, int j, int N, int M, int k) {
        if (i < 0 || i >= N || j < 0 || j >= M)
            return 0;
        return dp[i][j][k];
    }

    @Test
    public void test() {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }

}
