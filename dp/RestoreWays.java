package dp;

import org.junit.Test;

/**
 * @author wanghu
 * @discrption： 整型数组arr长度为n(3 < = n < = 1 0 ^ 4) ， 最初每个数字是 < = 2 0 0 的正数且满足如下条件:
 * 1 . arr [ 0 ] < = arr [ 1 ]
 * 2 . arr [ n-1 ] < = arr [ n-2 ]
 * 3 . arr [ i ] < = max ( arr [ i-1 ], arr [ i + 1 ] )
 * 但是在arr有些数字丢失了 ， 比如k位置的数字之前是正数 ， 丢失之后k位置的数字为0 。
 * 请你根据上述条件 ， 计算可能有多少种不同的arr可以满足以上条件 。 比如 [ 6, 0, 9 ] 只有还原成 [ 6, 9, 9 ] 满足全部三个条件 ， 所以返回1种 。
 * @create 2022-07-29 9:33
 */
public class RestoreWays {

    // 暴力递归
    public int ways1(int[] arr) {
        int N = arr.length;
        if (arr[N - 1] != 0) {
            return process1(arr, N - 1, arr[N - 1], 2);
        } else {
            int ways = 0;
            for (int v = 1; v <= 200; v++) {
                ways += process1(arr, N - 1, v, 2);
            }
            return ways;
        }
    }

    public int process1(int[] arr, int index, int v, int status) {
        if (index == 0) {
            return ((status == 0 || status == 1) && (arr[0] == 0 || v == arr[0])) ? 1 : 0;
        }
        if (arr[index] != 0 && v != arr[index]) {
            return 0;
        }
        int ways = 0;
        if (status == 0 || status == 1) {
            for (int pre = 1; pre <= 200; pre++) {
                ways += process1(arr, index - 1, pre, pre > v ? 2 : (pre == v ? 1 : 0));
            }
        } else {
            for (int pre = v; pre <= 200; pre++) {
                ways += process1(arr, index - 1, pre, pre == v ? 1 : 2);
            }
        }
        return ways;
    }

    // 换一种方式
    public int ways2(int[] arr) {
        int N = arr.length;
        if (arr[N - 1] != 0) {
            return process2(arr, N - 1, arr[N - 1], 2);
        } else {
            int ways = 0;
            for (int v = 1; v <= 200; v++) {
                ways += process2(arr, N - 1, v, 2);
            }
            return ways;
        }
    }

    public int process2(int[] arr, int index, int v, int status) {
        if (index == 0) {
            return ((status == 0 || status == 1) && (arr[0] == 0 || v == arr[0])) ? 1 : 0;
        }
        if (arr[index] != 0 && v != arr[index]) {
            return 0;
        }
        int ways = 0;
        if (status == 0 || status == 1) {
            for (int pre = 1; pre < v; pre++) {
                ways += process2(arr, index - 1, pre, 0);
            }
        }
        ways += process2(arr, index - 1, v, 1);
        for (int pre = v + 1; pre <= 200; pre++) {
            ways += process2(arr, index - 1, pre, 2);
        }
        return ways;
    }

    // 动态规划
    public int ways3(int[] arr) {
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int v = 1; v <= 200; v++) {
                dp[0][v][0] = 1;
                dp[0][v][1] = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int v = 1; v <= 200; v++) {
                for (int s = 0; s < 3; s++) {
                    if (arr[i] == 0 || v == arr[i]){
                        if (s == 0 || s == 1) {
                            for (int pre = 1; pre < v; pre++) {
                                dp[i][v][s] += dp[i - 1][pre][0];
                            }
                        }
                        dp[i][v][s] += dp[i - 1][v][1];
                        for (int pre = v + 1; pre <= 200; pre++) {
                            dp[i][v][s] += dp[i - 1][pre][2];
                        }
                    }
                }
            }
        }
        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            int ways = 0;
            for (int v = 1; v <= 200; v++) {
                ways += dp[N - 1][v][2];
            }
            return ways;
        }
    }

    @Test
    public void test() {
        int len = 4;
        int testTime = 15;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * len) + 2;
            int[] arr = generateRandomArray(N);
            int ans1 = ways1(arr);
            int ans2 = ways2(arr);
            int ans3 = ways3(arr);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("功能测试结束");
//        System.out.println("===========");
//        int N = 100000;
//        int[] arr = generateRandomArray(N);
//        long begin = System.currentTimeMillis();
//        ways3(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("run time : " + (end - begin) + " ms");
    }

    // for test
    public int[] generateRandomArray(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            if (Math.random() < 0.5) {
                ans[i] = 0;
            } else {
                ans[i] = (int) (Math.random() * 200) + 1;
            }
        }
        return ans;
    }

    // for test
    public void printArray(int[] arr) {
        System.out.println("arr size : " + arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
