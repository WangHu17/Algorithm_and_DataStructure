package datascale;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；
 * 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * 返回通过所有的怪兽，需要花的最小钱数。
 * @create 2022-07-18 11:11
 */
public class Monster1 {

    // 怪兽的能力值范围不大，贿赂需要的钱数巨大

    // 暴力递归
    public int minMoney(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    private int process(int[] d, int[] p, int ability, int index) {
        if (index == d.length) return 0;
        int money = 0;
        if (ability < d[index]) {
            return p[index] + process(d, p, ability + d[index], index + 1);
        } else {
            return Math.min(p[index] + process(d, p, ability + d[index], index + 1),
                    process(d, p, ability, index + 1));
        }
    }

    // 动态规划
    public int minMoney2(int[] d, int[] p) {
        int maxAbility = 0;
        for (int a : d) {
            maxAbility += a;
        }
        int[][] dp = new int[d.length + 1][maxAbility + 1];
        for (int index = d.length - 1; index >= 0; index--) {
            for (int ability = 0; ability <= maxAbility; ability++){
                if (ability + d[index] > maxAbility)continue;
                if (ability < d[index]) {
                    dp[index][ability] = p[index] + dp[index + 1][ability + d[index]];
                } else {
                    dp[index][ability] = Math.min(p[index] + dp[index + 1][ability + d[index]],
                            dp[index + 1][ability]);
                }
            }
        }
        return dp[0][0];
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
            long ans1 = minMoney(d, p);
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
