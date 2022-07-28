package dp;

import java.util.LinkedList;

/**
 * @author wanghu
 * @discrption： 给定一个数组arr，和一个正数M，返回在arr的子数组在长度不超过M的情况下，最大的累加和。
 * @create 2022-07-27 16:20
 */
public class MaxSubArraySum {

    public static int maxSum(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M == 0) return 0;
        int N = arr.length;
        if (M > N) M = N;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int i = 0;
        for (; i < M; i++) {
            while (!queue.isEmpty() && sum[queue.peekLast()] <= sum[i]) {
                queue.pollLast();
            }
            queue.add(i);
        }
        int max = sum[queue.peekFirst()];
        int L = 0;
        for (; i < N; i++, L++) {
            if (queue.peekFirst() == L) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && sum[queue.peekLast()] <= sum[i]) {
                queue.pollLast();
            }
            queue.add(i);
            max = Math.max(max, sum[queue.peekFirst()] - sum[L]);
        }
        for (; L < N-1; L++) {
            if (queue.peekFirst() == L) {
                queue.pollFirst();
            }
            max = Math.max(max, sum[queue.peekFirst()] - sum[L]);
        }
        return max;
    }

    // 用作测试
    public static void main(String[] args) {
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = randomArray(N, maxValue);
            int ans1 = test(arr, M);
            int ans2 = maxSum(arr, M);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

    // O(N^2)的解法，暴力解，用作对数器
    public static int test(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int L = 0; L < N; L++) {
            int sum = 0;
            for (int R = L; R < N; R++) {
                if (R - L + 1 > M) {
                    break;
                }
                sum += arr[R];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    // 用作测试
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

}
