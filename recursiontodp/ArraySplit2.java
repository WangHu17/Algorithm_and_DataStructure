package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-23 10:00
 */
public class ArraySplit2 {

    public int arraySplit1(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int sum = 0;
        for (int a : arr)
            sum += a;
        int N = arr.length;
        if ((N & 1) == 0)
            return process(arr, 0, N / 2, sum / 2);
        else
            return Math.max(process(arr, 0, N / 2, sum / 2), process(arr, 0, N / 2 + 1, sum / 2));
    }

    public int process(int[] arr, int i, int picks, int rest) {
        if (i == arr.length)
            return picks == 0 ? 0 : -1;
        int p1 = process(arr, i + 1, picks, rest);
        int p2 = -1;
        int next = -1;
        if (arr[i] <= rest) {
            next = process(arr, i + 1, picks - 1, rest - arr[i]);
        }
        if (next != -1)
            p2 = arr[i] + next;
        return Math.max(p1, p2);
    }

    public int arraySplit2(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int sum = 0;
        for (int a : arr)
            sum += a;
        int N = arr.length;
        int[][][] dp = new int[N + 1][(N + 1) / 2 + 1][sum / 2 + 1];
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= (N + 1) / 2; j++)
                for (int k = 0; k <= sum / 2; k++)
                    dp[i][j][k] = -1;
        for (int rest = 0; rest <= sum / 2; rest++)
            dp[N][0][rest] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int picks = 0; picks <= (N + 1) / 2; picks++) {
                for (int rest = 0; rest <= sum / 2; rest++) {
                    int p1 = dp[i + 1][picks][rest];
                    int p2 = -1;
                    int next = -1;
                    if (picks - 1 >= 0 && arr[i] <= rest) {
                        next = dp[i + 1][picks - 1][rest - arr[i]];
                    }
                    if (next != -1)
                        p2 = arr[i] + next;
                    dp[i][picks][rest] = Math.max(p1, p2);
                }
            }
        }
        if ((N & 1) == 0)
            return dp[0][N / 2][sum / 2];
        else
            return Math.max(dp[0][N / 2][sum / 2], dp[0][N / 2 + 1][sum / 2]);
    }


    @Test
    public void test() {
        int maxLen = 10;
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

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
