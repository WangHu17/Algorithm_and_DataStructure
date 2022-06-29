package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-20 16:14
 */
public class Money2 {

    // 暴力递归
    public int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0)
            return 0;
        return process1(arr, 0, aim);
    }

    public int process1(int[] arr, int index, int rest) {
        if (index == arr.length)
            return rest == 0 ? 1 : 0;
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process1(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }

    // 动态规划
    public int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0)
            return 0;
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //优化前
//                int ways = 0;
//                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//                    ways += dp[index + 1][rest - zhang * arr[index]];
//                }
//                dp[index][rest] = ways;
                // 优化后
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    @Test
    public void test() {
        int maxLen = 10;
        int maxValue = 30;
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
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
