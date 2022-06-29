package recursiontodp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-20 17:24
 */
public class Money3 {

    class Info {
        int[] coins;
        int[] zhangs;

        public Info(int[] coins, int[] zhang) {
            this.coins = coins;
            this.zhangs = zhang;
        }
    }

    public Info getInfo(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int[] coins = new int[map.size()];
        int[] zhang = new int[map.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[i] = entry.getKey();
            zhang[i++] = entry.getValue();
        }
        return new Info(coins, zhang);
    }

    // 暴力递归
    public int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        Info info = getInfo(arr);
        return process(info.coins, info.zhangs, 0, aim);
    }

    public int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length)
            return rest == 0 ? 1 : 0;
        int ways = 0;
        for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
            ways += process(coins, zhangs, index + 1, rest - zhang * coins[index]);
        }
        return ways;
    }

    // 动态规划
    public int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 优化前
//                int ways = 0;
//                for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
//                    ways += dp[index + 1][rest - zhang * coins[index]];
//                }
//                dp[index][rest] = ways;
                // 优化后
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    @Test
    public void test() {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay1(arr, aim);
            int ans2 = coinsWay2(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    // 为了测试
    public int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
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
