package recursiontodp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 数组分裂问题1
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：最接近的情况下，较小集合的累加和。
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
