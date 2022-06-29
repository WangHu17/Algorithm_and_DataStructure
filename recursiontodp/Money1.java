package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： arr是货币数组，其中的值都是正数。再给定一个正数aim。每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，返回组成aim的方法数
 * 例如︰ arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2一共就3种方法，所以返回3
 * @create 2022-06-20 15:23
 */
public class Money1 {

    public int coinWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        return process(arr, 0, aim);
    }

    // 暴力递归
    public int process(int[] arr, int index, int rest) {
        if (rest < 0)
            return 0;
        if (index == arr.length)
            return rest == 0 ? 1 : 0;
        return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
    }

    // 动态规划
    public int coinWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest=0; rest<=aim; rest++){
                dp[index][rest] = dp[index+1][rest] + (rest - arr[index] >= 0 ? dp[index+1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    @Test
    public void test() {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays1(arr, aim);
            int ans2 = coinWays2(arr, aim);
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
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
