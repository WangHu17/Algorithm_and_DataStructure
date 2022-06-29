package slidewindow;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author wanghu
 * @discrption： 达标子数组
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 - sub中最小值 <= num
 * 返回arr中达标子数组的数量。
 * @create 2022-06-24 10:46
 */
public class ArrCount {

    public int arrNum(int[] arr, int num) {
        if (arr == null || arr.length == 0)return 0;
        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> minQueue = new LinkedList<>();
        int N = arr.length;
        int R = 0;
        int res = 0;
        for (int L=0; L<N; L++){
            while (R < N){
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[R])
                    maxQueue.pollLast();
                maxQueue.addLast(R);
                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[R])
                    minQueue.pollLast();
                minQueue.addLast(R);
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num)
                    break;
                else
                    R++;
            }
            res += R - L;
            if (maxQueue.peekFirst() == L)maxQueue.pollFirst();
            if (minQueue.peekFirst() == L)minQueue.pollFirst();
        }
        return res;
    }

    @Test
    public void test() {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = arrNum(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }

    // 暴力的对数器方法
    public int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    // for test
    public int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

}
