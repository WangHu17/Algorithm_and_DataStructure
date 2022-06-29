package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-23 9:30
 */
public class ArraySplit1 {

    // 暴力递归：
    public int arraySplit1(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        return process(arr, 0, sum / 2);
    }

    public int process(int[] arr, int i, int rest) {
        if (i == arr.length)
            return 0;
        int p1 = process(arr, i + 1, rest);
        int p2 = 0;
        if (arr[i] <= rest)
            p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
        return Math.max(p1, p2);
    }

    // 动态规划：
    public int arraySplit2(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][sum / 2 + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum / 2; rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                if (arr[i] <= rest)
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum / 2];
    }

    @Test
    public void test() {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = arraySplit1(arr);
            int ans2 = arraySplit2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
