package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-17 15:36
 */
public class HorseJump {

    // 暴力递归
    public int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    private int process(int x, int y, int k, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) return 0;
        if (k == 0)
            return (x == a && y == b) ? 1 : 0;
        int ways = process(x + 1, y + 2, k - 1, a, b);
        ways += process(x + 2, y + 1, k - 1, a, b);
        ways += process(x + 2, y - 1, k - 1, a, b);
        ways += process(x + 1, y - 2, k - 1, a, b);
        ways += process(x - 1, y - 2, k - 1, a, b);
        ways += process(x - 2, y - 1, k - 1, a, b);
        ways += process(x - 1, y + 2, k - 1, a, b);
        ways += process(x - 2, y + 1, k - 1, a, b);
        return ways;
    }

    // 动态规划
    public int jump1(int a, int b, int k) {
        int[][][] dp = new int[10][9][k+1];
        dp[a][b][0] = 1;
        for (int m = 1; m <= k; m++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    int ways = get(i + 1, j + 2, m - 1, dp);
                    ways += get(i + 2, j + 1, m - 1, dp);
                    ways += get(i + 2, j - 1, m - 1, dp);
                    ways += get(i + 1, j - 2, m - 1, dp);
                    ways += get(i - 1, j - 2, m - 1, dp);
                    ways += get(i - 2, j - 1, m - 1, dp);
                    ways += get(i - 1, j + 2, m - 1, dp);
                    ways += get(i - 2, j + 1, m - 1, dp);
                    dp[i][j][m] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public int get(int x, int y, int k, int[][][] dp) {
        if (x < 0 || x > 9 || y < 0 || y > 8) return 0;
        return dp[x][y][k];
    }

    @Test
    public void test(){
        System.out.println(jump(7,7,10));
        System.out.println(jump1(7,7,10));
    }

}
