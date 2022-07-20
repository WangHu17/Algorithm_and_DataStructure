package datascale;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wanghu
 * @discrption： 数组所有子序列中累加和%m之后的最大值
 * @create 2022-07-20 11:30
 */
public class SumModM1 {

    // 数组每个元素不大，m巨大，数组长度 * 所有元素的累加和 不能大于 10的8次方。

    // 暴力递归
    public int max1(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        int max = 0;
        for (Integer n : set) {
            max = Math.max(max, n % m);
        }
        return max;
    }

    public void process(int[] arr, int index, int sum, Set<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
        } else {
            process(arr, index + 1, sum, set);
            process(arr, index + 1, sum + arr[index], set);
        }
    }

    // 动态规划
    public int max2(int[] arr, int m) {
        int N = arr.length;
        int sum = 0;
        for (int n : arr) sum += n;
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[N - 1][i]) {
                max = Math.max(max, i % m);
            }
        }
        return max;
    }

    @Test
    public void test() {
        int len = 10;
        int value = 100;
        int m = 76;
        int testTime = 50000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = max1(arr, m);
            int ans2 = max2(arr, m);
            int ans3 = SumModM2.max3(arr, m);
            int ans4 = SumModM3.max4(arr, m);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }
    public int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        return ans;
    }

}
