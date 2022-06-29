package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 背包问题
 * @create 2022-06-16 14:34
 */
public class Knapsack {

    // 暴力递归
    public int knapsack(int[] w, int[] v, int capacity) {
        if (w == null || v == null || w.length != v.length || capacity == 0)
            return 0;
        return process(w, v, 0, capacity);
    }

    public int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0)
            return -1;
        if (index == w.length)
            return 0;
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1)
            p2 = v[index] + next;
        return Math.max(p1, p2);
    }

    // 动态规划
    public int knapsack1(int[] w, int[] v, int capacity) {
        if (w == null || v == null || w.length != v.length || capacity == 0)
            return 0;
        int N = w.length;
        int[][] dp = new int[N + 1][capacity + 1];
        int p1, p2;
        for (int i = N-1; i>=0; i--){
            for (int j=0; j<=capacity; j++){
                p1 = dp[i+1][j];
                p2 = j - w[i] < 0 ? -1 : v[i] + dp[i+1][j-w[i]];
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][capacity];
    }

    @Test
    public void test() {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(knapsack(weights, values, bag));
        System.out.println(knapsack1(weights, values, bag));
    }

}
