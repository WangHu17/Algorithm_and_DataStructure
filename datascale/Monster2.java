package datascale;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： int[] d，d[i]：i号怪兽的能力
 * * int[] p，p[i]：i号怪兽要求的钱
 * * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；
 * * 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * * 返回通过所有的怪兽，需要花的最小钱数。
 * @create 2022-07-20 9:58
 */
public class Monster2 {

    // 贿赂每个怪兽需要的钱数不大，怪兽能力值巨大

    public int minMoney1(int[] d, int[] p) {
        int sum = 0;
        for (int m : p) {
            sum += m;
        }
        int N = d.length;
        for (int i = 0; i < sum; i++) {
            if (process(d, p, N - 1, i) != -1) {
                return i;
            }
        }
        return sum;
    }

    public int process(int[] d, int[] p, int index, int money) {
        if (index == -1) {
            return money == 0 ? 0 : -1;
        }
        int preMaxAbility = process(d, p, index - 1, money);
        int p1 = -1;
        if (preMaxAbility != -1 && preMaxAbility >= d[index]) {
            p1 = preMaxAbility;
        }
        int preMaxAbility2 = process(d, p, index - 1, money - p[index]);
        int p2 = -1;
        if (preMaxAbility2 != -1) {
            p2 = preMaxAbility2 + d[index];
        }
        return Math.max(p1, p2);
    }

    // 动态规划
    public int minMoney2(int[] d, int[] p) {
        int N = d.length;
        int sum = 0;
        for (int m : p) sum += m;
        int[][] dp = new int[N][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][p[0]] = d[0];
        for (int index = 1; index < N; index++) {
            for (int money = 0; money <= sum; money++) {
                // 不贿赂
                if (dp[index - 1][money] >= d[index]) {
                    dp[index][money] = dp[index - 1][money];
                }
                // 贿赂
                if (money - p[index] >= 0 && dp[index - 1][money - p[index]] != -1) {
                    dp[index][money] = Math.max(dp[index][money], dp[index - 1][money - p[index]] + d[index]);
                }
            }
        }
        for (int i = 0; i <= sum; i++) {
            if (dp[N - 1][i] != -1)
                return i;
        }
        return -1;
    }

    @Test
    public void test() {
        int len = 10;
        int value = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = minMoney1(d, p);
            long ans2 = minMoney2(d, p);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
    }

    public int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

}
