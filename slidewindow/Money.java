package slidewindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghu
 * @discrption： 货币问题
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，返回组成aim的最少货币数
 * @create 2022-06-28 11:04
 */
public class Money {

    // 暴力递归
    public int minCoins1(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public int process(int[] arr, int index, int rest) {
        if (rest < 0)
            return Integer.MAX_VALUE;
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int p1 = process(arr, index + 1, rest);
        int p2 = process(arr, index + 1, rest - arr[index]);
        if (p2 != Integer.MAX_VALUE)
            p2++;
        return Math.min(p1, p2);
    }

    // 动态规划
    public int minCoins2(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 1; i <= aim; i++)
            dp[N][i] = Integer.MAX_VALUE;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE)
                    p2++;
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim];
    }

    // 暴力递归：数量统计
    class Info {
        int[] coins;
        int[] zhangs;

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    public Info getInfo(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : arr)
            map.put(a, map.getOrDefault(a, 0) + 1);
        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    public int minCoins3(int[] arr, int aim) {
        Info info = getInfo(arr);
        return process1(info.coins, info.zhangs, 0, aim);
    }

    public int process1(int[] c, int[] z, int index, int rest) {
        if (index == c.length)
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * c[index] <= rest && zhang <= z[index]; zhang++) {
            int next = process1(c, z, index + 1, rest - zhang * c[index]);
            if (next != Integer.MAX_VALUE)
                res = Math.min(res, zhang + next);
        }
        return res;
    }

    // 动态规划：数量统计
    public int minCoins4(int[] arr, int aim) {
        Info info = getInfo(arr);
        int[] c = info.coins;
        int[] z = info.zhangs;
        int N = c.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 1; i <= aim; i++)
            dp[N][i] = Integer.MAX_VALUE;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * c[index] <= rest && zhang <= z[index]; zhang++) {
                    if (rest - zhang * c[index] >= 0) {
                        int next = dp[index + 1][rest - zhang * c[index]];
                        if (next != Integer.MAX_VALUE)
                            res = Math.min(res, zhang + next);
                    }
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][aim];
    }


    // 为了测试
    @Test
    public void test() {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins1(arr, aim);
            int ans2 = minCoins2(arr, aim);
            int ans3 = minCoins3(arr, aim);
            int ans4 = minCoins4(arr, aim);
            if (ans1 != ans2 || ans1 != ans3 || ans2 != ans3 || ans1 != ans4) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

    // 为了测试
    public int[] randomArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
