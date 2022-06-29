package monotonicstack;

import org.junit.Test;

import java.util.Stack;

/**
 * @author wanghu
 * @discrption： 给定一个只包含正数的数组arr，arr中任何一个子数组sub，一定都可以算出(sub累加和)*(sub中的最小值)是什么，那么所有子数组中，这个值最大的是多少?
 * @create 2022-06-28 16:47
 */
public class MaxSubArr {

    public int max2(int[] arr){
        int N = arr.length;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i=1; i<N; i++){
            sum[i] = sum[i-1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i=0; i<N; i++){
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                Integer j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sum[i-1] : (sum[i-1] - sum[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sum[N-1] : (sum[N-1] - sum[stack.peek()])) * arr[j]);
        }
        return max;
    }

    @Test
    public void test() {
        int testTimes = 200000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
    public int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }
    public int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

}
