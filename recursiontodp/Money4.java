package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-21 9:45
 */
public class Money4 {

    // 暴力递归：
    public int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return Integer.MAX_VALUE;
        return process(arr, 0, aim);
    }

    public int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int next = process(arr, index + 1, rest - zhang * arr[index]);
            if (next != Integer.MAX_VALUE) {
                res = Math.min(res, next + zhang);
            }
        }
        return res;
    }

    // 动态规划：
    public int minCoins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return Integer.MAX_VALUE;
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 优化前
//                int res = Integer.MAX_VALUE;
//                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//                    int next = dp[index + 1][rest - zhang * arr[index]];
//                    if (next != Integer.MAX_VALUE) {
//                        res = Math.min(res, next + zhang);
//                    }
//                }
//                dp[index][rest] = res;
                // 优化后
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
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
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
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
